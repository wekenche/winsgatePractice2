package w.fujiko.api.suppliers;

import static w.fujiko.util.common.constants.SupplierConstants.MSG_CONFLICT_DEPARTMENT;
import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;

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
import w.fujiko.dto.suppliers.departments.SupplierDepartmentCreateDTO;
import w.fujiko.dto.suppliers.departments.SupplierDepartmentDTO;
import w.fujiko.dto.suppliers.departments.SupplierDepartmentUpdateDTO;
import w.fujiko.model.masters.suppliers.SupplierDepartment;
import w.fujiko.service.suppliers.departments.SupplierDepartmentService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/supplier/department")
public class SupplierDepartmentApi 
	extends Api<SupplierDepartmentService, SupplierDepartment, Integer> {
	
	private ModelMapper modelMapper;
	
	public SupplierDepartmentApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<SupplierDepartment> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();

		SupplierDepartment supplierDepartment = response.get();
		SupplierDepartmentDTO supplierDepartmentDTO = this.modelMapper
										                  .map(supplierDepartment, SupplierDepartmentDTO.class);

		return ResponseEntity
				.ok(supplierDepartmentDTO);
	}
	
	@GetMapping("/base/{baseId}")
	public @ResponseBody List<SupplierDepartmentDTO> getByBaseCode(@PathVariable(value="baseId") final int baseId) {
		
		List<SupplierDepartment> supplierDepartmentModel = service.getByBaseId(baseId);
		
		Type listType = new TypeToken<List<SupplierDepartmentDTO>>() {}.getType();
		List<SupplierDepartmentDTO> supplierDepartmentDTO = this.modelMapper
										     .map(supplierDepartmentModel,listType);

		return supplierDepartmentDTO;
    }
	
	@GetMapping("/dto")
	public @ResponseBody List<SupplierDepartmentDTO> getDTO() {
		
		List<SupplierDepartment> supplierDepartmentModel = service.get();
		
		Type listType = new TypeToken<List<SupplierDepartmentDTO>>() {}.getType();

		List<SupplierDepartmentDTO> supplierDepartmentDTO = this.modelMapper
										     .map(supplierDepartmentModel, listType);

		return supplierDepartmentDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<SupplierDepartmentDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<SupplierDepartment> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<SupplierDepartmentDTO> pageDTO = new Page<SupplierDepartmentDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<SupplierDepartmentDTO>>() {}.getType();

		List<SupplierDepartmentDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody SupplierDepartmentCreateDTO supplierDepartmentCreate, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			int baseId = supplierDepartmentCreate.getBaseId();
			Optional<SupplierDepartment> departmentResult = service.getSupplierDepartmentByCode(baseId, supplierDepartmentCreate.getCode());
			if(departmentResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_DEPARTMENT));
			} else {
				SupplierDepartment departmentEntity = this.modelMapper.map(supplierDepartmentCreate, SupplierDepartment.class);
				service.saveOrUpdate(departmentEntity);
				String createdLink = "/api/supplier/department/"+departmentEntity.getId();
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
	public @ResponseBody ResponseEntity<?> update(@Valid @RequestBody SupplierDepartmentUpdateDTO supplierDepartmentUpdate, Errors error) {
		if(error.hasErrors())   
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			SupplierDepartment supplierDepartmentEntity = service.get(supplierDepartmentUpdate.getId()).orElseThrow();
			this.modelMapper.map(supplierDepartmentUpdate, supplierDepartmentEntity);
			service.saveOrUpdate(supplierDepartmentEntity);
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