package w.fujiko.api.users;

import static w.fujiko.util.common.constants.UserConstants.MSG_500;
import static w.fujiko.util.common.constants.UserConstants.MSG_CONFLICT_USER;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Created;
import fte.api.Page;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.users.UserCreateDTO;
import w.fujiko.dto.users.UserDTO;
import w.fujiko.dto.users.UserUpdateDTO;
import w.fujiko.model.masters.users.User;
import w.fujiko.service.users.UserService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/user")
public class UserApi extends Api<UserService, User, Integer> {
	
	@Autowired 
	private UserService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private ModelMapper modelMapper;
	
	public UserApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE);
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<User> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            User user = response.get();

		
        UserDTO userDTO = this.modelMapper
										  .map(user,UserDTO.class);

		return ResponseEntity
				.ok(userDTO);
	}

	@GetMapping("/code/{code}")
	public @ResponseBody ResponseEntity<?> getByCode(@PathVariable(value="code") final Short code) {
		Optional<User> response = service.getByCode(code);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            User user = response.get();

		
        UserDTO userDTO = this.modelMapper
							  .map(user,UserDTO.class);

		return ResponseEntity
				.ok(userDTO);
	}

	@GetMapping("/dto")
	public @ResponseBody List<UserDTO> getDTO() {
		
		List<User> userModel = service.get();
		
		Type listType = new TypeToken<List<UserDTO>>() {}.getType();

		List<UserDTO> userDTO = this.modelMapper
										  .map(userModel,listType);

		return userDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<UserDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<User> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), "isResigned", isEnd);
		Page<UserDTO> pageDTO = new Page<UserDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<UserDTO>>() {}.getType();

		List<UserDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/list/search")
	public ResponseEntity<?> getUsersByNameOrCode(
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			List<User> entities = 
					service.getUsersByNameOrCode(
							key, Integer.parseInt(index));
			
			Type listType = new TypeToken<List<UserDTO>>() {}.getType();
			List<UserDTO> usersDTO = this.modelMapper.map(entities,listType);
			
			return ResponseEntity.ok().body(usersDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
				
	}

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<User> userResult = service.getUndeletedUserByCode(userDTO.getCode());
			if(userResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_USER));
			} else {
				userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
				User userEntity = this.modelMapper.map(userDTO, User.class);
				service.saveOrUpdate(userEntity);
				String createdLink = "/api/user/"+userEntity.getId();
				return ResponseEntity
					  .created(new URI(createdLink))
					  .body(new Created(createdLink));
			}
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
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			User userEntity = service.getByUserId(userDTO.getId(), false).orElseThrow();
			if(userDTO.getPassword() == null){
				userDTO.setPassword(userEntity.getPassword());
			}else{
				userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			}
			this.modelMapper.map(userDTO, userEntity);
			service.saveOrUpdate(userEntity);
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
}