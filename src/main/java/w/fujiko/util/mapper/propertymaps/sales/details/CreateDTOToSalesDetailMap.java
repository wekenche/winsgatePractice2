package w.fujiko.util.mapper.propertymaps.sales.details;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.sales.details.SalesDetailCreateDTO;
import w.fujiko.model.transactions.sales.SalesDetail;

public class CreateDTOToSalesDetailMap extends PropertyMap<SalesDetailCreateDTO, SalesDetail> {

	@Override
	protected void configure() {
		map().getQuotationHeader().setId(source.getQuotationHeaderId());
        map().getId().setTaskId(source.getTaskId());
		map().setLineNumber(source.getLineNumber());
		map().setSalesSection(source.getSalesSection());
		map().setModelNumber(source.getModelNumber());
		map().setProductName(source.getProductName());
		map().getMaker().setId(source.getMakerId());
		map().getProduct().setId(source.getProductId());
		map().getUnit().setId(source.getUnitId());
		map().setQuantity(source.getQuantity());
		map().setUnitPrice(source.getUnitPrice());
		map().setOriginalUnitPrice(source.getOriginalUnitPrice());
		map().setAmount(source.getAmount());
		map().setRemarks(source.getRemarks());
		map().getCreatedBy().setId(source.getCreatedById());
		map().getCreatedAt().setId(source.getCreatedAtId());
	}

}