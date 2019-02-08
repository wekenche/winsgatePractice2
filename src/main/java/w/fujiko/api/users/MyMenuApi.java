package w.fujiko.api.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import java.lang.reflect.Type;
import java.net.URI;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Created;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.mymenus.MyMenuDTO;
import w.fujiko.dto.mymenus.MyMenuUpdateDTO;
import w.fujiko.dto.mymenus.MyMenuCreateDTO;
import w.fujiko.model.masters.users.MyMenu;
import w.fujiko.service.users.MyMenuService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.users.CreateDTOToMyMenuMap;
import w.fujiko.util.mapper.propertymaps.users.UpdateDTOToMyMenuMap;
import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;;

@RestController
@RequestMapping("/api/mymenu")
public class MyMenuApi 
	extends Api<MyMenuService,MyMenu,Integer> {

		private ModelMapper modelMapper;
	
		public MyMenuApi(){
			modelMapper = new ModelMapper();	
			modelMapper.getConfiguration()
						.setFieldMatchingEnabled(true)
						.setFieldAccessLevel(AccessLevel.PRIVATE)
						.setAmbiguityIgnored(true);
		}
		
	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<MyMenu> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
		var myMenu = response.get();

        MyMenuDTO myMenuDTO = this.modelMapper
								  .map(myMenu,MyMenuDTO.class);

		return ResponseEntity
				.ok(myMenuDTO);
	}

	@GetMapping("/get/{userid}/")
	public @ResponseBody List<MyMenuDTO> getCustomizeMenuOfUser(@PathVariable(value="userid") final Integer userid) {
		List<MyMenu> myMenuModel = service.getCustomizeMenuOfUser(userid);
		
		Type listType = new TypeToken<List<MyMenuDTO>>() {}.getType();

		List<MyMenuDTO> myMenuDTO = this.modelMapper
										.map(myMenuModel,listType);

		return myMenuDTO;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createDestination(@Valid @RequestBody MyMenuCreateDTO myMenuDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			var modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			modelMapper.addMappings(new CreateDTOToMyMenuMap());
			MyMenu myMenuEntity = modelMapper.map(myMenuDTO, MyMenu.class);
			service.saveOrUpdate(myMenuEntity);
			String createdLink = "/api/mymenu/"+myMenuEntity.getId();
			return ResponseEntity
					.created(new URI(createdLink))
					.body(new Created(createdLink));			

		}catch(ConstraintViolationException cve){
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe){
			List<Response> errorResponse = new ArrayList<>();
			fte.api.Errors err = new fte.api.Errors();
			err.setDefaultMessage(pe.getLocalizedMessage());			
			errorResponse.add(err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateMaker(@Valid @RequestBody MyMenuUpdateDTO myMenuUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			MyMenu myMenuEntity = service.get(myMenuUpdateDTO.getId()).orElseThrow();
			var modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			modelMapper.addMappings(new UpdateDTOToMyMenuMap());
			modelMapper.map(myMenuUpdateDTO, myMenuEntity);
			service.saveOrUpdate(myMenuEntity);
			return ResponseEntity.ok().body(new Success());
		}catch(ConstraintViolationException cve){
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe){
			List<Response> errorResponse = new ArrayList<>();
			fte.api.Errors err = new fte.api.Errors();
			err.setDefaultMessage(pe.getLocalizedMessage());			
			errorResponse.add(err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}		
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable("id") final Integer id) {
		
		try {
			var myMenu = service.get(id).orElseThrow();
			service.delete(myMenu);
			return ResponseEntity.ok().body(new Success());			
		}catch(ConstraintViolationException cve){
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe){
			List<Response> errorResponse = new ArrayList<>();
			fte.api.Errors err = new fte.api.Errors();
			err.setDefaultMessage(pe.getLocalizedMessage());			
			errorResponse.add(err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
		}
	}
}