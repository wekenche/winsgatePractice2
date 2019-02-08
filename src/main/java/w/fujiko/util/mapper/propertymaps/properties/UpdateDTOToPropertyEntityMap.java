package w.fujiko.util.mapper.propertymaps.properties;

import org.modelmapper.PropertyMap;

import org.springframework.stereotype.Service;

import w.fujiko.dto.properties.PropertyUpdateDTO;
import w.fujiko.model.masters.properties.Property;

/**
 * Maps PropertyUpdateDTO's property to properties of Property
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: PropertyUpdateDTO
 * Destination: Property
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class UpdateDTOToPropertyEntityMap extends PropertyMap<PropertyUpdateDTO,Property>{

	@Override
	protected void configure() {    
                skip(destination.getPropertyNo());                            
                map().setId(source.getId());
                map().setPropertyKana(source.getPropertyKana());
                map().setPropertyName(source.getPropertyName());
                map().setOwner(source.getOwner());
                map().getRegistrationOfficer().setId(source.getRegistrationOfficerId());
                map().getBranch().setId(source.getBranchId());
                map().setDeletedFLG(source.getDeletedFLG());
                map().setRemarks(source.getRemarks());
                map().getUpdatedBy().setId(source.getUpdatedById());
                map().getUpdatedAt().setId(source.getUpdatedAtId());

	}

}