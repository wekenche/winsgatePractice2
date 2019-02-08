package w.fujiko.util.mapper.propertymaps.customerbasemaps;

import org.modelmapper.PropertyMap;

import w.fujiko.dto.customers.CustomerBaseUpdateDTO;
import w.fujiko.model.masters.customers.CustomerBase;

/**
 * Maps CustomerBaseUpdateDTO's property to properties of CustomerBase
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: CustomerBaseUpdateDTO
 * Destination: CustomerBase
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
public class UpdateDTOToCustomerBaseMap extends PropertyMap<CustomerBaseUpdateDTO,CustomerBase>{

	@Override
	protected void configure() {
                skip(destination.getCode());             
	}

}