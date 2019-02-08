package w.fujiko.dao.repo.transactions.purchaseorders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import fte.api.Page;
import fte.api.universal.UniversalCrudRepo;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderHeaderDao;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderSearchDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader_;

@Repository
@Transactional
public class PurchaseOrderHeaderDaoRepo 
	extends UniversalCrudRepo<PurchaseOrderHeader, Integer> 
	implements PurchaseOrderHeaderDao {
	
	public PurchaseOrderHeaderDaoRepo() {
		super();
		type = PurchaseOrderHeader.class;
	}

	@Override
	public Optional<PurchaseOrderHeader> getBySlipNumber(String slipNumber) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PurchaseOrderHeader> criteria = builder.createQuery(type);
		Root<PurchaseOrderHeader> root = criteria.from(type);

		criteria.select(root)
			.where(
				builder.equal(root.get(PurchaseOrderHeader_.slipNumber), slipNumber)
			);

		Optional<PurchaseOrderHeader> result;
	
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
	public void deleteById(Integer purchaseOrderHeaderId) {
		Session session = this.getSessionFactory();
		Transaction transaction = session.getTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaDelete<PurchaseOrderHeader> criteria = builder.createCriteriaDelete(PurchaseOrderHeader.class);
		Root<PurchaseOrderHeader> root = criteria.from(PurchaseOrderHeader.class);
		
		criteria.where(
				builder.equal(root.get(PurchaseOrderHeader_.id), purchaseOrderHeaderId)
			);
		
		try {
			transaction.begin();			
			session.createQuery(criteria).executeUpdate();			
			transaction.commit();			
		} catch(ConstraintViolationException ex) {
			ex.printStackTrace();
			transaction.rollback();
		}
		finally{
			session.close();
		}
		
	}

	@Override
	public Page<PurchaseOrderHeader> purchaseOrderHeaderSearch(PurchaseOrderHeaderSearchDTO searchKeys) {
		Session session = this.getSessionFactory();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PurchaseOrderHeader> criteria = builder.createQuery(type);
		Root<PurchaseOrderHeader> root = criteria.from(type);
		
		List<Predicate> restrictions = new ArrayList<>();
		
		
		Integer workingSiteNo = searchKeys.getWorkingSiteNo();
		if(workingSiteNo != null) {
			restrictions.add(builder.equal(root.get("quotationHeader").get("workingSite").get("propertyNo"), workingSiteNo));
		}
		
		String workingSiteName = searchKeys.getWorkingSiteName();
		if(workingSiteName != null && !workingSiteName.equals("")) {
			restrictions.add(
				builder.like(
					builder.lower(root.get("quotationHeader").get("workingSite").get("propertyName")), 
					"%" + workingSiteName + "%"
				)
			);
		}
		
		String constructionNo = searchKeys.getConstructionNo();
		if(constructionNo != null) {
			restrictions.add(builder.equal(root.get("quotationHeader").get("constructionNumber"), constructionNo));
		}
		
		String constructionName = searchKeys.getConstructionName();
		if(constructionName != null && !constructionName.equals("")) {
			restrictions.add(
				builder.like(
					builder.lower(root.get("quotationHeader").get("constructionName")), 
					"%" + constructionName + "%"
				)
			);
		}
		
		/* Order Status Conditions */
		List<Predicate> orderStatusRestrictions = new ArrayList<>();
		List<Byte> orderStatus = searchKeys.getOrderStatus();
		if(orderStatus != null && orderStatus.size() > 0) {
			for(Byte status: orderStatus) {
				orderStatusRestrictions.add(builder.equal(root.get("orderStatus"), status));
			}
			restrictions.add(builder.or(orderStatusRestrictions.toArray(new Predicate[orderStatusRestrictions.size()])));
		}
		
		/* Sales Status Conditions */
		List<Predicate> salesStatusRestrictions = new ArrayList<>();
		List<Byte> salesStatus = searchKeys.getSalesStatus();
		if(salesStatus != null && salesStatus.size() > 0) {
			for(Byte status: salesStatus) {
				salesStatusRestrictions.add(builder.equal(root.get("salesStatus"), status));
			}
			restrictions.add(builder.or(salesStatusRestrictions.toArray(new Predicate[salesStatusRestrictions.size()])));
		}
		
		String supplierCode = searchKeys.getSupplierCode();
		if(supplierCode != null && !supplierCode.equals("")) {
			restrictions.add(builder.equal(root.get("supplierBase").get("code"), supplierCode));
		}
		
		String supplierName = searchKeys.getSupplierName();
		if(supplierName != null && !supplierName.equals("")) {
			restrictions.add(
				builder.like(
					builder.lower(root.get("supplierBase").get("name")), "%" + supplierName.toLowerCase() + "%"
					)
				);
		}
		
		Date orderDateFrom = searchKeys.getOrderDateFrom();
		if(orderDateFrom != null) {
			restrictions.add(
				builder.greaterThanOrEqualTo(
					root.get("purchaseOrderDate"), orderDateFrom));
		}
		
		Date orderDateTo = searchKeys.getOrderDateTo();
		if(orderDateTo != null) {
			restrictions.add(
				builder.lessThanOrEqualTo(
					root.get("purchaseOrderDate"), orderDateTo));
		}
		
		Short userCode = searchKeys.getUserCode();
		if(userCode != null) {
			restrictions.add(builder.equal(root.get("user").get("code"), userCode));
		}
		
		String username = searchKeys.getUsername();
		if(username != null && !username.equals("")) {
			restrictions.add(
				builder.like(
					builder.lower(root.get("user").get("username")), "%" + username.toLowerCase() + "%"
					)
				);
		}
		
		String departmentCode = searchKeys.getDepartmentCode();
		if(departmentCode != null && !departmentCode.equals("")) {
			restrictions.add(builder.equal(root.get("department").get("code"), departmentCode));
		}
		
		String departmentName = searchKeys.getDepartmentName();
		if(departmentName != null && !departmentName.equals("")) {
			restrictions.add(
				builder.like(
					builder.lower(root.get("department").get("name")), "%" + departmentName.toLowerCase() + "%"
					)
				);
		}
		
		String slipNum = searchKeys.getSlipNum();
		if(slipNum != null && !slipNum.equals("")) {
			restrictions.add(builder.equal(root.get("quotationHeader").get("slipNumber"), slipNum));
		}
		
		Boolean secureFlg = searchKeys.getSecureFlg();
		if(secureFlg != null) {
			restrictions.add(builder.equal(root.get("secureFlg"), secureFlg));
		}
		
		criteria.select(root);
		if(restrictions.size() > 0) {
			criteria.where(builder.and(restrictions.toArray(new Predicate[restrictions.size()])));
		}
		criteria.orderBy(builder.desc(root.get("dateCreated")));
		
		List<PurchaseOrderHeader> result = session.createQuery(criteria)
				.setFirstResult(searchKeys.getFirst())
				.setMaxResults(searchKeys.getMax())
				.getResultList();
		
		CriteriaQuery<Long> total = builder.createQuery(Long.class);
		total.select(builder.count(total.from(type)));
		if(restrictions.size() > 0) {
			total.where(builder.and(restrictions.toArray(new Predicate[restrictions.size()])));
		}
			
		Page<PurchaseOrderHeader> page = new Page<PurchaseOrderHeader>();
		page.setSuccess(true);
		page.setFirst(searchKeys.getFirst());
		page.setMax(searchKeys.getMax());
		page.setResults(result);
		page.setTotal(session.createQuery(total).getSingleResult());
		session.close();
		return page;
		
	}

}