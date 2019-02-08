package w.fujiko.dao.repo.transactions.quotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.transactions.quotations.QuotationHeaderDao;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.masters.customers.CustomerBase_;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.properties.Property;
import w.fujiko.model.masters.properties.Property_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.model.transactions.quotations.QuotationHeader_;

@Repository
@Transactional
public class QuotationHeaderDaoRepo 
	extends UniversalCrudRepo<QuotationHeader, Integer> 
	implements QuotationHeaderDao {
	
	public QuotationHeaderDaoRepo() {
		super();
		type = QuotationHeader.class;
	}

	@Override
	public List<QuotationHeader> get() {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<QuotationHeader> criteria = builder.createQuery(type);
		Root<QuotationHeader> root = criteria.from(QuotationHeader.class);
		criteria
		.select(root)
		.orderBy(
			builder.desc(
				root.get(QuotationHeader_.dateCreated)
			)
		);	
		List<QuotationHeader> header = session.createQuery(criteria).getResultList();
		session.close();
		return header;
	}

	@Override
	public Optional<QuotationHeader> getBySlipNumber(String slipNumber) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<QuotationHeader> criteria = builder.createQuery(type);
		Root<QuotationHeader> root = criteria.from(type);

		criteria.select(root)
			.where(
				builder.equal(root.get(QuotationHeader_.slipNumber), slipNumber)
			);

		Optional<QuotationHeader> result;
	
		try {
			result = Optional.of(session.createQuery(criteria).getSingleResult());
		}catch(NoResultException nre) {
			result = Optional.empty();
		}finally{
			session.close();
		}
		
		return result;
	}
	
	@Override
	public List<QuotationHeader> getByConstructionNumber(String constructionNumber) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<QuotationHeader> criteria = builder.createQuery(type);
		Root<QuotationHeader> root = criteria.from(QuotationHeader.class);
		criteria.select(root)
		.where(
				builder.equal(root.get(QuotationHeader_.constructionNumber), constructionNumber)
			);
	
		List<QuotationHeader> header = session.createQuery(criteria).getResultList();
		session.close();
		return header;
	}

	@Override
	public List<QuotationHeader> getConstructionList(Date quotationDateFrom,
													 Date quotationDateTo,
													 Integer userId,
													 Integer customerBaseId,
													 Optional<Integer> propertyId,
													 Optional<List<Integer>> quotationRank,
													 Optional<String> constructionName) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<QuotationHeader> criteria = builder.createQuery(QuotationHeader.class);
		Root<QuotationHeader> root = criteria.from(QuotationHeader.class);
		Join<QuotationHeader,User> joinUser = root.join(QuotationHeader_.user);
		Join<QuotationHeader,CustomerBase> joinCustomerBase = root.join(QuotationHeader_.customerBase);
		Join<QuotationHeader,Property> joinProperty = root.join(QuotationHeader_.workingSite);

		var condition = builder.and(builder.between(root.get(QuotationHeader_.quotationDate), quotationDateFrom, quotationDateTo)
								   ,builder.equal(joinUser.get(User_.id), userId)
								   ,builder.equal(joinCustomerBase.get(CustomerBase_.id), customerBaseId));
		
		if(propertyId.isPresent())
			condition = builder.and(condition, builder.equal(joinProperty.get(Property_.id), propertyId.get()));

		Predicate quotationRankCondition=null;
		if(quotationRank.isPresent()){
			var quotationRanks = quotationRank.get();
			for(var itemCount = 0;itemCount<quotationRanks.size();itemCount++){
				if(quotationRankCondition == null)
					quotationRankCondition = builder.equal(root.get(QuotationHeader_.quotationRank),quotationRanks.get(itemCount));
				else
					quotationRankCondition = builder.or(quotationRankCondition, builder.equal(root.get(QuotationHeader_.quotationRank),quotationRanks.get(itemCount)));
			}
		}

		if(quotationRankCondition!=null) 
			condition = builder.and(condition, quotationRankCondition);
		if(constructionName.isPresent()){
			condition = builder.and(condition, builder.like(
							builder.lower(root.get(QuotationHeader_.constructionName)), 
							constructionName.get().toLowerCase()+"%"
						));
		}
		criteria.select(root)				
				.where(					
					builder.and(condition)
				);			
		
		try {			
			return session.createQuery(criteria).getResultList();
		}catch(Exception ex){
			ex.printStackTrace();
			return new ArrayList<>();
		}
		finally{
			session.close();
		}			
	}

	@Override
	public Page<QuotationHeader> quotationHeaderSearch(final Optional<Integer> workingSiteNumberFrom,
														final Optional<Integer> workingSiteNumberTo,
														final Optional<Integer> constructionNumberFrom,
														final Optional<Integer> constructionNumberTo,
														final Optional<Integer> constructionCategoryFrom,
														final Optional<Integer> constructionCategoryTo,
														final Optional<Integer> customerBaseCodeFrom,
														final Optional<Integer> customerBaseCodeTo,											
														final Optional<Short> userCodeFrom,
														final Optional<Short> userCodeTo,
														final Optional<Integer> departmentCodeFrom,
														final Optional<Integer> departmentCodeTo,
														final Optional<Date> quotationDateFrom,
														final Optional<Date> quotationDateTo,
														final Optional<Date> deliveryDateFrom,
														final Optional<Date> deliveryDateTo,
														final Optional<Date> createdDateFrom,
														final Optional<Date> createdDateTo,
														final Optional<Double> totalAmountFrom,
														final Optional<Double> totalAmountTo,	
														final Optional<Double> grossMarginRatioFrom,
														final Optional<Double> grossMarginRatioTo,											
														final Optional<List<Integer>> quotationRank,
														final Optional<List<Byte>> orderStatus,
														final Optional<List<Byte>> purchaseStatus,
														final Optional<List<Byte>> salesStatus,
														final Optional<List<Byte>> billingStatus,
														final Optional<List<Byte>> paymentStatus,
														final int first,
                                                		final int max) {

		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<QuotationHeader> criteria = builder.createQuery(QuotationHeader.class);
		Root<QuotationHeader> root = criteria.from(QuotationHeader.class);
		root.alias("head");
		Join<QuotationHeader,Property> joinworkingSite = root.join(QuotationHeader_.workingSite);
		joinworkingSite.alias("site");
		Join<QuotationHeader,CustomerBase> joinCustomerBase = root.join(QuotationHeader_.customerBase);
		joinCustomerBase.alias("base");
		Join<QuotationHeader,User> joinUser = root.join(QuotationHeader_.user);	
		joinUser.alias("_user");
		Join<QuotationHeader,Department> joinDepartment = root.join(QuotationHeader_.department);
		joinDepartment.alias("dept");
		var predicate = builder.conjunction();

		if(workingSiteNumberFrom.isPresent() && workingSiteNumberTo.isPresent()) {
			
			predicate = builder.and(
							predicate,
							builder.between(joinworkingSite.get(Property_.propertyNo), Integer.valueOf(workingSiteNumberFrom.get()),Integer.valueOf(workingSiteNumberTo.get()))
						);
		}
		if(constructionNumberFrom.isPresent() && constructionNumberTo.isPresent()) {
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeader_.constructionNumber), String.valueOf(constructionNumberFrom.get()),String.valueOf(constructionNumberTo.get()))
						);
		}
		if(constructionCategoryFrom.isPresent() && constructionCategoryTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeader_.constructionCategory), Integer.valueOf(constructionCategoryFrom.get()),Integer.valueOf(constructionCategoryTo.get()))
						);
		}
		if(customerBaseCodeFrom.isPresent() && customerBaseCodeTo.isPresent()){			
			predicate = builder.and(
							predicate,
							builder.between(joinCustomerBase.get("code"), customerBaseCodeFrom.get(),customerBaseCodeTo.get())
						);
		}
		if(userCodeFrom.isPresent() && userCodeTo.isPresent()){
			
			predicate = builder.and(
							predicate,
							builder.between(joinUser.get(User_.code), userCodeFrom.get(),userCodeTo.get())
						);
		}
		if(departmentCodeFrom.isPresent() && departmentCodeTo.isPresent()){
			
			predicate = builder.and(
							predicate,
							builder.between(joinDepartment.get("code"), departmentCodeFrom.get(),departmentCodeTo.get())
						);
		}
		if(quotationDateFrom.isPresent() && quotationDateTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeader_.quotationDate), quotationDateFrom.get(),quotationDateTo.get())
						);
		}
		if(deliveryDateFrom.isPresent() && deliveryDateTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeader_.deliveryDate), deliveryDateFrom.get(),deliveryDateTo.get())
						);
		}
		if(createdDateFrom.isPresent() && createdDateTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeader_.dateCreated), createdDateFrom.get(),createdDateTo.get())
						);
		}
		if(totalAmountFrom.isPresent() && totalAmountTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeader_.quotationTotalAmount), totalAmountFrom.get(),totalAmountTo.get())
						);
		}
		if(grossMarginRatioFrom.isPresent() && grossMarginRatioTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeader_.grossMarginRatio), grossMarginRatioFrom.get(),grossMarginRatioTo.get())
						);
		}
		
		if(quotationRank.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeader_.quotationRank).in(quotationRank.get())
						);
		}

		if(orderStatus.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeader_.poStatus).in(orderStatus.get())
						);
		}

		if(purchaseStatus.isPresent()){
				predicate = builder.and(
								predicate,
								root.get(QuotationHeader_.purchaseStatus).in(purchaseStatus.get())
						);
		}

		if(salesStatus.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeader_.salesStatus).in(salesStatus.get())
						);
		}

		if(billingStatus.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeader_.billingStatus).in(billingStatus.get())
						);
		}

		if(paymentStatus.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeader_.paymentStatus).in(paymentStatus.get())
						);

		}

		criteria
		.select(root)
		.where(predicate)
		.orderBy(
			builder.desc(
				root.get(QuotationHeader_.dateCreated)
			)
		);	
	
		var page = new Page<QuotationHeader>(); 
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

			
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		finally{
			session.close();
		}		
		return page;	
	}
	
	@Override
	public Long getCountByWorkingSiteId(Integer id) {
		Session session = this.getSessionFactory();

		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("select count(*) from QuotationHeader qh where qh.workingSite.id=:id");
		query.setParameter("id", id);
		Long count = (Long)query.uniqueResult();
		
		return count;
	}

	@Override
	public void deleteById(Integer quotationHeaderId) {
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<QuotationHeader> criteria = builder.createCriteriaDelete(QuotationHeader.class);
		Root<QuotationHeader> root = criteria.from(QuotationHeader.class);
		
		criteria.where(
				builder.equal(root.get("id"), quotationHeaderId)
			);
		
		try {
			transaction.begin();
			session.createQuery(criteria).executeUpdate();
			transaction.commit();
		} catch(ConstraintViolationException ex) {
			transaction.rollback();
		}
		finally{
			session.close();
		}
	}

}