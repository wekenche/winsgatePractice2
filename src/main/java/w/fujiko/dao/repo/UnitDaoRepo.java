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
import w.fujiko.dao.UnitDao;
import w.fujiko.model.masters.Unit;

@Repository
@Transactional
public class UnitDaoRepo extends UniversalCrudRepo<Unit, Integer> implements UnitDao {
	
	public static final String FIELD_CODE = "code";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_IS_END = "isEnd";
	
	public UnitDaoRepo() {
		super();
		type = Unit.class;
	}
	
	@Override
	public List<Unit> getUnitsByNameOrCode(String key, Integer index) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Unit> criteria = builder.createQuery(type);
		Root<Unit> root = criteria.from(type);
		
		int code;
		try {
			code = Integer.parseInt(key);
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
		
		List<Unit> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public Optional<Unit> getUndeletedItemByCode(int code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Unit> criteria = builder.createQuery(type);
		Root<Unit> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(FIELD_CODE), code), 
					builder.equal(root.get(FIELD_IS_END), false)
				)
			);
		
		Optional<Unit> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

	}
	
}