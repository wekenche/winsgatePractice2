package w.fujiko.util.mapper.propertymaps.purchaseorders.headers;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;

public class PurchaseOrderHeaderToDTOMap extends PropertyMap<PurchaseOrderHeader, PurchaseOrderHeaderDTO> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setSlipNumber(source.getSlipNumber());
		map().setVersion(source.getVersion());
		map().setQuotationHeaderId(source.getQuotationHeader().getId());
		map().setOrderStatus(source.getOrderStatus());
		map().setSalesStatus(source.getSalesStatus());
		map().setPurchaseOrderDate(source.getPurchaseOrderDate());
		map().setDeliveryDate(source.getDeliveryDate());
		map().getSupplierBase().setId(source.getSupplierBase().getId());
		map().setSlipType(source.getSlipType());
		map().setConsumptionTaxType(source.getConsumptionTaxType());
		map().setSalesTaxRate(source.getSalesTaxRate());
		map().setContactPersonIncharge(source.getContactPersonIncharge());
		map().setSecureFlg(source.getSecureFlg());
		map().setCorrectionDeadline(source.getCorrectionDeadline());
		map().getDestination().setId(source.getDestination().getId());		
		map().getUser().setId(source.getUser().getId());
		map().getDepartment().setId(source.getDepartment().getId());
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
		map().setCreatedById(source.getCreatedBy().getId());
		map().setUpdatedById(source.getUpdatedBy().getId());
		map().setUpdatedAtId(source.getUpdatedAt().getId());
		map().setCreatedAtId(source.getCreatedAt().getId());
	}

}