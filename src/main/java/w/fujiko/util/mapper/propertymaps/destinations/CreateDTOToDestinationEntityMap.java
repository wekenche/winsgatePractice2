package w.fujiko.util.mapper.propertymaps.destinations;


import org.modelmapper.PropertyMap;

import org.springframework.stereotype.Service;

import w.fujiko.dto.destinations.DestinationCreateDTO;
import w.fujiko.model.masters.destinations.Destination;

/**
 * Maps DestinationCreateDTO's property to properties of Destination
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: DestinationCreateDTO
 * Destination: Destination
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class CreateDTOToDestinationEntityMap extends PropertyMap<DestinationCreateDTO,Destination>{

	@Override
	protected void configure() {                
                skip(destination.getId());                
                map().setCode(source.getCode());
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
                map().getCreatedBy().setId(source.getCreatedById());
                map().getCreatedAt().setId(source.getCreatedAtId());
	}

}