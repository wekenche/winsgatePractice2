package w.fujiko.util.mapper.propertymaps.users;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import w.fujiko.dto.mymenus.MyMenuCreateDTO;
import w.fujiko.model.masters.users.MyMenu;

/**
 * Maps MyMenuCreateDTO's property to properties of MyMenu
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: MyMenuCreateDTO
 * Destination: MyMenu
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class CreateDTOToMyMenuMap extends PropertyMap<MyMenuCreateDTO,MyMenu>{

	@Override
	protected void configure() {
        skip(destination.getId());
        map().getRoleProgram().getMstRoleProg().setAuthorized_user(source.getAuthorizedUserId());
        map().getRoleProgram().getMstRoleProg().setProgram_id(source.getProgramId());                
        map().getCreatedBy().setId(source.getCreatedById());
        map().getCreatedAt().setId(source.getCreatedAtId());
	}

}