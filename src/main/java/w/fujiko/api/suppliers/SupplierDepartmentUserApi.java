package w.fujiko.api.suppliers;

import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import w.fujiko.dto.suppliers.departments.users.SupplierDepartmentUserCreateDTO;
import w.fujiko.dto.suppliers.departments.users.SupplierDepartmentUserDTO;
import w.fujiko.dto.suppliers.departments.users.SupplierDepartmentUserUpdateDTO;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser;
import w.fujiko.service.suppliers.departments.users.SupplierDepartmentUserService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/supplier/department/user")
public class SupplierDepartmentUserApi 
	extends Api<SupplierDepartmentUserService, SupplierDepartmentUser,Integer> {
	
	private ModelMapper modelMapper;
	
	public SupplierDepartmentUserApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<SupplierDepartmentUser> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
		SupplierDepartmentUser supplierDepartmentUser = response.get();
		SupplierDepartmentUserDTO supplierDepartmentUserDTO = this.modelMapper
										.map(supplierDepartmentUser, SupplierDepartmentUserDTO.class);

		return ResponseEntity
				.ok(supplierDepartmentUserDTO);
	}
	
	@GetMapping("/department/{departmentId}")
	public @ResponseBody List<SupplierDepartmentUserDTO> getByDepartmentId(@PathVariable(value="departmentId") final int departmentId) {
		
		List<SupplierDepartmentUser> supplierDepartmentUserModel = service.getByDepartmentId(departmentId);
		Type listType = new TypeToken<List<SupplierDepartmentUserDTO>>() {}.getType();

		List<SupplierDepartmentUserDTO> supplierDepartmentUserDTO = this.modelMapper
										.map(supplierDepartmentUserModel,listType);

		return supplierDepartmentUserDTO;
    }
	
	@GetMapping("/dto")
	public @ResponseBody List<SupplierDepartmentUserDTO> getDTO() {
		
		List<SupplierDepartmentUser> supplierDepartmentUserModel = service.get();
		Type listType = new TypeToken<List<SupplierDepartmentUserDTO>>() {}.getType();
		List<SupplierDepartmentUserDTO> supplierDepartmentUserDTO = this.modelMapper
										     .map(supplierDepartmentUserModel,listType);

		return supplierDepartmentUserDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<SupplierDepartmentUserDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<SupplierDepartmentUser> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<SupplierDepartmentUserDTO> pageDTO = new Page<SupplierDepartmentUserDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<SupplierDepartmentUserDTO>>() {}.getType();

		List<SupplierDepartmentUserDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody SupplierDepartmentUserCreateDTO supplierDepartmentUserCreate, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
            SupplierDepartmentUser supplierDepartmentUserModel = this.modelMapper.map(supplierDepartmentUserCreate, SupplierDepartmentUser.class);
            supplierDepartmentUserModel.setId(null);
			service.saveOrUpdate(supplierDepartmentUserModel);
			String createdLink = "/api/supplier/department/user/"+supplierDepartmentUserModel.getId();
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
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(StringConverter.convertToReadable(MSG_500));
		}
    }
	
	@PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody SupplierDepartmentUserUpdateDTO supplierDepartmentUserUpdate, Errors error) {
		if(error.hasErrors())   
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<SupplierDepartmentUser> supplierDepartmentUserRecord = service.get(supplierDepartmentUserUpdate.getId());
			SupplierDepartmentUser supplierDepartmentUserModel=supplierDepartmentUserRecord.orElseThrow();
			
			this.modelMapper
				.map(supplierDepartmentUserUpdate, supplierDepartmentUserModel);

			service.saveOrUpdate(supplierDepartmentUserModel);
			return ResponseEntity.ok().body(new Success());

		}catch(NoSuchElementException ex){			
			return ResponseEntity.notFound().build();
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
			return ResponseEntity.badRequest().body(StringConverter.convertToReadable(MSG_500));
		}
	}

}