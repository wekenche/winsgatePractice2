package w.fujiko.dao.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.GenericClassificationItemDao;
import w.fujiko.model.masters.GenericClassification;
import w.fujiko.model.masters.GenericClassificationItem;
import w.fujiko.model.masters.GenericClassificationItem_;
import w.fujiko.model.masters.GenericClassification_;

@Repository
@Transactional
public class GenericClassificationItemDaoRepo 
	extends UniversalCrudRepo<GenericClassificationItem, Integer>
	implements GenericClassificationItemDao {
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_GENERIC_CLASSIFICATION = "genericClassification";
	public static final String FIELD_CODE = "code";
	public static final String FIELD_IS_END = "isEnd";
	
	public GenericClassificationItemDaoRepo() {
		super();
		type = GenericClassificationItem.class;
	}

	@Override
	public Optional<GenericClassificationItem> 
		getUndeletedItemByCode(String genericClassCode, Integer genericClassItemCode) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<GenericClassificationItem> criteria = builder.createQuery(type);
		Root<GenericClassificationItem> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(FIELD_GENERIC_CLASSIFICATION).get(FIELD_CODE), genericClassCode),
					builder.equal(root.get(FIELD_CODE), genericClassItemCode),
					builder.equal(root.get(FIELD_IS_END), false)
				)
			);
		
		Optional<GenericClassificationItem> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

	}

	@Override
	public List<GenericClassificationItem> getByGenericClassificationId(String genericClassId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<GenericClassificationItem> criteria = builder.createQuery(type);		
		Root<GenericClassificationItem> root = criteria.from(type);
		Join<GenericClassificationItem,GenericClassification> joinGenericClassification = root.join(GenericClassificationItem_.genericClassification);

		criteria.select(root)
			.where(
				builder.equal(joinGenericClassification.get(GenericClassification_.id), genericClassId)
			);
		
		try {
			return session.createQuery(criteria).getResultList();
		} catch(NoResultException nre) {
			return new ArrayList<GenericClassificationItem>();
		}finally{
			session.close();
		}
		
	}

}