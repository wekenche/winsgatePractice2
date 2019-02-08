package w.fujiko.api.customers;

import static w.fujiko.util.common.constants.CustomerConstants.MSG_500;
import static w.fujiko.util.common.constants.CustomerConstants.MSG_CONFLICT_GROUP;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import fte.api.CustomerGroupUpdated;
import fte.api.Page;
import fte.api.Response;
import w.fujiko.dto.customers.CustomerGroupCreateDTO;
import w.fujiko.dto.customers.CustomerGroupDTO;
import w.fujiko.dto.customers.CustomerGroupUpdateDTO;
import w.fujiko.model.masters.customers.CustomerGroup;
import w.fujiko.service.customers.CustomerGroupService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/customer/group")
public class CustomerGroupApi
	extends Api<CustomerGroupService,CustomerGroup,Integer> {
	
	private ModelMapper modelMapper;
	
	public CustomerGroupApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<CustomerGroupDTO> getDTO() {
		
		List<CustomerGroup> customerGroupModel = service.get();
		
		Type listType = new TypeToken<List<CustomerGroupDTO>>() {}.getType();

		List<CustomerGroupDTO> logDTO = this.modelMapper
										  .map(customerGroupModel,listType);

		return logDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<CustomerGroupDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<CustomerGroup> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<CustomerGroupDTO> pageDTO = new Page<CustomerGroupDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<CustomerGroupDTO>>() {}.getType();

		List<CustomerGroupDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@GetMapping("/user/{userId}")
	public @ResponseBody List<CustomerGroupDTO> getDTO(@PathVariable("userId")final Integer userId) {
		
		List<CustomerGroup> customerGroupModel = service.getByUserId(userId);
		
		Type listType = new TypeToken<List<CustomerGroupDTO>>() {}.getType();

		List<CustomerGroupDTO> logDTO = this.modelMapper
										    .map(customerGroupModel,listType);

		return logDTO;
	}

	@GetMapping("/dto/paginate/{first}/{max}")
	public @ResponseBody Page<CustomerGroupDTO> paginate(@PathVariable(value="first") final Integer first, @PathVariable(value="max") final Integer max) {
		
		Page<CustomerGroup> customerGroup = service.paginate(first, max);

		Page<CustomerGroupDTO> paginatedCustomerGroup = new Page<CustomerGroupDTO>();
		paginatedCustomerGroup.setFirst(customerGroup.getFirst());
		paginatedCustomerGroup.setMax(customerGroup.getMax());
		paginatedCustomerGroup.setTotal(customerGroup.getTotal());
		paginatedCustomerGroup.setSuccess(customerGroup.getSuccess());		

		Type listType = new TypeToken<List<CustomerGroupDTO>>() {}.getType();

		List<CustomerGroupDTO> customerGroupDTOs = this.modelMapper
										  .map(customerGroup.getResults(),listType);
        paginatedCustomerGroup.setResults(customerGroupDTOs);
		return paginatedCustomerGroup;
	}

	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody CustomerGroupCreateDTO customerGroupCreateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
			Optional<CustomerGroup> customerGroupResult = service.getUndeletedGroupByCode(customerGroupCreateDTO.getCode());
			if(customerGroupResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_GROUP));
			} else {
				CustomerGroup customerGroupEntity = this.modelMapper.map(customerGroupCreateDTO, CustomerGroup.class);
				customerGroupEntity = this.createScorePhases(customerGroupEntity);
				service.saveOrUpdate(customerGroupEntity);
				String createdLink = "/api/customer/group/"+customerGroupEntity.getId();
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
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody CustomerGroupUpdateDTO customerGroupUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
			CustomerGroup customerGroupEntity = service.get(customerGroupUpdateDTO.getId()).orElseThrow();
			customerGroupEntity = this.updateScorePhases(customerGroupEntity, customerGroupUpdateDTO);

			this.modelMapper.map(customerGroupUpdateDTO, customerGroupEntity);
			
			service.saveOrUpdate(customerGroupEntity);
			return ResponseEntity.ok().body(new CustomerGroupUpdated(customerGroupEntity.getScore1Phase(),
																	 customerGroupEntity.getScore2Phase(),
																	 customerGroupEntity.getScore3Phase()));
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

	private CustomerGroup createScorePhases(CustomerGroup customerGroup){
		CustomerGroup updatedCustomerGroup = customerGroup;
		Date dateCreated = new Date(); 
		if(!this.isStringNull(updatedCustomerGroup.getScore1()))
			updatedCustomerGroup.setScore1Phase(dateCreated);
		if(!this.isStringNull(updatedCustomerGroup.getScore2()))
			updatedCustomerGroup.setScore2Phase(dateCreated);
		if(!this.isStringNull(updatedCustomerGroup.getScore3()))
			updatedCustomerGroup.setScore3Phase(dateCreated);
		
		return updatedCustomerGroup;
	}

	private CustomerGroup updateScorePhases(CustomerGroup pastdata,CustomerGroupUpdateDTO currentdata){
		CustomerGroup updatedCustomerGroup = pastdata;
		Date dateCreated = new Date();
		
		if(!this.isEquals(pastdata.getScore1(),currentdata.getScore1()))
			updatedCustomerGroup.setScore1Phase(dateCreated); //update scorePhase1
		if(!this.isEquals(pastdata.getScore2(),currentdata.getScore2()))
			updatedCustomerGroup.setScore2Phase(dateCreated); //update scorePhase2		
		if(!this.isEquals(pastdata.getScore3(),currentdata.getScore3()))
			updatedCustomerGroup.setScore3Phase(dateCreated);//update scorePhase3		
		
		return updatedCustomerGroup;
	}

	private boolean isStringNull(String stringToCheck){		
		return StringUtils.isBlank(stringToCheck);
	}

	private boolean isEquals(String string1,String string2) {
		string1 = (string1 == "" ? null : string1);
		string2 = (string2 == "" ? null : string2);
		return StringUtils.equals(string1,string2);
	}
}