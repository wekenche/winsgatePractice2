package w.fujiko.util.mapper.propertymaps.payments;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.payments.PaymentUpdateDTO;
import w.fujiko.model.masters.payments.Payment;

/**
 * Maps PaymentUpdateDTO's property to properties of Payment
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: PaymentUpdateDTO
 * Destination: Payment
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
public class UpdateDTOToPaymentMap  extends PropertyMap<PaymentUpdateDTO, Payment> {

	@Override
	protected void configure() {
		skip(destination.getCode());
	}

}