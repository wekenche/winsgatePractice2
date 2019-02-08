package w.fujiko.api.customers;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static w.fujiko.util.common.constants.CustomerConstants.MSG_CONFLICT_CUSTOMER_DEPARTMENT;
import static w.fujiko.util.common.constants.CustomerConstants.MSG_500;

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

import w.fujiko.dto.customers.departments.CustomerDepartmentCreateDTO;
import w.fujiko.dto.customers.departments.CustomerDepartmentDTO;
import w.fujiko.dto.customers.departments.CustomerDepartmentUpdateDTO;
import w.fujiko.model.masters.customers.CustomerDepartment;
import w.fujiko.service.customers.departments.CustomerDepartmentService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/customer/department")
public class CustomerDepartmentApi
	extends Api<CustomerDepartmentService,CustomerDepartment,Integer> {
	
	private ModelMapper modelMapper;
	
	public CustomerDepartmentApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<CustomerDepartment> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            CustomerDepartment customerDepartment = response.get();

		
			CustomerDepartmentDTO customerDepartmentDTO = this.modelMapper
										                  .map(customerDepartment,CustomerDepartmentDTO.class);

		return ResponseEntity
				.ok(customerDepartmentDTO);
	}

    @GetMapping("/dto")
	public @ResponseBody List<CustomerDepartmentDTO> getDTO() {
		
		List<CustomerDepartment> customerDepartmentModel = service.get();
		
		Type listType = new TypeToken<List<CustomerDepartmentDTO>>() {}.getType();

		List<CustomerDepartmentDTO> customerDepartmentDTO = this.modelMapper
										     .map(customerDepartmentModel,listType);

		return customerDepartmentDTO;
    }

    @GetMapping("/dto/paginate/{first}/{max}")
	public @ResponseBody Page<CustomerDepartmentDTO> paginate(@PathVariable(value="first") final Integer first, @PathVariable(value="max") final Integer max) {
		
		Page<CustomerDepartment> customerDepartment = service.paginate(first, max);

		Page<CustomerDepartmentDTO> paginatedCustomerDepartment = new Page<CustomerDepartmentDTO>();
		paginatedCustomerDepartment.setFirst(customerDepartment.getFirst());
		paginatedCustomerDepartment.setMax(customerDepartment.getMax());
		paginatedCustomerDepartment.setTotal(customerDepartment.getTotal());
		paginatedCustomerDepartment.setSuccess(customerDepartment.getSuccess());		

		Type listType = new TypeToken<List<CustomerDepartmentDTO>>() {}.getType();

		List<CustomerDepartmentDTO> customerDepartmentDTOs = this.modelMapper
										                     .map(customerDepartment.getResults(),listType);
        paginatedCustomerDepartment.setResults(customerDepartmentDTOs);
		return paginatedCustomerDepartment;
	}

    
	@GetMapping("/base/{baseId}")
	public @ResponseBody List<CustomerDepartmentDTO> getByBaseCode(@PathVariable(value="baseId") final int baseId) {
		
		List<CustomerDepartment> customerDepartmentModel = service.getByBaseId(baseId);
		
		Type listType = new TypeToken<List<CustomerDepartmentDTO>>() {}.getType();

		List<CustomerDepartmentDTO> customerDepartmentDTO = this.modelMapper
										     .map(customerDepartmentModel,listType);

		return customerDepartmentDTO;
    }

	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody CustomerDepartmentCreateDTO customerDepartmentCreate, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			int baseId = customerDepartmentCreate.getBaseId();
			Optional<CustomerDepartment> departmentResult = service.getCustomerDepartmentByCode(baseId, customerDepartmentCreate.getCode());
			if(departmentResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_CUSTOMER_DEPARTMENT));
			} else {
				CustomerDepartment departmentEntity = this.modelMapper.map(customerDepartmentCreate, CustomerDepartment.class);
				service.saveOrUpdate(departmentEntity);
				String createdLink = "/api/customer/department/"+departmentEntity.getId();
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
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(StringConverter.convertToReadable(MSG_500));
		}
    }
    
    @PutMapping("/update")
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody CustomerDepartmentUpdateDTO customerDepartmentUpdate, Errors error) {
		if(error.hasErrors())   
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			CustomerDepartment customerDepartmentEntity = service.get(customerDepartmentUpdate.getId()).orElseThrow();
			this.modelMapper.map(customerDepartmentUpdate, customerDepartmentEntity);
			service.saveOrUpdate(customerDepartmentEntity);
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
			return ResponseEntity.badRequest().body(StringConverter.convertToReadable(MSG_500));
		}
	}
}