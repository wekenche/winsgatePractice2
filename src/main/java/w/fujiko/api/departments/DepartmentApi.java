package w.fujiko.api.departments;

import static w.fujiko.util.common.constants.DepartmentConstants.MSG_500;
import static w.fujiko.util.common.constants.DepartmentConstants.MSG_CONFLICT_DEPARTMENT;

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
import w.fujiko.dto.departments.DepartmentCreateDTO;
import w.fujiko.dto.departments.DepartmentDTO;
import w.fujiko.dto.departments.DepartmentDTODefault;
import w.fujiko.dto.departments.DepartmentUpdateDTO;
import w.fujiko.model.masters.departments.Department;
import w.fujiko.model.wrappers.DepartmentIdWrapper;
import w.fujiko.service.departments.DepartmentService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/department")
public class DepartmentApi
	extends Api<DepartmentService,Department,Integer> {
	
	private ModelMapper modelMapper;
	
	public DepartmentApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<DepartmentDTODefault> getDTO() {
		
		List<Department> departmentModel = service.get();
		
		Type listType = new TypeToken<List<DepartmentDTODefault>>() {}.getType();

		List<DepartmentDTODefault> departmentsDTO = this.modelMapper
										  .map(departmentModel,listType);

		return departmentsDTO;
	}

	@GetMapping("/code/{code}")
	public @ResponseBody DepartmentDTODefault getByCode(@PathVariable("code") final String code,
														@RequestParam(value="isEnd",required=false)final Boolean isEnd) {		
		Department departmentModel = service
									 .getDepartmentByCode(code,Optional.ofNullable(isEnd))
									 .orElseThrow();		

		DepartmentDTODefault departmentsDTO = this.modelMapper
								  				  .map(departmentModel,DepartmentDTODefault.class);

		return departmentsDTO;
	}

	@GetMapping("/dto/v2")
	public @ResponseBody Page<DepartmentDTODefault> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Department> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<DepartmentDTODefault> pageDTO = new Page<DepartmentDTODefault>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<DepartmentDTODefault>>() {}.getType();

		List<DepartmentDTODefault> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Department> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
		Department department = response.get();

		
		DepartmentDTO departmentDTO = this.modelMapper
										  .map(department,DepartmentDTO.class);

		return ResponseEntity
				.ok(departmentDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentCreateDTO departmentDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Department> departmentResult = 
					service.getUndeletedDepartmentByCode(departmentDTO.getBranchId(),
							departmentDTO.getParentDepartmentId(), departmentDTO.getCode());
			
			if(departmentResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_DEPARTMENT));
			} else {
				Department departmentEntity = this.modelMapper.map(departmentDTO, Department.class);
				departmentEntity.setCode(departmentDTO.getCode());
				if(departmentDTO.getParentDepartmentId() != null){
					Department parentDepartment = new Department();
					parentDepartment.setId(departmentDTO.getParentDepartmentId());
					departmentEntity.setParentDepartment(parentDepartment);
				}
				service.saveOrUpdate(departmentEntity);
				String createdLink = "/api/department/"+departmentEntity.getId();
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
	public ResponseEntity<?> updateDeparment(@Valid @RequestBody DepartmentUpdateDTO departmentDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Department departmentData = service.get(departmentDTO.getId()).orElseThrow();

			this.modelMapper.map(departmentDTO, departmentData);				
			service.saveOrUpdate(departmentData);
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

	@PostMapping("/softDelete")
	public @ResponseBody ResponseEntity<?> saveAll(@Valid @RequestBody DepartmentIdWrapper wrapper, Errors error){
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());

		try { 
			service.softDeleteDepartmentsById(wrapper.getDepartmentIds());
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

	@GetMapping("/user/{userId}")
	public @ResponseBody DepartmentDTODefault getDepartmentOfUser(@PathVariable(value="userId") final Integer userId) {
		
		Department departmentModel = service.getDepartmentOfUser(userId)
											.orElseThrow();
		
		DepartmentDTODefault departmentsDTO = this.modelMapper
												  .map(departmentModel,DepartmentDTODefault.class);

		return departmentsDTO;
	}

}