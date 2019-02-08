package w.fujiko.dao.repo.transactions.numgens;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.numgens.WorkingSiteNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.WorkingSiteNumberGenerator;

/**
 * @author yagami
 */

@Repository
@Transactional
public class WorkingSiteNumberGeneratorDaoRepo extends UniversalCrudRepo<WorkingSiteNumberGenerator, Integer>
        implements WorkingSiteNumberGeneratorDao {

	@Override
	public void deleteByUserID(Integer userId) {
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<WorkingSiteNumberGenerator> criteria = builder.createCriteriaDelete(WorkingSiteNumberGenerator.class);
		Root<WorkingSiteNumberGenerator> root = criteria.from(WorkingSiteNumberGenerator.class);
		
		criteria.where(
				builder.equal(root.get("user").get("id"), userId)
			);
		
		try {
			transaction.begin();
			session.createQuery(criteria).executeUpdate();
			transaction.commit();
		} catch(ConstraintViolationException ex) {
			transaction.rollback();
		}
		finally{
			session.close();
		}
	}}
