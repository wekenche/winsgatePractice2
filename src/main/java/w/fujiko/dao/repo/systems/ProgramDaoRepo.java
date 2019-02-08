package w.fujiko.dao.repo.systems;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.systems.ProgramDao;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.model.masters.users.RoleProgram_;
import w.fujiko.model.masters.systems.Program;
import w.fujiko.model.masters.systems.Program_;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.model.masters.systems.ProgramCategory_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;

@Repository
@Transactional
public class ProgramDaoRepo 
	extends UniversalCrudRepo<Program ,String>
	implements ProgramDao {

		public ProgramDaoRepo() {
			super();
			type = Program.class;
		}

		@Override
		public List<Program> getGrantedProgramInCategoryOfUser(Integer userId,char categoryId) {					
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Program> criteria = builder.createQuery(type);
			Root<Program> root = criteria.from(type);
			Join<Program,ProgramCategory> categoryJoined = root.join(Program_.category);
			Join<Program,RoleProgram> rolesJoined  = root.join(Program_.roles);
			Join<RoleProgram,User> userJoined  = rolesJoined.join(RoleProgram_.authorizedUser);
			criteria
				.select(root)
				.where(builder.and(
				 					builder.equal(
				 								  userJoined.get(User_.id),
												  userId),
				 					builder.equal(
				 								  categoryJoined.get(ProgramCategory_.id),
												  categoryId)
					)
		        );
				     
			try {
				List<Program> programList = session.createQuery(criteria).getResultList();				
				session.close();
				return programList;
			} catch (NoResultException nre) {
				session.close();
				return new ArrayList<Program>();
			}		
		}

		@Override
		public List<Program> getGrantedProgramsOfUser(Integer userId) {
			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Program> criteria = builder.createQuery(type);
			Root<Program> root = criteria.from(type);
			Join<Program,RoleProgram> rolesJoined  = root.join(Program_.roles);
			Join<RoleProgram,User> userJoined  = rolesJoined.join(RoleProgram_.authorizedUser);
			criteria
				.select(root)
				.where(builder.equal(userJoined.get(User_.id), userId));
				     
			try {
				List<Program> programList = session.createQuery(criteria).getResultList();				
				session.close();
				return programList;
			} catch (NoResultException nre) {
				session.close();
				return new ArrayList<Program>();
			}
		}
}
