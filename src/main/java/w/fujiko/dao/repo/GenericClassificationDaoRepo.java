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
import w.fujiko.dao.GenericClassificationDao;
import w.fujiko.model.masters.GenericClassification;

@Repository
@Transactional
public class GenericClassificationDaoRepo 
	extends UniversalCrudRepo<GenericClassification, Integer>
	implements GenericClassificationDao {
	
	public static final String FIELD_CODE = "code";
	public static final String FIELD_IS_END = "isEnd";
	
	public GenericClassificationDaoRepo() {
		super();
		type = GenericClassification.class;
	}

	@Override
	public Optional<GenericClassification> getUndeletedItemByCode(String code) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<GenericClassification> criteria = builder.createQuery(type);
		Root<GenericClassification> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(FIELD_CODE), code), 
					builder.equal(root.get(FIELD_IS_END), false)
				)
			);
		
		Optional<GenericClassification> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

	}

}
