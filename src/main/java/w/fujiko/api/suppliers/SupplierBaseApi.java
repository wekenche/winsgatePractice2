package w.fujiko.api.suppliers;

import static w.fujiko.util.common.constants.SupplierConstants.MSG_CONFLICT_BASE;
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
import w.fujiko.dto.suppliers.SupplierBaseCreateDTO;
import w.fujiko.dto.suppliers.SupplierBaseDTO;
import w.fujiko.dto.suppliers.SupplierBaseUpdateDTO;
import w.fujiko.model.masters.suppliers.SupplierBase;
import w.fujiko.service.suppliers.SupplierBaseService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.suppliers.UpdateDTOToSupplierBase;

@RestController
@RequestMapping("/api/supplier/base")
public class SupplierBaseApi extends Api<SupplierBaseService, SupplierBase,Integer> {
	
private ModelMapper modelMapper;
	
	public SupplierBaseApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<SupplierBase> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
		SupplierBase supplierBase = response.get();
		SupplierBaseDTO supplierBaseDTO = this.modelMapper
									  .map(supplierBase, SupplierBaseDTO.class);

		return ResponseEntity
				.ok(supplierBaseDTO);
	}

	@GetMapping("/code/{code}")
	public @ResponseBody ResponseEntity<?> getByCode(@PathVariable(value="code") final String code,
													 @RequestParam(value="groupCode",required=false)final String groupCode,
													 @RequestParam(value="isEnd",required=false)final Boolean isEnd) {
	    try{
			var supplierBase = service
							   .getByCode(code,Optional.ofNullable(groupCode),Optional.ofNullable(isEnd))
							   .orElseThrow();
			
			var supplierBaseDTO = this.modelMapper
									  .map(supplierBase,SupplierBaseDTO.class);

			return ResponseEntity
					.ok(supplierBaseDTO);

		}catch(NoSuchElementException ex){
			return ResponseEntity
				   .notFound()
				   .build();
		}
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<SupplierBaseDTO> getDTO() {
		
		List<SupplierBase> supplierBaseModel = service.get();
		
		Type listType = new TypeToken<List<SupplierBaseDTO>>() {}.getType();

		List<SupplierBaseDTO> supplierBaseDTO = this.modelMapper
										  			.map(supplierBaseModel,listType);

		return supplierBaseDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<SupplierBaseDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<SupplierBase> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<SupplierBaseDTO> pageDTO = new Page<SupplierBaseDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<SupplierBaseDTO>>() {}.getType();

		List<SupplierBaseDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/user/{userId}")
	public @ResponseBody List<SupplierBaseDTO> getDTO(@PathVariable("userId")final Integer userId) {
		
		List<SupplierBase> supplierBaseModel = service.getByUserId(userId);
		
		Type listType = new TypeToken<List<SupplierBaseDTO>>() {}.getType();

		List<SupplierBaseDTO> supplierBaseDTO = this.modelMapper
										 			.map(supplierBaseModel,listType);

		return supplierBaseDTO;
	}
	
	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody SupplierBaseCreateDTO supplierBaseCreate, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
			int groupId = supplierBaseCreate.getGroupId();
			Optional<SupplierBase> supplierResult = service.getUndeletedSupplierBaseByCode(groupId, supplierBaseCreate.getCode());
			if(supplierResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_BASE));
			} else {
				SupplierBase supplierEntity = this.modelMapper.map(supplierBaseCreate, SupplierBase.class);
				if(supplierBaseCreate.getBranchId()==null) supplierEntity.setBranch(null);				
				service.saveOrUpdate(supplierEntity);
				String createdLink = "/api/supplier/base/"+supplierEntity.getId();
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
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody SupplierBaseUpdateDTO supplierBaseUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			SupplierBase supplierEntity = service.get(supplierBaseUpdateDTO.getId()).orElseThrow();
			if(supplierBaseUpdateDTO.getBranchId() == null) 
				supplierEntity.setBranch(null);
			ModelMapper modelMapper = new ModelMapper();			
			modelMapper.addMappings(new UpdateDTOToSupplierBase());
			modelMapper.map(supplierBaseUpdateDTO, supplierEntity);
			service.saveOrUpdate(supplierEntity);
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
