package w.fujiko.dao.repo.destinations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import w.fujiko.model.masters.customers.CustomerBase_;
import w.fujiko.model.masters.customers.CustomerGroup_;
import w.fujiko.model.masters.destinations.Destination;
import w.fujiko.model.masters.destinations.Destination_;
import w.fujiko.dao.destinations.DestinationDao;

import fte.api.universal.UniversalCrudRepo;

@Repository
@Transactional
public class DestinationDaoRepo 
	extends UniversalCrudRepo<Destination ,Integer>
	implements DestinationDao {

		public DestinationDaoRepo() {
			super();
			type = Destination.class;
		}

	@Override
	public Optional<Destination> getUndeletedDestinationByCode(Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Destination> criteria = builder.createQuery(type);
		Root<Destination> root = criteria.from(type);
		
		criteria.select(root)
				.where(
					builder.and(
						builder.equal(root.get(Destination_.code), code), 
						builder.equal(root.get(Destination_.isEnd), false)
					)
				);
		
		Optional<Destination> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}

	@Override
	public List<Destination> getByCustomerCode(String customerCode) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Destination> criteria = builder.createQuery(type);
		Root<Destination> root = criteria.from(type);
		var joinCustomerBase = root.join(Destination_.customer);
		var joinCustomerGroup = joinCustomerBase.join(CustomerBase_.group);
		  
		criteria.select(root)
				.where(
					builder.equal(
						builder.concat(
							joinCustomerGroup.get(CustomerGroup_.code),
							joinCustomerBase.get(CustomerBase_.code)
						),
						customerCode
					)
				);

		try {
			return session.createQuery(criteria).getResultList();
		} catch(NoResultException nre) {
			return new ArrayList<>();
		}finally{
			session.close();
		}
	}
}
