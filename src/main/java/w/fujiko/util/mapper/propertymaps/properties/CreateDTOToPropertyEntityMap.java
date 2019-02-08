package w.fujiko.util.mapper.propertymaps.properties;

import org.modelmapper.PropertyMap;

import org.springframework.stereotype.Service;

import w.fujiko.dto.properties.PropertyCreateDTO;
import w.fujiko.model.masters.properties.Property;
/**
 * Maps PropertyCreateDTO's property to properties of Property
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: PropertyCreateDTO
 * Destination: Property
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class CreateDTOToPropertyEntityMap extends PropertyMap<PropertyCreateDTO,Property>{

	@Override
	protected void configure() {                
                skip(destination.getId());
                skip(destination.getPropertyNo());
                map().setPropertyName(source.getPropertyName());
                map().setPropertyKana(source.getPropertyKana());
                map().setOwner(source.getOwner());
                map().getRegistrationOfficer().setId(source.getRegistrationOfficerId());
                map().getBranch().setId(source.getBranchId());
                map().setDeletedFLG(source.getDeletedFLG());
                map().setRemarks(source.getRemarks());
                map().getCreatedBy().setId(source.getCreatedById());
                map().getCreatedAt().setId(source.getCreatedAtId());
	}

}