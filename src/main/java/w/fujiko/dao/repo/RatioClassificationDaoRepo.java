package w.fujiko.dao.repo;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.RatioClassificationDao;
import w.fujiko.model.masters.RatioClassification;

@Repository
@Transactional
public class RatioClassificationDaoRepo
	extends UniversalCrudRepo<RatioClassification, Integer>
	implements RatioClassificationDao {
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_MAKER = "maker";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_IS_END = "isEnd";
	
	public RatioClassificationDaoRepo() {
		super();
		type = RatioClassification.class;
	}

	@Override
	public Optional<RatioClassification> getUndeletedItemByCode(Integer code, Integer makerId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<RatioClassification> criteria = builder.createQuery(type);
		Root<RatioClassification> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(FIELD_MAKER).get(FIELD_ID), makerId),
					builder.equal(root.get(FIELD_CODE), code),
					builder.equal(root.get(FIELD_IS_END), false)
				)
			);
		
		Optional<RatioClassification> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

	}

}