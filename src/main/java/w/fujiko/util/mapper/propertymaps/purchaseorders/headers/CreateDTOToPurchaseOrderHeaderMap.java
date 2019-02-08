package w.fujiko.util.mapper.propertymaps.purchaseorders.headers;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderCreateDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;

public class CreateDTOToPurchaseOrderHeaderMap extends PropertyMap<PurchaseOrderHeaderCreateDTO, PurchaseOrderHeader> {

	@Override
	protected void configure() {
		skip(destination.getId());
		map().setSlipNumber(source.getSlipNumber());
		map().setVersion(source.getVersion());
		map().getQuotationHeader().setId(source.getQuotationHeaderId());
		map().setOrderStatus(source.getOrderStatus());
		map().setSalesStatus(source.getSalesStatus());
		map().setPurchaseOrderDate(source.getPurchaseOrderDate());
		map().setDeliveryDate(source.getDeliveryDate());
		map().getSupplierBase().setId(source.getSupplierBaseId());
		map().setSlipType(source.getSlipType());
		map().setConsumptionTaxType(source.getConsumptionTaxType());
		map().setSalesTaxRate(source.getSalesTaxRate());
		map().setContactPersonIncharge(source.getContactPersonIncharge());
		map().setSecureFlg(source.getSecureFlg());
		map().setCorrectionDeadline(source.getCorrectionDeadline());
		map().getDestination().setId(source.getDestinationId());		
		map().getUser().setId(source.getUserId());
		map().getDepartment().setId(source.getDepartmentId());
		map().setPurchaseType(source.getPurchaseType());
		map().setRemarks(source.getRemarks());
		map().setDeliveryType(source.getDeliveryType());
		map().setDirectDelivery(source.getDirectDelivery());
		map().setDirectDeliveryTimezone(source.getDirectDeliveryTimezone());
		map().setDirectDeliveryDesignationType(source.getDirectDeliveryDesignationType());
		map().setHertzType(source.getHertzType());
		map().setPriceList(source.getPriceList());
		map().setPriceRatio(source.getPriceRatio());
		map().setTotalPurchase(source.getTotalPurchase());
		map().setTotalReturn(source.getTotalReturn());
		map().setTotalDiscount(source.getTotalDiscount());
		map().setTotalOrder(source.getTotalOrder());
		map().setTotalTaxIncluded(source.getTotalTaxIncluded());
		map().setTotalTaxConsumption(source.getTotalTaxConsumption());
		map().setPurchaseOrderAmount(source.getPurchaseOrderAmount());
		map().getCreatedBy().setId(source.getCreatedById());
		map().getCreatedAt().setId(source.getCreatedAtId());
	}

}