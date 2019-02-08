package w.fujiko.dao.repo.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.dao.users.RoleProgramCommandDao;
import w.fujiko.model.masters.users.RoleProgramCommandPk;
import w.fujiko.model.masters.users.RoleProgramCommand_;
import w.fujiko.model.masters.users.RoleProgram;
import w.fujiko.model.masters.users.RoleProgramCommand;
import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class RoleProgramCommandDaoRepo 
	extends UniversalCrudRepo<RoleProgramCommand ,RoleProgramCommandPk>
	implements RoleProgramCommandDao {

		public RoleProgramCommandDaoRepo() {
			super();
			type = RoleProgramCommand.class;
		}

		public List<RoleProgramCommand> findByRoleProgram(RoleProgram roleProgram){

			Session session = this.getSessionFactory();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RoleProgramCommand> criteria = builder.createQuery(type);
			Root<RoleProgramCommand> root = criteria.from(type);
		
			criteria
				.select(root)
				.where(builder.and(
				 					builder.equal(
				 								  root.get(RoleProgramCommand_.ROLE_PROGRAM),
												   roleProgram)
					)
		        );
				     
			try {
				List<RoleProgramCommand> roleProgramList = session.createQuery(criteria).getResultList();				
				session.close();
				return roleProgramList;
			} catch (NoResultException nre) {
				session.close();
				return new ArrayList<RoleProgramCommand>();
			}		

		}

	@Override
    public void batchSave(List<RoleProgramCommand> roleProgramCommands) throws ConstraintViolationException, PersistenceException {
        
        final int BATCH_SIZE = 25;
        
        var session = this.getSessionFactory();
        var transaction = session.getTransaction();
        var listSize = roleProgramCommands.size();
        try {
            transaction.begin();
			for(int index = 0; index < listSize; index++) {                
				session.saveOrUpdate(roleProgramCommands.get(index));
				if (index > 0 && index % BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch(ConstraintViolationException cve) {
            transaction.rollback();            
			throw cve;
		} catch(PersistenceException cve) {
			transaction.rollback();
			throw cve;
		}finally {
			session.close();
		}
	}
	
	@Override
    public void batchDelete(List<RoleProgramCommand> roleProgramCommands) throws ConstraintViolationException, PersistenceException {
        
        final int BATCH_SIZE = 25;
        
        var session = this.getSessionFactory();
        var transaction = session.getTransaction();
        var listSize = roleProgramCommands.size();
        try {
            transaction.begin();
			for(int index = 0; index < listSize; index++) {                
				session.delete(roleProgramCommands.get(index));
				if (index > 0 && index % BATCH_SIZE == 0) {
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch(ConstraintViolationException cve) {
            transaction.rollback();            
			throw cve;
		} catch(PersistenceException cve) {
			transaction.rollback();
			throw cve;
		}finally {
			session.close();
		}
    }

}
