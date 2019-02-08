package w.fujiko.util.mapper.propertymaps.users;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import w.fujiko.dto.mymenus.MyMenuUpdateDTO;
import w.fujiko.model.masters.users.MyMenu;

/**
 * Maps MyMenuUpdateDTO's property to properties of MyMenu
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: MyMenuUpdateDTO
 * Destination: MyMenu
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class UpdateDTOToMyMenuMap extends PropertyMap<MyMenuUpdateDTO,MyMenu>{

	@Override
	protected void configure() {                
        map().getRoleProgram().getMstRoleProg().setAuthorized_user(source.getAuthorizedUserId());
        map().getRoleProgram().getMstRoleProg().setProgram_id(source.getProgramId());                
        map().getUpdatedBy().setId(source.getUpdatedById());
        map().getUpdatedAt().setId(source.getUpdatedAtId());
	}

}