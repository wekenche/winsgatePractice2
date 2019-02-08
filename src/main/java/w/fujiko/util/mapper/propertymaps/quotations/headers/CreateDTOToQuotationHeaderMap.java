package w.fujiko.util.mapper.propertymaps.quotations.headers;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.quotations.headers.QuotationHeaderCreateDTO;
import w.fujiko.model.transactions.quotations.QuotationHeader;

/**
 * Maps QuotationHeaderCreateDTO's property to properties of QuotationHeader
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: QuotationHeaderCreateDTO
 * Destination: QuotationHeader
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
public class CreateDTOToQuotationHeaderMap extends PropertyMap<QuotationHeaderCreateDTO,QuotationHeader>{

	@Override
	protected void configure() {
        skip(destination.getId());
                map().setSlipNumber(source.getSlipNumber());
                map().getWorkingSite().setId(source.getWorkingSiteId());
                map().setQuotationDate(source.getQuotationDate());
                map().setDeliveryDate(source.getDeliveryDate());
                map().setConstructionNumber(source.getConstructionNumber());
                map().setConstructionName(source.getConstructionName());
                map().setConstructionCategory(source.getConstructionCategory());
                map().setConstructionCategoryName(source.getConstructionCategoryName());
                map().setUnit(source.getUnit());
                map().setRedSlipCopyFlag(source.getRedSlipCopyFlag());
                map().setCopyOriginNumber(source.getCopyOriginNumber());
                map().setConstructionContractFlag(source.getConstructionContractFlag());
                map().setWarrantyCardFlag(source.getWarrantyCardFlag());                
                map().getUser().setId(source.getUserId());
                map().getDepartment().setId(source.getDepartmentId());
                map().getCustomerBase().setId(source.getCustomerBaseId());
                map().setCustomerName(source.getCustomerName());
                map().setDeliveryDateMessage(source.getDeliveryDateMessage());
                map().setRespectUnnecessaryFlag(source.getRespectUnnecessaryFlag());
                map().setContactUserName(source.getContactUserName());
                map().setPostMessage(source.getPostMessage());
                map().setPaymentTermsMessage(source.getPaymentTermsMessage());
                map().setEstimatedDeadlineMessage(source.getEstimatedDeadlineMessage());
                map().setDeliveryPlaceMessage(source.getDeliveryPlaceMessage());
                map().setSparePartsCostMessage(source.getSparePartsCostMessage());
                map().setStandardUnitPricePrintingFlag(source.getStandardUnitPricePrintingFlag());
                map().setQuotationRank(source.getQuotationRank());
                map().setPoStatus(source.getPoStatus());
                map().setPurchaseStatus(source.getPurchaseStatus());
                map().setSalesStatus(source.getSalesStatus());
                map().setBillingStatus(source.getBillingStatus());
                map().setPaymentStatus(source.getPaymentStatus());
                map().setQuotationTotalAmount(source.getQuotationTotalAmount());
                map().setOrderTotalAmount(source.getOrderTotalAmount());
                map().setPurchaseTotalAmount(source.getPurchaseTotalAmount());
                map().setGrossProfit(source.getGrossProfit());
                map().setGrossMarginRatio(source.getGrossMarginRatio());
                map().setDateCreated(source.getDateCreated());
                map().getCreatedBy().setId(source.getCreatedBy());
                map().getCreatedAt().setId(source.getCreatedAt());
	}

}
