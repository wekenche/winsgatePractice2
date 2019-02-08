package w.fujiko.dao.repo.billings;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.model.masters.billings.Billing;
import w.fujiko.model.masters.billings.Billing_;
import w.fujiko.dao.billings.BillingDao;

import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class BillingDaoRepo 
	extends UniversalCrudRepo<Billing ,Integer>
	implements BillingDao {

	public BillingDaoRepo() {
		super();
		type = Billing.class;
	}

	@Override
	public Optional<Billing> getUndeletedBillingByCode(Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Billing> criteria = builder.createQuery(type);
		Root<Billing> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(Billing_.code), code), 
					builder.equal(root.get(Billing_.isEnd), false)
				)
			);
		
		Optional<Billing> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}
}
