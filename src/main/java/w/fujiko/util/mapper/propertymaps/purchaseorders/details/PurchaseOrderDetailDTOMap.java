package w.fujiko.util.mapper.propertymaps.purchaseorders.details;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.purchaseorders.details.PurchaseOrderDetailDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;

public class PurchaseOrderDetailDTOMap extends PropertyMap<PurchaseOrderDetail, PurchaseOrderDetailDTO> {

	@Override
	protected void configure() {
		map().setPurchaseOrderHeaderId(source.getPurchaseOrderHeader().getId());
		map().setQuotationHeaderId(source.getId().getQuotationHeaderId());
		map().setTaskId(source.getTaskId());
		map().setLineNumber(source.getLineNumber());
		map().setPurchasingSection(source.getPurchasingSection());
		map().setModelNumber(source.getModelNumber());
		map().setProductName(source.getProductName());
		map().getMaker().setId(source.getMaker().getId());
		map().getProduct().setId(source.getProduct().getId());
		map().getUnit().setId(source.getUnit().getId());
		map().setQuantity(source.getQuantity());
		map().setEstimatedQuantity(source.getEstimatedQuantity());
		map().setUnitPrice(source.getUnitPrice());
		map().setPurchasedQuantity(source.getPurchasedQuantity());
		map().setAmount(source.getAmount());
		map().setRemarks(source.getRemarks());
		map().setCreatedById(source.getCreatedBy().getId());
		map().setUpdatedById(source.getUpdatedBy().getId());
		map().setUpdatedAtId(source.getUpdatedAt().getId());
		map().setCreatedAtId(source.getCreatedAt().getId());
	}

}