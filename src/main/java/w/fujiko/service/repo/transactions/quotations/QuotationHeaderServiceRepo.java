package w.fujiko.service.repo.transactions.quotations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.Page;
import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.quotations.QuotationDetailDao;
import w.fujiko.dao.transactions.quotations.QuotationHeaderDao;
import w.fujiko.model.masters.branches.Branch;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.masters.customers.CustomerGroup;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.properties.Property;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.quotations.QuotationDetail;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.model.transactions.quotations.jasperdata.Quotation;
import w.fujiko.model.transactions.quotations.jasperdata.QuotationWrapper;
import w.fujiko.service.transactions.quotations.QuotationHeaderService;
import w.fujiko.util.common.generator.jasperpdf.JasperReportDataManager;

@Service
@Transactional
public class QuotationHeaderServiceRepo extends UniversalServiceRepo<QuotationHeader, QuotationHeaderDao, Integer>
		implements QuotationHeaderService {
	
	@Autowired QuotationDetailDao quotationDetailDao;

	@Override
	public Optional<QuotationHeader> getBySlipNumber(String slipNumber) {
		return dao.getBySlipNumber(slipNumber);
	}

	@Override
	public Long getCountByWorkingSiteId(Integer id) {
		return dao.getCountByWorkingSiteId(id);
	}

	@Override
	public List<QuotationHeader> getConstructionList(Date quotationDateFrom, 
													 Date quotationDateTo,
													 Integer userId,
													 Integer customerBaseId,
													 Optional<Integer> propertyId,
													 Optional<List<Integer>> quotationRank,
													 Optional<String> constructionName) {
		
		return dao.getConstructionList(quotationDateFrom,
									   quotationDateTo,
									   userId,
									   customerBaseId,
									   propertyId,
									   quotationRank,
									   constructionName);
	}

	@Override
	public Page<QuotationHeader> quotationHeaderSearch(Optional<Integer> workingSiteNumberFrom,
			Optional<Integer> workingSiteNumberTo, Optional<Integer> constructionNumberFrom,
			Optional<Integer> constructionNumberTo, Optional<Integer> constructionCategoryFrom,
			Optional<Integer> constructionCategoryTo, Optional<Integer> customerBaseCodeFrom,
			Optional<Integer> customerBaseCodeTo, Optional<Short> userCodeFrom, Optional<Short> userCodeTo,
			Optional<Integer> departmentCodeFrom, Optional<Integer> departmentCodeTo, Optional<Date> quotationDateFrom,
			Optional<Date> quotationDateTo, Optional<Date> deliveryDateFrom, Optional<Date> deliveryDateTo,
			Optional<Date> createdDateFrom, Optional<Date> createdDateTo, Optional<Double> totalAmountFrom,
			Optional<Double> totalAmountTo,final Optional<Double> grossMarginRatioFrom,
			final Optional<Double> grossMarginRatioTo, Optional<List<Integer>> quotationRank, Optional<List<Byte>> orderStatus,
			Optional<List<Byte>> purchaseStatus, Optional<List<Byte>> salesStatus, Optional<List<Byte>> billingStatus,
			Optional<List<Byte>> paymentStatus,
			int first,
			int max) {
				
		return dao.quotationHeaderSearch(workingSiteNumberFrom,
										 workingSiteNumberTo,
										 constructionNumberFrom,
										 constructionNumberTo,
										 constructionCategoryFrom,
										 constructionCategoryTo,
										 customerBaseCodeFrom,
										 customerBaseCodeTo,
										 userCodeFrom,
										 userCodeTo,
										 departmentCodeFrom,
										 departmentCodeTo,
										 quotationDateFrom,
										 quotationDateTo,
										 deliveryDateFrom,
										 deliveryDateTo,
										 createdDateFrom,
										 createdDateTo,
										 totalAmountFrom,
										 totalAmountTo,
										 grossMarginRatioFrom,
										 grossMarginRatioTo,
										 quotationRank,
										 orderStatus,
										 purchaseStatus,
										 salesStatus,
										 billingStatus,
										 paymentStatus,
										 first,
										 max);
	}

	@Override
	public void generateReport(String constructionNumber, HttpServletResponse response) {
		JasperReportDataManager jrdm = new JasperReportDataManager();
		List<QuotationHeader> quotationHeaders = dao.getByConstructionNumber(constructionNumber);
		Map<String, Object> parameters = null;
		if(quotationHeaders != null && quotationHeaders.size() > 0) {
			parameters = constructReportData(quotationHeaders);
		}
		jrdm.generatePDF("/reports/quotations/quotation.jrxml", parameters, response);
	}
	
	private Map<String, Object> constructReportData(List<QuotationHeader> quotationHeaders) {

		// get the first quotation header
		QuotationHeader quotationHeader = quotationHeaders.get(0);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(Calendar.getInstance().getTime()));
		
		CustomerBase customerBase = quotationHeader.getCustomerBase();
		if(customerBase != null) {
			CustomerGroup customerGroup = customerBase.getGroup();
			String customerName = customerGroup.getName();
			customerName += customerBase.getName();
			customerName += "　御中";
			data.put("customerName", customerName);
		}

		data.put("constructionNumber", quotationHeader.getConstructionNumber());
		
		String propertyName = "";
		Property property = quotationHeader.getWorkingSite();
		if(property != null) {
			propertyName = property.getPropertyName();
		}
		data.put("propertyName", propertyName);
		data.put("postMessage", quotationHeader.getPostMessage());
		data.put("contactUsername", quotationHeader.getContactUserName());
		data.put("paymentTerms", quotationHeader.getPaymentTermsMessage());
		data.put("estimatedExpirationDate", quotationHeader.getEstimatedDeadlineMessage());
		data.put("deliveryDate", quotationHeader.getDeliveryDateMessage());
		data.put("deliveryPlace", quotationHeader.getDeliveryPlaceMessage());
		data.put("sparePartsCost", quotationHeader.getSparePartsCostMessage());

		User user = quotationHeader.getUser();
		if(user != null) {
			Department department = user.getDepartment();
			if(department != null) {
				Branch branch = department.getBranch();
				if(branch != null) {
					data.put("branchName", branch.getName());
				}
				data.put("postalCode", department.getPostalCode());
				data.put("address", department.getAddress1());
				data.put("telephone", department.getTelephoneNumber());
				data.put("fax", department.getFaxNumber());
			}
		}
		
		List<QuotationWrapper> details = createDetails(quotationHeaders);
		List<Quotation> headers = createHeaders(quotationHeaders, details);

		data.put("quotationHeaders", headers);
		data.put("quotationDetails", details);
		
		Double totalAmount = getTotalAmount(headers);
		Double taxRate = quotationHeader.getTaxRate();
		Double totalAmountWIthTax = totalAmount + (totalAmount * (taxRate / 100));
		
		data.put("totalAmount", totalAmount);
		
		/* Need to format the number here. Cannot be formatted in jrxml 
		 * because of the concatenated value in the text field */
		data.put("totalAmountWithTax", String.format("%,.0f", totalAmountWIthTax));
		
		return data;
	}
	
	private List<Quotation> createHeaders(
			List<QuotationHeader> quotationHeaders,
			List<QuotationWrapper> quotationDetails) {
		
		int size = quotationHeaders.size();
		
		List<Quotation> resultHeaders = new ArrayList<>();
		
		for(int i = 0; i < size; i++) {
			resultHeaders.add(
				new Quotation(
					null, 
					quotationHeaders.get(i).getConstructionCategoryName(), 
					1, 
					null,
					null, 
					getTotalAmount(quotationDetails.get(i).getQuotationItems()), 
					null, 
					null
				));
		}
		
		return resultHeaders;
	}
	
	private List<QuotationWrapper> createDetails(List<QuotationHeader> quotationHeaders) {
		List<QuotationWrapper> details = new ArrayList<>();
		for(QuotationHeader qh : quotationHeaders) {
			
			List<QuotationDetail> detailsPerHeader = quotationDetailDao.getByQuotationHeaderId(qh.getId());
			List<Quotation> quotationDetails = new ArrayList<>();
			
			for(QuotationDetail quotationDetail : detailsPerHeader) {
				quotationDetails.add(
					new Quotation(
						quotationDetail.getProductName(), 
						quotationDetail.getProduct().getMaker().getName() + " " + quotationDetail.getProduct().getModelNumber(), 
						quotationDetail.getQuantity(), 
						quotationDetail.getUnit(),
						quotationDetail.getUnitPrice(), 
						quotationDetail.getAmount(), 
						quotationDetail.getOriginalUnitPrice(), 
						quotationDetail.getRemarks()
					));
			}
			details.add(
				new QuotationWrapper(
					qh.getConstructionNumber(),
					qh.getWorkingSite().getPropertyName(),
					qh.getConstructionCategoryName(),
					getTotalAmount(quotationDetails),
					quotationDetails
				));
		}
		
		return details;
	}
	
	private Double getTotalAmount(List<Quotation> quotations) {
		return quotations.stream().mapToDouble(element -> {
			Double amount = element.getAmount();
			if(amount == null) {
				return 0;
			} else {
				return element.getAmount();
			}
		}).sum();
	}

}