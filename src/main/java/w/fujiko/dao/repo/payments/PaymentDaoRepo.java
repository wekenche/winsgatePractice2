package w.fujiko.dao.repo.payments;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.payments.PaymentDao;
import w.fujiko.model.masters.payments.Payment;
import w.fujiko.model.masters.payments.Payment_;

@Repository
@Transactional
public class PaymentDaoRepo 
	extends UniversalCrudRepo<Payment ,Integer> 
	implements PaymentDao {
	
	public PaymentDaoRepo() {
		super();
		type = Payment.class;
	}

	@Override
	public Optional<Payment> getUndeletedPaymentByCode(Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Payment> criteria = builder.createQuery(type);
		Root<Payment> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(Payment_.code), code), 
					builder.equal(root.get(Payment_.isEnd), false)
				)
			);
		
		Optional<Payment> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

}