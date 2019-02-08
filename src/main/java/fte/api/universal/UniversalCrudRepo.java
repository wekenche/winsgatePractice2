/**
 * 
 */
package fte.api.universal;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fte.api.Page;


/**
 * @author Try-Parser
 *
 */
@Repository
@Transactional
public class UniversalCrudRepo<T extends Serializable, I extends Serializable> implements UniversalCrud<T, I> {

	public Class<T> type;

	@Autowired SessionFactory sessionFactory;    

	public Class<T> getType() {
        return type;
    }
	
	public Session getSessionFactory() {
		return sessionFactory.openSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#paginate(java.lang.Long, java.lang.Long)
	 * @param first offset of the Page.
	 * @param max maximum return length of the Page.
	 * @return Page<T> paginated serialize object.
	 * @see fte.api.Page
	 */
	@Override
	public Page<T> paginate(int first, int max) {
		return paginate(first, max, "isEnd", null);
	}
	
	@Override
	public Page<T> paginate(int first, int max, Boolean flagValue) {
		return paginate(first, max, "isEnd", flagValue);
	}
	
	@Override
	public Page<T> paginate(int first, int max, String flagField) {
		return paginate(first, max, flagField, null);
	}
	
	@Override
	public Page<T> paginate(int first, int max, String flagField, Boolean flagValue) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		Root<T> root =  criteria.from(type);
		criteria.select(root);
		if(flagValue != null) {
			criteria.where(builder.equal(root.get(flagField), flagValue));
		}
		Page<T> page = new Page<T>(); 
		
		CriteriaQuery<Long> total = builder.createQuery(Long.class);
		total.select(builder.count(total.from(type)));
		if(flagValue != null) {
			total.where(builder.equal(root.get(flagField), flagValue));
		}
		
		page.setSuccess(true);
		page.setFirst(first);
		page.setMax(max);
		page.setResults(session.createQuery(criteria).setFirstResult(first).setMaxResults(max).getResultList());
		page.setTotal(session.createQuery(total).getSingleResult());
		session.close();
		return page;
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#get()
	 * @return List<T> list of serialize object.
	 */
	@Override
	public List<T> get() {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		Root<T> root = criteria.from(type);
		criteria.select(root);
		List<T> t = session.createQuery(criteria).getResultList();
		session.close();
		return t;
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#get(java.lang.Object)
	 * @param id serializable type.
	 * @return Optional<T> optional serialize object. 
	 */
	@Override
	public Optional<T> get(I id) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		Root<T> root = criteria.from(type);
		
		criteria
		.select(root)
		.where(builder.equal(root.get("id"), id));
		
		Optional<T> single;
		
		try {
			single = Optional.of(session.createQuery(criteria).getSingleResult());
		} catch(NoResultException nre) {
			single = Optional.empty();
		}
		session.close();
		
		return single;
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#saveOrUpdate()
	 * @param data serialize object.

	 */
	@Override
	public void saveOrUpdate(T data) throws ConstraintViolationException,PersistenceException{
		Session session = this.getSessionFactory();
		Transaction tx = session.getTransaction();

		try {
			tx.begin();
			session.saveOrUpdate(data);
			tx.commit();
		} catch(ConstraintViolationException cve) {			
			tx.rollback();
			throw cve;
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#disable(java.lang.Boolean)
	 * @param flag Boolean
	 * @param id Generic
	 */
	@Override
	public void delete(T data) throws ConstraintViolationException,PersistenceException {		
		Session session = this.getSessionFactory();
		Transaction tx = session.getTransaction();

		try {
			tx.begin();
			session.delete(data);
			tx.commit();
		} catch(ConstraintViolationException cve) {			
			tx.rollback();
			throw cve;
		}finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see fte.api.UniversalCRUD#disable(java.lang.Boolean)
	 * @param flag Boolean
	 * @param id Generic
	 * @return Response List
	 * @see fte.api.Response
	 */
	@Override
	public void disable(Boolean flag, I id) throws Exception {		
		Session session = this.getSessionFactory();
		Transaction tx = session.getTransaction();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<T> criteria = builder.createCriteriaUpdate(type);
		
		Root<T> root = criteria.from(type); 
		criteria
		.set(root.get("isEnd"), flag)
		.where(builder.equal(root.get("id"), id));
		
		try {
			tx.begin();
			session.createQuery(criteria).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	/* (non-Javadoc)
	 * Gets the row count of a query
	 * @see fte.api.UniversalCRUD#getRowCount(java.lang.Boolean)
	 * @param CriteriaQuery Boolean
	 * @param CriteriaBuilder Generic
	 * @param Root Generic
	 * @return Row count
	 */
	public Long getRowCount(CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder,Root<?> root){
		var session = this.getSessionFactory();
		CriteriaQuery<Long> countCriteria = criteriaBuilder.createQuery(Long.class);
		Root<?> entityRoot = countCriteria.from(root.getJavaType());
		entityRoot.alias(root.getAlias());
		doJoins(root.getJoins(),entityRoot);
		countCriteria.select(criteriaBuilder.count(entityRoot));
		countCriteria.where(criteriaQuery.getRestriction());
		return session.createQuery(countCriteria).getSingleResult();
	}
	
	/**
	 * Join using root's join
	 * @param joins
	 * @param root_
	 */
	private void doJoins(Set<? extends Join<?, ?>> joins,Root<?> root_){
		joins.forEach(e->{
			Join<?,?> joined = root_.join(e.getAttribute().getName(),e.getJoinType());
			joined.alias(e.getAlias());
			doJoins(e.getJoins(), joined);
		});		
	}
	
	/**
	 * Deep join
	 * @param joins
	 * @param root_
	 */
	private void doJoins(Set<? extends Join<?, ?>> joins,Join<?,?> root_){
		joins.forEach(e->{
			Join<?,?> joined = root_.join(e.getAttribute().getName(),e.getJoinType());
			joined.alias(e.getAlias());
			doJoins(e.getJoins(),joined);
		});	
	}

}
