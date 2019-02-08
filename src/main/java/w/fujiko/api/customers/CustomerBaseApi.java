package w.fujiko.api.customers;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static w.fujiko.util.common.constants.CustomerConstants.MSG_CONFLICT_CUSTOMER;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Created;
import fte.api.Response;
import fte.api.Success;
import fte.api.Page;
import w.fujiko.dto.customers.CustomerBaseCreateDTO;
import w.fujiko.dto.customers.CustomerBaseDTO;
import w.fujiko.dto.customers.CustomerBaseSearchResultDTO;
import w.fujiko.dto.customers.CustomerBaseUpdateDTO;
import w.fujiko.dto.customers.CustomerGroupDTO;
import w.fujiko.model.masters.customers.CustomerBase;
import w.fujiko.service.customers.CustomerBaseService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.customerbasemaps.UpdateDTOToCustomerBaseMap;

@RestController
@RequestMapping("/api/customer/base")
public class CustomerBaseApi
	extends Api<CustomerBaseService,CustomerBase,Integer> {
	
	private ModelMapper modelMapper;
	
	public CustomerBaseApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@Override
	@GetMapping("/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<CustomerBase> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            CustomerBase customerBase = response.get();

		
			CustomerBaseDTO customerBaseDTO = this.modelMapper
										  .map(customerBase,CustomerBaseDTO.class);

		return ResponseEntity
				.ok(customerBaseDTO);
	}

	@GetMapping("/code/{code}")
	public @ResponseBody ResponseEntity<?> getByCode(@PathVariable(value="code") final String code,
													 @RequestParam(value="groupCode",required=false)final String groupCode,
													 @RequestParam(value="isEnd",required=false)final Boolean isEnd) {
	    try{
			CustomerBase customerBase = service
										.getByCode(code,Optional.ofNullable(groupCode),Optional.ofNullable(isEnd))
										.orElseThrow();
			
			var customerBaseDTO = this.modelMapper
									.map(customerBase,CustomerBaseDTO.class);

			return ResponseEntity
					.ok(customerBaseDTO);

		}catch(NoSuchElementException ex){
			return ResponseEntity
				   .notFound()
				   .build();
		}
	}

	@GetMapping("/dto")
	public @ResponseBody List<CustomerBaseDTO> getDTO() {
		
		List<CustomerBase> customerBaseModel = service.get();
		
		Type listType = new TypeToken<List<CustomerBaseDTO>>() {}.getType();

		List<CustomerBaseDTO> customerBaseDTO = this.modelMapper
										  			.map(customerBaseModel,listType);

		return customerBaseDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<CustomerBaseDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<CustomerBase> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<CustomerBaseDTO> pageDTO = new Page<CustomerBaseDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<CustomerBaseDTO>>() {}.getType();

		List<CustomerBaseDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@GetMapping("/user/{userId}")
	public @ResponseBody List<CustomerBaseDTO> getDTO(@PathVariable("userId")final Integer userId) {
		
		List<CustomerBase> customerBaseModel = service.getByUserId(userId);
		
		Type listType = new TypeToken<List<CustomerBaseDTO>>() {}.getType();

		List<CustomerBaseDTO> customerBaseDTO = this.modelMapper
										 			.map(customerBaseModel,listType);

		return customerBaseDTO;
	}

	@GetMapping("/search")
	public @ResponseBody List<CustomerBaseSearchResultDTO> search(@RequestParam(value = "groupCode", required = false) final String groupCode,
																  @RequestParam(value = "baseCode", required = false) final String baseCode,
																  @RequestParam(value = "customerName", required = false) final String customerName,
																  @RequestParam(value = "userCode", required = false) final Short userCode) {
		
		var customerBaseModel = service
								.search(
									Optional.ofNullable(groupCode),
									Optional.ofNullable(baseCode),
									Optional.ofNullable(customerName),
									Optional.ofNullable(userCode)
								);
		
		Type listType = new TypeToken<List<CustomerBaseSearchResultDTO>>() {}.getType();

		List<CustomerBaseSearchResultDTO> customerBaseDTO = this.modelMapper
										 						.map(customerBaseModel,listType);

		return customerBaseDTO;
	}

	@GetMapping("/dto/paginate/{first}/{max}")
	public @ResponseBody Page<CustomerBaseDTO> paginate(@PathVariable(value="first") final Integer first, @PathVariable(value="max") final Integer max) {
		
		Page<CustomerBase> customerBase = service.paginate(first, max);

		Page<CustomerBaseDTO> paginatedCustomerBase = new Page<CustomerBaseDTO>();
		paginatedCustomerBase.setFirst(customerBase.getFirst());
		paginatedCustomerBase.setMax(customerBase.getMax());
		paginatedCustomerBase.setTotal(customerBase.getTotal());
		paginatedCustomerBase.setSuccess(customerBase.getSuccess());		

		Type listType = new TypeToken<List<CustomerGroupDTO>>() {}.getType();

		List<CustomerBaseDTO> customerBaseDTOs = this.modelMapper
										  .map(customerBase.getResults(),listType);
        paginatedCustomerBase.setResults(customerBaseDTOs);
		return paginatedCustomerBase;
	}

	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody CustomerBaseCreateDTO customerBaseCreate, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
			int groupId = customerBaseCreate.getGroupId();
			Optional<CustomerBase> customerResult = service.getUndeletedCustomerByCode(groupId, customerBaseCreate.getCode());
			if(customerResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_CUSTOMER));
			} else {
				CustomerBase customerEntity = this.modelMapper.map(customerBaseCreate, CustomerBase.class);
				if(customerBaseCreate.getBranchId()==null) customerEntity.setBranch(null);				
				service.saveOrUpdate(customerEntity);
				String createdLink = "/api/customer/base/"+customerEntity.getId();
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
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody CustomerBaseUpdateDTO customerBaseUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			CustomerBase customerEntity = service.get(customerBaseUpdateDTO.getId()).orElseThrow();
			
			if(customerBaseUpdateDTO.getBranchId()==null) 
				customerEntity.setBranch(null);
			ModelMapper modelMapper = new ModelMapper();			
			modelMapper.addMappings(new UpdateDTOToCustomerBaseMap());
			modelMapper.map(customerBaseUpdateDTO, customerEntity);
			
			service.saveOrUpdate(customerEntity);
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