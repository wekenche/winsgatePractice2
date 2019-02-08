package w.fujiko.dao.repo.products;

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
import w.fujiko.dao.products.ProductClassificationItemDao;
import w.fujiko.model.masters.products.ProductClassificationItem;
import w.fujiko.util.common.constants.ProductConstants;

@Repository
@Transactional
public class ProductClassificationItemDaoRepo 
	extends UniversalCrudRepo<ProductClassificationItem, Integer>
	implements ProductClassificationItemDao {
	
	public ProductClassificationItemDaoRepo() {
		super();
		type = ProductClassificationItem.class;
	}

	@Override
	public List<ProductClassificationItem> getProductsByClassificationId(Integer productClassId, Integer index) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductClassificationItem> criteria = builder.createQuery(type);
		Root<ProductClassificationItem> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.equal(
					root.get(ProductConstants.FIELD_PRODUCT_CLASSIFICATION)
						.get(ProductConstants.FIELD_PRODUCT_CLASS_ID), 
					productClassId
				)
			);
		
		List<ProductClassificationItem> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public List<ProductClassificationItem> getProductsClassificationItemByNameOrCode(
			Integer classificationId, String key, Integer index) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductClassificationItem> criteria = builder.createQuery(type);
		Root<ProductClassificationItem> root = criteria.from(type);
		
		int code;
		try {
			code = Integer.parseInt(key);
		} catch(NumberFormatException nfe) {
			code = -1;
		}
		
		criteria.select(root)
			.where(builder.and(
				builder.equal(root.get(ProductConstants.FIELD_PRODUCT_CLASSIFICATION)
								.get(ProductConstants.FIELD_PRODUCT_CLASS_ID), classificationId),
				builder.or(
					builder.equal(root.get(ProductConstants.FIELD_CODE), code),
					builder.like(
						builder.lower(root.get(ProductConstants.FIELD_NAME)), 
						"%"+key+"%"
					)
				)
			));
		
		List<ProductClassificationItem> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public Optional<ProductClassificationItem> getUndeletedProductClassificationItemByCode(
			Integer classificationId, Integer code) {

		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductClassificationItem> criteria = builder.createQuery(type);
		Root<ProductClassificationItem> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(ProductConstants.FIELD_PRODUCT_CLASSIFICATION)
							.get(ProductConstants.FIELD_PRODUCT_CLASS_ID), classificationId),
					builder.equal(root.get(ProductConstants.FIELD_CODE), code), 
					builder.equal(root.get(ProductConstants.FIELD_IS_END), false)
				)
			);
		
		Optional<ProductClassificationItem> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;


	}

}