package w.fujiko.dao.repo.systems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.systems.ProgramCommandDao;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.Program_;
import w.fujiko.model.masters.systems.ProgramCommand;
import w.fujiko.model.masters.systems.ProgramCommand_;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.model.masters.users.RoleProgramCommand;
import w.fujiko.model.masters.users.RoleProgram_;
import w.fujiko.model.masters.users.RoleProgramCommand_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.service.systems.ProgramService;

@Repository
@Transactional
public class ProgramCommandDaoRepo 
	extends UniversalCrudRepo<ProgramCommand,Integer>
	implements ProgramCommandDao {
		public ProgramCommandDaoRepo() {
		super();
		type = ProgramCommand.class;
	}

	@Autowired
	ProgramService programService;

	/**
	 * Get the programs with its commands that the user has permission
	 */
	@Override
	public List<Program> getGrantedProgramCommandByUser(Integer userId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProgramCommand> criteria = builder.createQuery(ProgramCommand.class);		
		Root<ProgramCommand> root = criteria.from(type);

		Join<ProgramCommand,Program> programJoined = root.join(ProgramCommand_.program);		
		Join<Program,RoleProgram> roleProgramJoined = programJoined.join(Program_.roles);
		Join<RoleProgram,User> userJoined = roleProgramJoined.join(RoleProgram_.authorizedUser);

		criteria
			.select(root)
			.distinct(true)
			.where(builder.and(builder.equal(userJoined.get(User_.id),
			                                 userId),
							   builder.equal(root.get(ProgramCommand_.isPermissionNeeded),
							                 true)))			  
			.orderBy(builder.asc(root.get(ProgramCommand_.keyCode)));

		try {			

			List<Program> programList=new ArrayList<>();
			List<ProgramCommand> ProgramCommandList = session.createQuery(criteria).getResultList();	
			
			//filter only commands the the user has permission to 
			ProgramCommandList.stream().forEach(command->{
							  command.setRoleProgramCommands(command.getRoleProgramCommands()
									 .stream()
									 .filter(roleCommand->roleCommand.getRole_program()
																	 .getAuthorizedUser()
																	 .getId()==userId)
									 .collect(Collectors.toSet())
								);					
			});

			ProgramCommandList.stream().collect(Collectors.groupingBy(ProgramCommand::getProgramId,Collectors.toSet()))
				.entrySet().forEach(e->{					
					programService.get(e.getKey()).ifPresent(prog->{
						prog.setCommands(e.getValue()
										  .stream()
										  .sorted((n1,n2)->Integer.valueOf((n1.getKeyCode()!=null ? n1.getKeyCode():0))
										  						  .compareTo(Integer.valueOf((n2.getKeyCode()!=null ? n2.getKeyCode():0))))
																  .collect(Collectors.toList()));									
						programList.add(prog);
					});
			});

			session.close();
			return programList;
		} catch (NoResultException nre) {
			session.close();
			return new ArrayList<>();
		}						
	}

	/**
	 * Gets a list of commands that a user has permission on a program/page
	 */
	public List<ProgramCommand> getGrantedCommandOfUserByPage(int userId,String pageId) {

		Session session = this.getSessionFactory();	
				 
		try {
			var grantedProgramCommandList = session.createNativeQuery(
												this.getGrantedCommandOfUserByPageSQLStatement(),
												ProgramCommand.class
											).setResultSetMapping("ProgramCommandMapping")
											 .setParameter("userId", userId)
											 .setParameter("programId", pageId)
											 .getResultList();				                    
			session.close();
			return grantedProgramCommandList;
		} catch (NoResultException nre) {
			session.close();
			return new ArrayList<ProgramCommand>();
		}		

	}

	private String getGrantedCommandOfUserByPageSQLStatement(){
		String queryStatement = "  SELECT cmd.id, "
								+"		  cmd.prog_command_name, "	
								+"		  cmd.command_function, "
								+"		  cmd.prog_command_key, "
								+"		  cmd.prog_command_key_code, "	
								+"		  cmd.is_permission_needed, "									
								+"		  cmd.sys_prog_id "															
                                +" FROM "
                                +" 	  sys_prog_command cmd"
                                +" INNER JOIN "
                                +" 	  mst_role_prog_command rolecmd"
                                +" 	   	 ON  cmd.id = rolecmd.mst_role_prog_command_id"
                             	+" INNER JOIN "
                                +" 	  mst_role_prog roleprog"
                                +" 	  	  ON  rolecmd.authorized_user_id = roleprog.authorized_user_id"
                                +" 	  	  AND  rolecmd.mst_role_prog_command_progid = roleprog.mst_role_prog_id"
                                +" INNER JOIN "
                                +" 	  sys_prog sysprog"
                                +" 	   	 ON  roleprog.mst_role_prog_id = sysprog.sys_prog_id"
                                +" INNER JOIN "
                                +" 	  mst_u_user user1"
                                +" 	   	 ON  roleprog.authorized_user_id = user1.mst_u_user_id"
                                +" WHERE "
                                +" 	   	 user1.mst_u_user_id = :userId"
                                +" 	   	 AND sysprog.sys_prog_id = :programId"
                                +" UNION "
								+" SELECT cmd2.id, "
								+"		  cmd2.prog_command_name, "
								+"		  cmd2.command_function, "
								+"		  cmd2.prog_command_key, "
								+"		  cmd2.prog_command_key_code, "								
								+"		  cmd2.is_permission_needed, "			
								+"		  cmd2.sys_prog_id "		
                                +" FROM "
                                +" 	  sys_prog_command cmd2"
                                +" INNER JOIN "
                                +" 	  sys_prog sysprog2"
                                +" 	   	 ON  cmd2.sys_prog_id = sysprog2.sys_prog_id"
                                +" WHERE "
                                +" 	   	 cmd2.is_permission_needed = 0"
                                +" 	   	 AND sysprog2.sys_prog_id = :programId";
        return queryStatement;                                                                   
	}

}
