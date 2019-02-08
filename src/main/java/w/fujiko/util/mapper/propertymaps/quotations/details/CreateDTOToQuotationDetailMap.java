package w.fujiko.util.mapper.propertymaps.quotations.details;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import w.fujiko.dto.quotations.details.QuotationDetailCreateDTO;
import w.fujiko.model.transactions.quotations.QuotationDetail;

/**
 * Maps QuotationDetailCreateDTO's property to properties of QuotationDetail
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: QuotationDetailCreateDTO
 * Destination: QuotationDetail
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class CreateDTOToQuotationDetailMap extends PropertyMap<QuotationDetailCreateDTO,QuotationDetail>{

	@Override
	protected void configure() {
                map().setProductName(source.getProductName());              
                map().getId().setTaskId(source.getTaskId());
                map().getMaker().setId(source.getMakerId());
                map().getProduct().setId(source.getProductId());
                map().setUnit(source.getUnit());
                map().setOrderedQuantity(source.getOrderedQuantity());
                map().setPurchasedQuantity(source.getPurchasedQuantity());
                map().setSoldQuantity(source.getSoldQuantity());
                map().getCreatedBy().setId(source.getCreatedById());
                map().getCreatedAt().setId(source.getCreatedAtId());
	}

}