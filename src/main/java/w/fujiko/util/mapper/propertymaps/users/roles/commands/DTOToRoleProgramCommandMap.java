package w.fujiko.util.mapper.propertymaps.users.roles.commands;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import w.fujiko.dto.roleprograms.commands.RoleProgramCommandDTO;
import w.fujiko.model.masters.users.RoleProgramCommand;

/**
 * Maps RoleProgramCommandDTO's property to properties of RoleProgramCommand
 * Only properties of the source which are bound to a specific property of the destination are define in this mapping.
 * 
 * Source: RoleProgramCommandDTO
 * Destination: RoleProgramCommand
 * 
 * Usage:
 * use this in modelMapper object to define specific mapping. ex.: modelMapper.addMappings(myMap);
 *  
 */
@Service
public class DTOToRoleProgramCommandMap extends PropertyMap<RoleProgramCommandDTO,RoleProgramCommand>{

	@Override
	protected void configure() {
        map().getCommand().setProgram_id(source.getProgramId());
        map().getCommand().setUser_id(source.getUserId());
        map().getCommand().setCommand_id(source.getCommandId());        
        map().getCreated_by().setId(source.getCreatedById());
        map().getCreated_at().setId(source.getCreatedAtId());
	}
}