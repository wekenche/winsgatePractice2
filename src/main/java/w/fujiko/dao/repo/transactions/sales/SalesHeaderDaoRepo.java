package w.fujiko.dao.repo.transactions.sales;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.sales.SalesHeaderDao;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.model.transactions.sales.SalesHeader_;

@Repository
@Transactional
public class SalesHeaderDaoRepo 
	extends UniversalCrudRepo<SalesHeader, Integer> 
	implements SalesHeaderDao {
	
	public SalesHeaderDaoRepo() {
		super();
		type = SalesHeader.class;
	}

	@Override
	public Optional<SalesHeader> getBySlipNumber(String slipNumber) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SalesHeader> criteria = builder.createQuery(type);
		Root<SalesHeader> root = criteria.from(type);

		criteria.select(root)
			.where(
				builder.equal(root.get(SalesHeader_.slipNumber), slipNumber)
			);

		Optional<SalesHeader> result;
	
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		}catch(NoResultException nre) {
			result = Optional.empty();
		}finally{
			session.close();
		}
		
		return result;
	}

	@Override
	public void deleteById(Integer salesHeaderId) {
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<SalesHeader> criteria = builder.createCriteriaDelete(SalesHeader.class);
		Root<SalesHeader> root = criteria.from(SalesHeader.class);
		
		criteria.where(
				builder.equal(root.get(SalesHeader_.id), salesHeaderId)
			);
		
		try {
			transaction.begin();			
			session.createQuery(criteria).executeUpdate();			
			transaction.commit();			
		} catch(ConstraintViolationException ex) {
			ex.printStackTrace();
			transaction.rollback();
		}
		finally{
			session.close();
		}
		
	}

	// @Override
	// public Page<SalesHeader> salesHeaderSearch(PurchaseOrderHeaderSearchDTO searchKeys) {
	// 	throw new NotImplementedException();
	// }

}