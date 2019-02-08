package w.fujiko.api.customers;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import java.lang.reflect.Type;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Created;
import fte.api.Response;
import fte.api.Success;
import fte.api.Page;

import w.fujiko.dto.customers.departments.users.CustomerDepartmentUserCreateDTO;
import w.fujiko.dto.customers.departments.users.CustomerDepartmentUserDTO;
import w.fujiko.dto.customers.departments.users.CustomerDepartmentUserUpdateDTO;
import w.fujiko.model.masters.customers.CustomerDepartmentUser;
import w.fujiko.service.customers.departments.users.CustomerDepartmentUserService;

@RestController
@RequestMapping("/api/customer/department/user")
public class CustomerDepartmentUserApi
	extends Api<CustomerDepartmentUserService,CustomerDepartmentUser,Integer> {
	
	private ModelMapper modelMapper;
	
	public CustomerDepartmentUserApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<CustomerDepartmentUser> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            CustomerDepartmentUser customerDepartmentUser = response.get();

		
			CustomerDepartmentUserDTO customerDepartmentUserDTO = this.modelMapper
										                      .map(customerDepartmentUser,CustomerDepartmentUserDTO.class);

		return ResponseEntity
				.ok(customerDepartmentUserDTO);
	}

    @GetMapping("/dto")
	public @ResponseBody List<CustomerDepartmentUserDTO> getDTO() {
		
		List<CustomerDepartmentUser> customerDepartmentUserModel = service.get();
		
		Type listType = new TypeToken<List<CustomerDepartmentUserDTO>>() {}.getType();

		List<CustomerDepartmentUserDTO> customerDepartmentUserDTO = this.modelMapper
										     .map(customerDepartmentUserModel,listType);

		return customerDepartmentUserDTO;
    }

    @GetMapping("/dto/paginate/{first}/{max}")
	public @ResponseBody Page<CustomerDepartmentUserDTO> paginate(@PathVariable(value="first") final Integer first, @PathVariable(value="max") final Integer max) {
		
		Page<CustomerDepartmentUser> customerDepartmentUser = service.paginate(first, max);

		Page<CustomerDepartmentUserDTO> paginatedCustomerDepartmentUser = new Page<CustomerDepartmentUserDTO>();
		paginatedCustomerDepartmentUser.setFirst(customerDepartmentUser.getFirst());
		paginatedCustomerDepartmentUser.setMax(customerDepartmentUser.getMax());
		paginatedCustomerDepartmentUser.setTotal(customerDepartmentUser.getTotal());
		paginatedCustomerDepartmentUser.setSuccess(customerDepartmentUser.getSuccess());		

		Type listType = new TypeToken<List<CustomerDepartmentUserDTO>>() {}.getType();

		List<CustomerDepartmentUserDTO> customerDepartmentUserDTOs = this.modelMapper
										                                 .map(customerDepartmentUser.getResults(),listType);
        paginatedCustomerDepartmentUser.setResults(customerDepartmentUserDTOs);
		return paginatedCustomerDepartmentUser;
	}

    
	@GetMapping("/department/{departmentId}")
	public @ResponseBody List<CustomerDepartmentUserDTO> getByDepartmentId(@PathVariable(value="departmentId") final int departmentId) {
		
		List<CustomerDepartmentUser> customerDepartmentUserModel = service.getByDepartmentId(departmentId);
		
		Type listType = new TypeToken<List<CustomerDepartmentUserDTO>>() {}.getType();

		List<CustomerDepartmentUserDTO> customerDepartmentUserDTO = this.modelMapper
										     .map(customerDepartmentUserModel,listType);

		return customerDepartmentUserDTO;
    }

	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody CustomerDepartmentUserCreateDTO customerDepartmentUserCreate, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
            CustomerDepartmentUser customerDepartmentUserModel = this.modelMapper.map(customerDepartmentUserCreate,CustomerDepartmentUser.class);
            customerDepartmentUserModel.setId(null);
			service.saveOrUpdate(customerDepartmentUserModel);
			String createdLink = "/api/customer/department/user/"+customerDepartmentUserModel.getId();
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
			return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
		}
    }
    
    @PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody CustomerDepartmentUserUpdateDTO customerDepartmentUserUpdate, Errors error) {
		if(error.hasErrors())   
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<CustomerDepartmentUser> customerDepartmentUserRecord = service.get(customerDepartmentUserUpdate.getId());
			CustomerDepartmentUser customerDepartmentUserModel=customerDepartmentUserRecord.orElseThrow();
			
			this.modelMapper
				.map(customerDepartmentUserUpdate,customerDepartmentUserModel);

			service.saveOrUpdate(customerDepartmentUserModel);
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
			return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
		}
	}
}