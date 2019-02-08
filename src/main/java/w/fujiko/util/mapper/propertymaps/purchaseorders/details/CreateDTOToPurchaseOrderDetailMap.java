package w.fujiko.util.mapper.propertymaps.purchaseorders.details;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.purchaseorders.details.PurchaseOrderDetailCreateDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;

public class CreateDTOToPurchaseOrderDetailMap extends PropertyMap<PurchaseOrderDetailCreateDTO, PurchaseOrderDetail> {

	@Override
	protected void configure() {
		map().getId().setTaskId(source.getTaskId());
		map().getId().setQuotationHeaderId(source.getQuotationHeaderId());
		map().setLineNumber(source.getLineNumber());
		map().setPurchasingSection(source.getPurchasingSection());
		map().setModelNumber(source.getModelNumber());
		map().setProductName(source.getProductName());
		map().getMaker().setId(source.getMakerId());
        map().getProduct().setId(source.getProductId());
		map().getUnit().setId(source.getUnitId());
		map().setQuantity(source.getQuantity());
		map().setEstimatedQuantity(source.getEstimatedQuantity());
		map().setUnitPrice(source.getUnitPrice());
		map().setPurchasedQuantity(source.getPurchasedQuantity());
		map().setAmount(source.getAmount());
		map().setRemarks(source.getRemarks());
		map().getCreatedBy().setId(source.getCreatedById());
		map().getCreatedAt().setId(source.getCreatedAtId());
	}

}