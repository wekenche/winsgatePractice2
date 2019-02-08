package w.fujiko.dao.repo;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
/**
 * @author johnl
 */
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.MakerDao;
import w.fujiko.model.masters.Maker;
import w.fujiko.model.masters.Maker_;

@Repository
@Transactional
public class MakerDaoRepo extends UniversalCrudRepo<Maker, Integer> implements MakerDao {
	
	public static final String FIELD_CODE = "code";
	public static final String FIELD_IS_END = "isEnd";
	
	public MakerDaoRepo() {
		super();
		type = Maker.class;
	}

	@Override
	public Optional<Maker> getUndeletedItemByCode(Integer code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Maker> criteria = builder.createQuery(type);
		Root<Maker> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(FIELD_CODE), code), 
					builder.equal(root.get(FIELD_IS_END), false)
				)
			);
		
		Optional<Maker> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

	}

	@Override
	public Optional<Maker> getByName(String name,Optional<Boolean> isEnd) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Maker> criteria = builder.createQuery(type);
		Root<Maker> root = criteria.from(type);
		
		var predicate = builder.equal(
							builder.lower(root.get(Maker_.name)),
							name.toLowerCase()
						);
		if(isEnd.isPresent()) {
			predicate = builder.and(
							predicate,
							builder.equal(root.get(Maker_.isEnd), isEnd.get())
						);	
		}

		criteria.select(root)
				.where(predicate);
		
		Optional<Maker> result;
		
		try {
			result = Optional.of(session.createQuery(criteria)
										.setFirstResult(0)
										.setMaxResults(1)
										.getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		} finally {
			session.close();
		}		
		
		return result;

	}
}
