package w.fujiko.api.suppliers;

import static w.fujiko.util.common.constants.SupplierConstants.MSG_CONFLICT_GROUP;
import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;

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
import fte.api.Page;
import fte.api.Response;
import fte.api.SupplierGroupUpdated;
import w.fujiko.dto.suppliers.SupplierGroupCreateDTO;
import w.fujiko.dto.suppliers.SupplierGroupDTO;
import w.fujiko.dto.suppliers.SupplierGroupUpdateDTO;
import w.fujiko.model.masters.suppliers.SupplierGroup;
import w.fujiko.service.suppliers.SupplierGroupService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/supplier/group")
public class SupplierGroupApi extends Api<SupplierGroupService, SupplierGroup,Integer> {

	private ModelMapper modelMapper;
	
	public SupplierGroupApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<SupplierGroupDTO> getDTO() {
		
		List<SupplierGroup> supplierGroupModel = service.get();
		Type listType = new TypeToken<List<SupplierGroupDTO>>() {}.getType();

		List<SupplierGroupDTO> supplierDTO = this.modelMapper
										  .map(supplierGroupModel,listType);

		return supplierDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<SupplierGroupDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<SupplierGroup> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<SupplierGroupDTO> pageDTO = new Page<SupplierGroupDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<SupplierGroupDTO>>() {}.getType();

		List<SupplierGroupDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/user/{userId}")
	public @ResponseBody List<SupplierGroupDTO> getDTO(@PathVariable("userId")final Integer userId) {
		
		List<SupplierGroup> supplierGroupModel = service.getByUserId(userId);
		
		Type listType = new TypeToken<List<SupplierGroupDTO>>() {}.getType();

		List<SupplierGroupDTO> supplierDTO = this.modelMapper
										    .map(supplierGroupModel,listType);

		return supplierDTO;
	}
	
	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody SupplierGroupCreateDTO supplierGroupCreateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
			Optional<SupplierGroup> supplierGroupResult = service.getUndeletedSupplierGroupByCode(supplierGroupCreateDTO.getCode());
			if(supplierGroupResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_GROUP));
			} else {
				SupplierGroup supplierGroupEntity = this.modelMapper.map(supplierGroupCreateDTO, SupplierGroup.class);
				supplierGroupEntity = this.createScorePhases(supplierGroupEntity);
				service.saveOrUpdate(supplierGroupEntity);
				String createdLink = "/api/supplier/group/"+supplierGroupEntity.getId();
				return ResponseEntity
					  .created(new URI(createdLink))
					  .body(new Created(createdLink));
			}

		}catch(ConstraintViolationException cve){
			cve.printStackTrace();
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
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody SupplierGroupUpdateDTO supplierGroupUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
			SupplierGroup supplierGroupEntity = service.get(supplierGroupUpdateDTO.getId()).orElseThrow();
			supplierGroupEntity = this.updateScorePhases(supplierGroupEntity, supplierGroupUpdateDTO);

			this.modelMapper.map(supplierGroupUpdateDTO, supplierGroupEntity);
			
			service.saveOrUpdate(supplierGroupEntity);
			return ResponseEntity.ok().body(new SupplierGroupUpdated(supplierGroupEntity.getScore1Phase(),
																	supplierGroupEntity.getScore2Phase(),
																	supplierGroupEntity.getScore3Phase()));
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
	
	private SupplierGroup createScorePhases(SupplierGroup supplierGroup){
		SupplierGroup updatedSupplierGroup = supplierGroup;
		Date dateCreated = new Date(); 
		if(!this.isStringNull(updatedSupplierGroup.getScore1()))
			updatedSupplierGroup.setScore1Phase(dateCreated);
		if(!this.isStringNull(updatedSupplierGroup.getScore2()))
			updatedSupplierGroup.setScore2Phase(dateCreated);
		if(!this.isStringNull(updatedSupplierGroup.getScore3()))
			updatedSupplierGroup.setScore3Phase(dateCreated);
		
		return updatedSupplierGroup;
	}

	private SupplierGroup updateScorePhases(SupplierGroup pastdata, SupplierGroupUpdateDTO currentdata){
		SupplierGroup updatedSupplierGroup = pastdata;
		Date dateCreated = new Date();
		
		if(!this.isEquals(pastdata.getScore1(),currentdata.getScore1()))
			updatedSupplierGroup.setScore1Phase(dateCreated); //update scorePhase1
		if(!this.isEquals(pastdata.getScore2(),currentdata.getScore2()))
			updatedSupplierGroup.setScore2Phase(dateCreated); //update scorePhase2		
		if(!this.isEquals(pastdata.getScore3(),currentdata.getScore3()))
			updatedSupplierGroup.setScore3Phase(dateCreated);//update scorePhase3		
		
		return updatedSupplierGroup;
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