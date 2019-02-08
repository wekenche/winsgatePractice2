package w.fujiko.dao.repo.transactions.quotations.histories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.transactions.quotations.histories.QuotationHeaderHistoryDao;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.properties.Property;
import w.fujiko.model.masters.properties.Property_;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.masters.users.User_;
import w.fujiko.model.transactions.quotations.histories.QuotationHeaderHistory;
import w.fujiko.model.transactions.quotations.histories.QuotationHeaderHistory_;

@Repository
@Transactional
public class QuotationHeaderHistoryDaoRepo 
	extends UniversalCrudRepo<QuotationHeaderHistory, Integer> 
	implements QuotationHeaderHistoryDao {
	
	public QuotationHeaderHistoryDaoRepo() {
		super();
		type = QuotationHeaderHistory.class;
	}

	@Override
	public Long getCountByWorkingSiteId(Integer id) {
		Session session = this.getSessionFactory();

		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("select count(*) from QuotationHeaderHistory qhh where qhh.workingSite.id=:id");
		query.setParameter("id", id);
		Long count = (Long)query.uniqueResult();
		
		return count;
	}

	@Override
	public Page<QuotationHeaderHistory> quotationHeaderSearch(final Optional<Integer> workingSiteNumberFrom,
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
		CriteriaQuery<QuotationHeaderHistory> criteria = builder.createQuery(QuotationHeaderHistory.class);
		Root<QuotationHeaderHistory> root = criteria.from(QuotationHeaderHistory.class);
		root.alias("head");
		Join<QuotationHeaderHistory,Property> joinworkingSite = root.join(QuotationHeaderHistory_.workingSite);
		joinworkingSite.alias("site");
		Join<QuotationHeaderHistory,CustomerBase> joinCustomerBase = root.join(QuotationHeaderHistory_.customerBase);
		joinCustomerBase.alias("base");
		Join<QuotationHeaderHistory,User> joinUser = root.join(QuotationHeaderHistory_.user);	
		joinUser.alias("_user");
		Join<QuotationHeaderHistory,Department> joinDepartment = root.join(QuotationHeaderHistory_.department);
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
							builder.between(root.get(QuotationHeaderHistory_.constructionNumber), String.valueOf(constructionNumberFrom.get()),String.valueOf(constructionNumberTo.get()))
						);
		}
		if(constructionCategoryFrom.isPresent() && constructionCategoryTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeaderHistory_.constructionCategory), Integer.valueOf(constructionCategoryFrom.get()),Integer.valueOf(constructionCategoryTo.get()))
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
							builder.between(root.get(QuotationHeaderHistory_.quotationDate), quotationDateFrom.get(),quotationDateTo.get())
						);
		}
		if(deliveryDateFrom.isPresent() && deliveryDateTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeaderHistory_.deliveryDate), deliveryDateFrom.get(),deliveryDateTo.get())
						);
		}
		if(createdDateFrom.isPresent() && createdDateTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeaderHistory_.dateCreated), createdDateFrom.get(),createdDateTo.get())
						);
		}
		if(totalAmountFrom.isPresent() && totalAmountTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeaderHistory_.quotationTotalAmount), totalAmountFrom.get(),totalAmountTo.get())
						);
		}
		if(grossMarginRatioFrom.isPresent() && grossMarginRatioTo.isPresent()){
			predicate = builder.and(
							predicate,
							builder.between(root.get(QuotationHeaderHistory_.grossMarginRatio), grossMarginRatioFrom.get(),grossMarginRatioTo.get())
						);
		}

		if(quotationRank.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeaderHistory_.quotationRank).in(quotationRank.get())
						);
		}

		if(orderStatus.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeaderHistory_.poStatus).in(orderStatus.get())
						);
		}

		if(purchaseStatus.isPresent()){
				predicate = builder.and(
								predicate,
								root.get(QuotationHeaderHistory_.purchaseStatus).in(purchaseStatus.get())
						);
		}

		if(salesStatus.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeaderHistory_.salesStatus).in(salesStatus.get())
						);
		}

		if(billingStatus.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeaderHistory_.billingStatus).in(billingStatus.get())
						);
		}

		if(paymentStatus.isPresent()){
			predicate = builder.and(
							predicate,
							root.get(QuotationHeaderHistory_.paymentStatus).in(paymentStatus.get())
						);

		}
	
		criteria.select(root).where(predicate);		
		var page = new Page<QuotationHeaderHistory>(); 
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

}
