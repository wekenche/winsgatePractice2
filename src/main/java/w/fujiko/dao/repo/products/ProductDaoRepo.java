package w.fujiko.dao.repo.products;

import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.products.ProductDao;
import w.fujiko.model.masters.products.Product;
import w.fujiko.model.masters.products.Product_;
import w.fujiko.model.masters.Maker;
import w.fujiko.model.masters.Maker_;

@Repository
@Transactional
public class ProductDaoRepo extends UniversalCrudRepo<Product, Integer>
	implements ProductDao {
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_MAKER = "maker";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_MODEL_NUMBER = "modelNumber";
	private static final String FIELD_IS_END = "isEnd";
	
	public ProductDaoRepo() {
		super();
		type = Product.class;
	}
	
	@Override
	public List<Product> getProductsByModelNumberOrName(String key, Integer index) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);
		Root<Product> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.or(
					builder.like(
						builder.lower(root.get(FIELD_MODEL_NUMBER)), 
						"%"+key+"%"
					),
					builder.like(
						builder.lower(root.get(FIELD_NAME)), 
						"%"+key+"%"
					)
				)
			);
		
		List<Product> t = session.createQuery(criteria)
			.setFirstResult(index)
			.setMaxResults(30)
			.getResultList();
		
		session.close();
		return t;
	}

	@Override
	public Optional<Product> getUndeletedItemByModelNumber(String modelNumber, int makerId) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);
		Root<Product> root = criteria.from(type);
		
		criteria.select(root)
			.where(
				builder.and(
					builder.equal(root.get(FIELD_MAKER).get(FIELD_ID), makerId),
					builder.equal(root.get(FIELD_MODEL_NUMBER), modelNumber), 
					builder.equal(root.get(FIELD_IS_END), false)
				)
			);
		
		Optional<Product> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			result = Optional.empty();
		}
		session.close();
		
		return result;

	}
	
	@Override
	public Product getLastItem() {
		@SuppressWarnings("unchecked")
		Query<Product> query = this.getSessionFactory().createQuery("from Product order by code DESC");
		query.setMaxResults(1);
		Product product = (Product) query.uniqueResult();
		return product;
	}

	@Override
	public void batchSave(List<Product> products) throws ConstraintViolationException, PersistenceException {
		int batchSize = 25;
		int size = products.size();
		
		Session session = this.getSessionFactory();
		Transaction tx = session.getTransaction();

		try {
			tx.begin();
			for(int i = 0; i < size; i++) {
				session.saveOrUpdate(products.get(i));
				if (i > 0 && i % batchSize == 0) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		} catch(ConstraintViolationException cve) {
			tx.rollback();
			throw cve;
		} finally {
			session.close();
		}
	}

	@Override
	public List<Product> getByMakerId(Integer makerId) {
		return getByMakerId(makerId, null);
	}
	
	@Override
	public List<Product> getByMakerId(Integer makerId, Boolean isEnd) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);		
		Root<Product> root = criteria.from(type);
		Join<Product,Maker> makerJoin = root.join(Product_.maker);

		criteria.select(root);
		if(isEnd == null) {
			criteria.where(
					builder.and(					
						builder.equal(makerJoin.get(Maker_.id), makerId)
					)
				);
		} else {
			criteria.where(
					builder.and(					
						builder.equal(makerJoin.get(Maker_.id), makerId),
						builder.equal(root.get(Product_.isEnd), isEnd)
					)
				);
		}

		try {
			return session.createQuery(criteria).getResultList();
		} catch(NoResultException nre) {
			return new ArrayList<Product>();
		}finally {
			session.close();
		}		
	}

	@Override
	public List<Product> getByMakerIdWithModelNumberSearch(Integer makerId,String modelNumber) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);		
		Root<Product> root = criteria.from(type);
		Join<Product,Maker> makerJoin = root.join(Product_.maker);

		criteria.select(root)
			.where(
				builder.and(	
					builder.equal(makerJoin.get(Maker_.id), makerId),
					builder.like(builder.lower(root.get(Product_.modelNumber)),modelNumber+"%")
				)
			);

		try {
			return session.createQuery(criteria).getResultList();
		} catch(NoResultException nre) {
			return new ArrayList<Product>();
		}finally {
			session.close();
		}		
	}

	@Override
	public Page<Product> getByMakerWithModelNumberSearch(Integer makerId, String modelNumber, int first, int max, Optional<Boolean> isEnd) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);		
		Root<Product> root = criteria.from(type);
		root.alias("prod");
		Join<Product,Maker> makerJoin = root.join(Product_.maker);
		makerJoin.alias("maker");
		var condition = builder.and(	
							builder.equal(
								makerJoin.get(Maker_.id), makerId
							),
							builder.like(
								builder.lower(root.get(Product_.modelNumber)
							),modelNumber.toLowerCase()+"%")
						);
		if(isEnd.isPresent()) {
			condition = builder.and(
							condition,
							builder.equal(
								root.get(Product_.isEnd),
								isEnd.get()
							)
					    );
		}
		criteria.select(root).where(condition);
		criteria.orderBy(builder.desc(root.get(Product_.code)));

		Page<Product> page = new Page<Product>(); 
		
		try {
			var rowCount = this.getRowCount(criteria,builder,root);
			page.setTotal(rowCount);			
			page.setSuccess(true);
			page.setFirst(first);
			page.setMax(max);
			page.setResults(session
							.createQuery(criteria)
							.setFirstResult(first)
							.setMaxResults(max)
							.getResultList());				
			return page;		
		} catch(NoResultException nre) {
			return page;
		}finally {
			session.close();
		}	
	}

	@Override
	public Page<Product> getExceptByMakerName(List<String> makerNames,String modelNumber,Optional<Boolean> isEnd,int first,int max) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);		
		Root<Product> root = criteria.from(type);
		root.alias("prod");
		Join<Product,Maker> makerJoin = root.join(Product_.maker);
		makerJoin.alias("maker");
		
		makerNames.forEach(e->e = e.toLowerCase());		
		var predicate =	builder.like(
								builder.lower(
									builder.concat(
										builder.concat(
											makerJoin.get(Maker_.name),
											" "
										),
										root.get(Product_.modelNumber)
									)
								),
								"%"+modelNumber.toLowerCase().trim()+"%"
						);

		predicate = builder
					.and(
						predicate,
						builder.not(
							builder.lower(makerJoin.get(Maker_.name))
								   .in(makerNames)
							)
					);

		if(isEnd.isPresent()){
			predicate = builder.and(
							predicate,
							builder.equal(
								root.get(Product_.isEnd),
								isEnd.get()
							)
						);
		}

		criteria
		.select(root)
		.where(predicate);
		
		criteria.orderBy(builder.desc(root.get(Product_.code)));

		Page<Product> page = new Page<Product>(); 
		
		try {
			var rowCount = this.getRowCount(criteria,builder,root);
			page.setTotal(rowCount);			
			page.setSuccess(true);
			page.setFirst(first);
			page.setMax(max);
			page.setResults(session
							.createQuery(criteria)
							.setFirstResult(first)
							.setMaxResults(max)
							.getResultList());				
			return page;		
		} catch(NoResultException nre) {
			return page;
		}finally {
			session.close();
		}	
	}

	@Override
	public Page<Product> paginate(int first, int max, Optional<Boolean> flagValue, Optional<String> modelNumber) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);
		Root<Product> root =  criteria.from(type);
		root.alias("prod");
		var predicate = builder.conjunction();
		
		if(flagValue.isPresent()) {
			predicate = builder.and(predicate,builder.equal(root.get(Product_.isEnd), flagValue.get()));
		}
		
		if(modelNumber.isPresent()) {
			predicate = builder.and(predicate,builder.like(
													builder.lower(root.get(Product_.modelNumber)
												),modelNumber.get().toLowerCase()+"%"));
		}

		criteria.select(root)
				.where(predicate);
		
		Page<Product> page = new Page<Product>(); 
		
		var rowCount = this.getRowCount(criteria,builder,root);
		
		page.setSuccess(true);
		page.setFirst(first);
		page.setMax(max);
		page.setResults(session.createQuery(criteria).setFirstResult(first).setMaxResults(max).getResultList());
		page.setTotal(rowCount);
		session.close();
		return page;
	}

	@Override
	public Optional<Product> getByModelNumber(String modelNumber) {
		
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);
		Root<Product> root = criteria.from(type);
		
 		criteria.select(root)
				.where(builder.equal(
							builder.lower(builder.trim(root.get(Product_.modelNumber))),
							modelNumber.trim().toLowerCase()
					)
				);
		
		Optional<Product> result;
		
		try {
			result = Optional.of(session.createQuery(criteria).setFirstResult(0).setMaxResults(1).getSingleResult());
		} catch(NoResultException nre) {
			nre.printStackTrace();
			result = Optional.empty();
		}
		session.close();
		
		return result;
	}
	
	@Override
	public Page<Product> search(Optional<Boolean> specialOrderClassificationFlag,
							   Optional<Boolean> abolishedNumberFlag,
							   Optional<Boolean> isEnd,  
							   Optional<String> makerName,
							   Optional<Integer> makerCode,
							   Optional<String> modelNumber,
							   int first,
							   int max) {

		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(type);
		Root<Product> root =  criteria.from(type);
		root.alias("prod");
		var makerJoin = root.join(Product_.maker);		
		makerJoin.alias("maker");

		var predicate = builder.conjunction();
		
		if(specialOrderClassificationFlag.isPresent()) {
			predicate = builder.and(predicate,builder.equal(root.get(Product_.specialOrderClassification), specialOrderClassificationFlag.get()));
		}

		if(abolishedNumberFlag.isPresent()) {
			predicate = builder.and(predicate,builder.equal(root.get(Product_.abolishedNumberFLG), abolishedNumberFlag.get()));
		}

		if(isEnd.isPresent()) {
			predicate = builder.and(predicate,builder.equal(root.get(Product_.isEnd), isEnd.get()));
		}
		
		if(makerName.isPresent()) {
			predicate = builder.and(
								predicate,
								builder.equal(
									builder.lower(makerJoin.get(Maker_.name)),
									makerName.get().toLowerCase()
								)
						);
		}
		
		if(makerCode.isPresent()) {
			predicate = builder.and(predicate,
								builder.equal(makerJoin.get(Maker_.code),
								makerCode.get())
						);
		}

		if(modelNumber.isPresent()) {
			predicate = builder.and(
							predicate,
							builder.like(
								builder.lower(
									builder.concat(
										builder.concat(
											makerJoin.get(Maker_.name),
											" "
										),
										root.get(Product_.modelNumber)
									)
								),
								"%"+modelNumber.get().toLowerCase().trim()+"%"
							)
						);
		}

		criteria.select(root)
				.where(predicate);
		
		Page<Product> page = new Page<Product>(); 
		
		var rowCount = this.getRowCount(criteria,builder,root);
		
		page.setSuccess(true);
		page.setFirst(first);
		page.setMax(max);
		page.setResults(session.createQuery(criteria).setFirstResult(first).setMaxResults(max).getResultList());
		page.setTotal(rowCount);
		session.close();
		return page;
	}

}