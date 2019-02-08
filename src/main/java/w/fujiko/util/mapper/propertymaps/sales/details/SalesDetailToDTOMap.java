package w.fujiko.util.mapper.propertymaps.sales.details;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.sales.details.SalesDetailDTO;
import w.fujiko.model.transactions.sales.SalesDetail;

public class SalesDetailToDTOMap extends PropertyMap<SalesDetail, SalesDetailDTO> {

	@Override
	protected void configure() {
		map().setSalesHeaderId(source.getSalesHeader().getId());
		map().setQuotationHeaderId(source.getQuotationHeader().getId());
		map().setTaskId(source.getTaskId());
		map().setLineNumber(source.getLineNumber());
		map().setSalesSection(source.getSalesSection());
		map().setModelNumber(source.getModelNumber());
		map().setProductName(source.getProductName());
		map().getMaker().setId(source.getMaker().getId());
		map().getProduct().setId(source.getProduct().getId());
		map().getUnit().setId(source.getUnit().getId());
		map().setQuantity(source.getQuantity());
		map().setUnitPrice(source.getUnitPrice());
		map().setOriginalUnitPrice(source.getOriginalUnitPrice());
		map().setAmount(source.getAmount());
		map().setRemarks(source.getRemarks());
		map().setCreatedById(source.getCreatedBy().getId());
		map().setUpdatedById(source.getUpdatedBy().getId());
		map().setUpdatedAtId(source.getUpdatedAt().getId());
		map().setCreatedAtId(source.getCreatedAt().getId());
	}

}