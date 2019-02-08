package w.fujiko.util.mapper.propertymaps.quotations.details;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import w.fujiko.dto.quotations.details.QuotationDetailDTO;
import w.fujiko.model.transactions.quotations.QuotationDetail;

/**
 * Maps QuotationDetail model property to properties of QuotationDetailDTO
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: QuotationDetail
 * Destination: QuotationDetailDTO
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class QuotationDetailToDTOMap extends PropertyMap<QuotationDetail,QuotationDetailDTO>{

	@Override
	protected void configure() {
        map().setQuotationHeaderId(source.getId().getQuotationHeaderId());
        map().setTaskId(source.getId().getTaskId());
        map().getMaker().setId(source.getMaker().getId());
        map().getMaker().setCode(source.getMaker().getCode());
        map().getMaker().setName(source.getMaker().getName());
        map().getProduct().setId(source.getProduct().getId());
        map().getProduct().setCode(source.getProduct().getCode());
        map().getProduct().setName(source.getProduct().getName());
        map().setProductName(source.getProductName());
        map().setCreatedById(source.getCreatedBy().getId());
        map().setUpdatedById(source.getUpdatedBy().getId());
        map().setUpdatedAtId(source.getUpdatedAt().getId());
        map().setCreatedAtId(source.getCreatedAt().getId());
	}

}
