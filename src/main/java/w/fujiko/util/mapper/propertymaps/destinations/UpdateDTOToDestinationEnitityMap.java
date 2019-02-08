package w.fujiko.util.mapper.propertymaps.destinations;

import org.modelmapper.PropertyMap;

import org.springframework.stereotype.Service;

import w.fujiko.dto.destinations.DestinationUpdateDTO;
import w.fujiko.model.masters.destinations.Destination;

/**
 * Maps DestinationUpdateDTO's property to properties of Destination
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: DestinationUpdateDTO
 * Destination: Destination
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class UpdateDTOToDestinationEnitityMap extends PropertyMap<DestinationUpdateDTO,Destination>{

	@Override
	protected void configure() {    
        skip(destination.getCode());                
        map().setId(source.getId());
        map().getCustomer().setId(source.getCustomerId());
        map().setName1(source.getName1());
        map().setName2(source.getName2());
        map().setNameKana(source.getNameKana());
        map().setShortName(source.getShortName());
        map().setDocumentPrintName(source.getDocumentPrintName());
        map().setPostalCode(source.getPostalCode());
        map().setAddress1(source.getAddress1());
        map().setAddress2(source.getAddress2());
        map().setAddress3(source.getAddress3());
        map().setPhoneNumber(source.getPhoneNumber());
        map().setFaxNumber(source.getFaxNumber());
        map().setEmail(source.getEmail());
        map().setUrl(source.getUrl());
        map().setDistributorName(source.getDistributorName());
        map().setIsEnd(source.getIsEnd());                
        map().getUpdatedBy().setId(source.getUpdatedById());
        map().getUpdatedAt().setId(source.getUpdatedAtId());

	}

}