package w.fujiko.util.mapper.propertymaps.quotations.headers;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.quotations.headers.QuotationHeaderDTO;
import w.fujiko.model.transactions.quotations.QuotationHeader;

/**
 * Maps QuotationHeader model property to properties of QuotationHeaderDTO
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * <br/>
 * Source: QuotationHeader
 * Destination: QuotationHeaderDTO
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
public class QuotationHeaderToDTOMap extends PropertyMap<QuotationHeader,QuotationHeaderDTO>{

	@Override
	protected void configure() {
        map().setId(source.getId());
        map().setSlipNumber(source.getSlipNumber());
        map().getWorkingSite().setId(source.getWorkingSite().getId());
        map().getWorkingSite().setPropertyName(source.getWorkingSite().getPropertyName());
        map().getWorkingSite().setPropertyNo(source.getWorkingSite().getPropertyNo());
        map().setQuotationDate(source.getQuotationDate());
        map().setDeliveryDate(source.getDeliveryDate());
        map().setConstructionNumber(source.getConstructionNumber());
        map().setConstructionName(source.getConstructionName());
        map().setUnit(source.getUnit());
        map().setConstructionCategory(source.getConstructionCategory());
        map().setConstructionCategoryName(source.getConstructionCategoryName());
        map().setRedSlipCopyFlag(source.isRedSlipCopyFlag());
        map().setCopyOriginNumber(source.getCopyOriginNumber());
        map().setConstructionContractFlag(source.isConstructionContractFlag());
        map().setWarrantyCardFlag(source.isWarrantyCardFlag());                
        map().getUser().setId(source.getUser().getId());
        map().getUser().setCode(source.getUser().getCode());
        map().getUser().setUsername(source.getUser().getUsername());
        map().getDepartment().setId(source.getDepartment().getId());
        map().getDepartment().setName(source.getDepartment().getName());
        map().getCustomerBase().setId(source.getCustomerBase().getId());
        map().getCustomerBase().setBillingId(source.getCustomerBase().getBill().getId());
        map().getCustomerBase().setBranchId(source.getCustomerBase().getBranch().getId());
        map().getCustomerBase().setCode(source.getCustomerBase().getCode());
        map().getCustomerBase().setName(source.getCustomerBase().getName());
        map().setCustomerName(source.getCustomerName());
        map().setDeliveryDateMessage(source.getDeliveryDateMessage());
        map().setRespectUnnecessaryFlag(source.isRespectUnnecessaryFlag());
        map().setContactUserName(source.getContactUserName());
        map().setPostMessage(source.getPostMessage());
        map().setPaymentTermsMessage(source.getPaymentTermsMessage());
        map().setEstimatedDeadlineMessage(source.getEstimatedDeadlineMessage());
        map().setDeliveryPlaceMessage(source.getDeliveryPlaceMessage());
        map().setSparePartsCostMessage(source.getSparePartsCostMessage());
        map().setStandardUnitPricePrintingFlag(source.isStandardUnitPricePrintingFlag());
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
        map().setCreatedById(source.getCreatedBy().getId());
        map().setCreatedAtId(source.getCreatedAt().getId());
	}

}