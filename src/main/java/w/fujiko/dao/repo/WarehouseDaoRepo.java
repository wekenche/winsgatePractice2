package w.fujiko.dao.repo;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.WarehouseDao;
import w.fujiko.model.masters.Warehouse;

@Repository
@Transactional
public class WarehouseDaoRepo 
	extends UniversalCrudRepo<Warehouse, Integer> 
		implements WarehouseDao {
	
	public static final String FIELD_CODE = "code";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_IS_END = "isEnd";
	
	public WarehouseDaoRepo() {
		super();
		type = Warehouse.class;
	}

	@Override
	public List<Warehouse> getWarehousesByNameOrCode(String key, Integer index) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Warehouse> criteria = builder.createQuery(type);
		Root<Warehouse> root = criteria.from(type);
		
		long code;
		try {
			code = Long.parseLong(key);
		} catch(NumberFormatException nfe) {
			code = -1;
		}
		
		criteria.select(root)
			.where(
				builder.or(
					builder.equal(root.get(FIELD_CODE), code), 
					builder.like(
						builder.lower(root.get(FIELD_NAME)), 
						"%"+key+"%"
					)
				)
			);
		
		List<Warehouse> results = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return results;
	}

	@Override
	public Optional<Warehouse> getUndeletedWarehouseByCode(Long code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Warehouse> criteria = builder.createQuery(type);
		Root<Warehouse> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(FIELD_CODE), code), 
					builder.equal(root.get(FIELD_IS_END), false)
				)
			);
		
		Optional<Warehouse> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}
	
}