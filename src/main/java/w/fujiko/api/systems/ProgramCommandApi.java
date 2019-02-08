package w.fujiko.api.systems;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import w.fujiko.dto.programs.commands.ProgramCommandDTO;
import w.fujiko.model.masters.systems.ProgramCategory;
import w.fujiko.model.masters.systems.ProgramCommand;
import w.fujiko.service.systems.ProgramCommandService;

@RestController
@RequestMapping("/api/program/command/")
public class ProgramCommandApi 
	extends Api<ProgramCommandService,ProgramCommand,Integer> {

		private ModelMapper modelMapper;

		public ProgramCommandApi(){
			modelMapper = new ModelMapper();	
			modelMapper.getConfiguration()
						.setFieldMatchingEnabled(true)
						.setFieldAccessLevel(AccessLevel.PRIVATE)
						.setAmbiguityIgnored(true);
		}

		@GetMapping("/grantedcommands/{id}")
		public @ResponseBody List<ProgramCategory> getGrantedProgramCommandByUser(@PathVariable(value="id") final Integer userId) {
			List<ProgramCategory> grantedCommands = service.getGrantedProgramCommandByUser(userId);			
			return grantedCommands;
		}

		@GetMapping("/grantedcommands/{userId}/{pageId}")
		public @ResponseBody List<ProgramCommandDTO> getGrantedProgramCommandOfUserByPage(@PathVariable(value="userId") final Integer userId,
																					   @PathVariable(value="pageId") final String pageId) {							
		
			Type listType = new TypeToken<List<ProgramCommandDTO>>() {}.getType();
			List<ProgramCommand> programCommandModel = service.getGrantedCommandOfUserByPage(userId, pageId);																				
			List<ProgramCommandDTO> programCommandDTO = this.modelMapper
														    .map(programCommandModel,listType);

			return programCommandDTO;
		}

	}