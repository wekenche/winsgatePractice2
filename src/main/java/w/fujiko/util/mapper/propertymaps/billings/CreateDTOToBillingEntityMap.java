package w.fujiko.util.mapper.propertymaps.billings;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import w.fujiko.dto.billings.BillingCreateDTO;
import w.fujiko.model.masters.billings.Billing;

/**
 * Maps BillingUpdateDTO's property to properties of Billing
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: BillingUpdateDTO
 * Destination: Billing
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class CreateDTOToBillingEntityMap extends PropertyMap<BillingCreateDTO,Billing>{

	@Override
	protected void configure() {                
                skip(destination.getId());
                map().setName(source.getName());
                map().setNameKana(source.getNameKana());
                map().setShortName(source.getShortName());
                map().setPostalCode(source.getPostalCode());
                map().setAddress1(source.getAddress1());
                map().setAddress2(source.getAddress2());
                map().setAddress3(source.getAddress3());
                map().setAccountPhoneNumber(source.getAccountPhoneNumber());
                map().setTelephoneNumber1(source.getTelephoneNumber1());
                map().setTelephoneNumber2(source.getTelephoneNumber2());
                map().setTitle(source.getTitle());
                map().setClaimsGroup(source.getClaimsGroup());
                map().setTotalInvoicePrintingType(source.getTotalInvoicePrintingType());
                map().setBillingPrintingType(source.getBillingPrintingType());
                map().setPropertyPrintingType(source.getPropertyPrintingType());
                map().setDepositMethod1reference(source.getDepositMethod1reference());
                map().setDepositMethod1SplitPayment(source.getDepositMethod1SplitPayment());
                map().setDepositMethod1(source.getDepositMethod1());
                map().setDepositMethod1PlannedPaymentMonthAfter(source.getDepositMethod1PlannedPaymentMonthAfter());
                map().setDepositMethod1PlanDay(source.getDepositMethod1PlanDay());
                map().setDepositMethod1Division(source.getDepositMethod1Division());
                map().setDepositMethod1CriteriaPercentage(source.getDepositMethod1CriteriaPercentage());
                map().setDepositMethod1DivisionStandardAmount(source.getDepositMethod1DivisionStandardAmount());
                map().setDepositMethod1ScheduledPaymentMonthAfter(source.getDepositMethod1ScheduledPaymentMonthAfter());
                map().setDepositMethod1ScheduledDay(source.getDepositMethod1ScheduledDay());
                map().setDepositMethod2Reference(source.getDepositMethod2Reference());
                map().setDepositMethod2SplitPayment(source.getDepositMethod2SplitPayment());
                map().setDepositMethod2(source.getDepositMethod2());
                map().setDepositMethod2PlannedPaymentMonthAfter(source.getDepositMethod2PlannedPaymentMonthAfter());
                map().setDepositMethod2PlanDay(source.getDepositMethod2PlanDay());
                map().setDepositMethod2Division(source.getDepositMethod2Division());
                map().setDepositMethod2CriteriaPercentage(source.getDepositMethod2CriteriaPercentage());
                map().setDepositMethod2DivisionStandardAmount(source.getDepositMethod2DivisionStandardAmount());
                map().setDepositMethod2Slip(source.getDepositMethod2Slip());
                map().setDepositMethod2ScheduledPaymentMonthAfter(source.getDepositMethod2ScheduledPaymentMonthAfter());
                map().setDepositMethod2ScheduledPlanDay(source.getDepositMethod2ScheduledPlanDay());
                map().setCashOnDelivery(source.getCashOnDelivery());
                map().setCollection(source.getCollection());
                map().setDepositSite(source.getDepositSite());
                map().setShipmentAfterPaymentConfirmed(source.getShipmentAfterPaymentConfirmed());
                map().setDedicatedSlip(source.getDedicatedSlip());
                map().setPaymentConditions(source.getPaymentConditions());
                map().setCollectSafetyCooperationFeeFlag(source.getCollectSafetyCooperationFeeFlag());
                map().setCollectSafetyCooperationFee(source.getCollectSafetyCooperationFee());
                map().setCollectSafetyCooperationFeeMemo(source.getCollectSafetyCooperationFeeMemo());
                map().setMemo(source.getMemo());
                map().setIsEnd(source.getIsEnd());
                map().setDateCreated(source.getDateCreated());
                map().getCreatedBy().setId(source.getCreatedById());
                map().getCreatedAt().setId(source.getCreatedAtId());
	}

}