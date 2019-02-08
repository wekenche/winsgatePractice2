package w.fujiko.service.repo.transactions.purchaseorders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fte.api.Page;
import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderDetailDao;
import w.fujiko.dao.transactions.purchaseorders.PurchaseOrderHeaderDao;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderSearchDTO;
import w.fujiko.model.masters.branches.Branch;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.model.masters.customers.CustomerGroup;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.masters.destinations.Destination;
import w.fujiko.model.masters.products.Product;
import w.fujiko.model.masters.suppliers.SupplierBase;
import w.fujiko.model.masters.suppliers.SupplierGroup;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.model.transactions.purchaseorders.jasperdata.PurchaseOrderDetailModel;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderHeaderService;
import w.fujiko.util.common.generator.jasperpdf.JasperReportDataManager;

@Service
@Transactional
public class PurchaseOrderHeaderServiceRepo 
	extends UniversalServiceRepo<PurchaseOrderHeader, PurchaseOrderHeaderDao, Integer> 
	implements PurchaseOrderHeaderService {

	@Autowired PurchaseOrderDetailDao purchseDetailDao;
	
	@Override
	public Optional<PurchaseOrderHeader> getBySlipNumber(String slipNumber) {
		return dao.getBySlipNumber(slipNumber);
	}

	@Override
	public Page<PurchaseOrderHeader> purchaseOrderHeaderSearch(PurchaseOrderHeaderSearchDTO searchKeys) {
		return dao.purchaseOrderHeaderSearch(searchKeys);
	}

	@Override
	public void generateReport(String slipNumber, HttpServletResponse response) {
		JasperReportDataManager jrdm = new JasperReportDataManager();

		Optional<PurchaseOrderHeader> purchaseOrderHeader = getBySlipNumber(slipNumber);
		Map<String, Object> parameters = null;

		if(purchaseOrderHeader.isPresent()) {
			parameters = constructReportData(purchaseOrderHeader.get());
		}

		jrdm.generatePDF("/reports/purchaseorders/purchase_order.jrxml", parameters, response);
	}
	
	private Map<String, Object> constructReportData(PurchaseOrderHeader poHeader) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters = new HashMap<String, Object>();

		parameters.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(Calendar.getInstance().getTime()));
		
		QuotationHeader quotationHeader = poHeader.getQuotationHeader();
		SupplierBase supplierBase = poHeader.getSupplierBase();
		if(supplierBase != null) {
			SupplierGroup supplierGroup = supplierBase.getGroup();
			String supplierName = supplierGroup.getName();
			supplierName += supplierBase.getName();
			supplierName += "　御中";

			parameters.put("supplierName", supplierName);
			parameters.put("slipNumber", poHeader.getSlipNumber());
			parameters.put("propertyNumber", quotationHeader.getWorkingSite().getPropertyNo());
			parameters.put("faxNumber", supplierBase.getFaxNumber1());
		}
		
		CustomerBase customerBase = quotationHeader.getCustomerBase();
		if(customerBase != null) {
			CustomerGroup customerGroup = customerBase.getGroup();
			String customerName = customerGroup.getName();
			customerName += customerBase.getName();
			
			parameters.put("customerName", customerName);
		}
		
		User user = poHeader.getUser();
		if(user != null) {
			Department department = user.getDepartment();
			if(department != null) {
				Branch branch = department.getBranch();
				if(branch != null) {
					parameters.put("branchName", branch.getName());
				}
				parameters.put("postalCode", department.getPostalCode());
				parameters.put("address", department.getAddress1());
				parameters.put("telephone", department.getTelephoneNumber());
				parameters.put("fax", department.getFaxNumber());
			}
			Short code = user.getCode();
			String username = user.getUsername();
			parameters.put("assignee", code + "	" + username);
		}
		
		parameters.put("constructionNumber", quotationHeader.getConstructionNumber());
		parameters.put("constructionName", quotationHeader.getConstructionName());
		
		Destination destination = poHeader.getDestination();
		if(destination != null) {
			parameters.put("destinationPostalCode", destination.getPostalCode());
			parameters.put("destinationAddress1", destination.getAddress1());
			parameters.put("destinationAddress2", destination.getAddress2());
			parameters.put("destinationAddress3", destination.getAddress3());
			parameters.put("destinationName1", destination.getName1());
			parameters.put("destinationName2", destination.getName2());
			parameters.put("distributorName", destination.getDistributorName());
			parameters.put("destinationPhoneNumber", destination.getPhoneNumber());
		}
		
		parameters.put("purchaseOrderDetails", getDetails(poHeader));
		parameters.put("deliveryDate", new SimpleDateFormat("yyyy年MM月dd日").format(poHeader.getDeliveryDate()));
		parameters.put("remarks", poHeader.getRemarks());
		
		return parameters;
	}
	
	private List<PurchaseOrderDetailModel> getDetails(PurchaseOrderHeader poHeader) {
		List<PurchaseOrderDetail> details = purchseDetailDao.getByPurchaseOrderHeaderId(poHeader.getId());
		List<PurchaseOrderDetailModel> result = new ArrayList<>();
		if(details != null && details.size() > 0) {
			result = details.stream()
						.map(element -> {
							Product product = element.getProduct();
							return new PurchaseOrderDetailModel(
									"発注",
									product.getMaker().getName() + "　" + product.getModelNumber(),
									product.getName(), 
									element.getQuantity(),
									element.getRemarks()
								);
						}).collect(Collectors.toList());
		}
		return result;
	}

}