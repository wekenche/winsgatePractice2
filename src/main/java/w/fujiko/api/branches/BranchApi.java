package w.fujiko.api.branches;

import static w.fujiko.util.common.constants.BranchConstants.MSG_500;
import static w.fujiko.util.common.constants.BranchConstants.MSG_CONFLICT_BRANCH;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
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
import w.fujiko.dto.branches.BranchCreateDTO;
import w.fujiko.dto.branches.BranchDTO;
import w.fujiko.dto.branches.BranchDTODefault;
import w.fujiko.dto.branches.BranchUpdateDTO;
import w.fujiko.dto.departments.DepartmentDTO;
import w.fujiko.model.masters.branches.Branch;
import w.fujiko.service.branches.BranchService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.branches.UpdateDTOToBranchEntityMap;


@RestController
@RequestMapping("/api/branch")
public class BranchApi
	extends Api<BranchService,Branch,Integer> {
	
	private ModelMapper modelMapper;
	@Autowired
	private UpdateDTOToBranchEntityMap updateDTOToBranchEntityMap;

	public BranchApi(){
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setAmbiguityIgnored(true);			
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Branch> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
		Branch branch = response.get();

		
		BranchDTO branchDTO = this.modelMapper
										  .map(branch,BranchDTO.class);

		branchDTO
			.setDepartments(this.sortDepartmentsByCode(branchDTO.getDepartments()));

		return ResponseEntity
				.ok(branchDTO);
	}

	@GetMapping("/dto")
	public @ResponseBody List<BranchDTO> getDTO() {
		
		List<Branch> branchModel = service.get();
		
		Type listType = new TypeToken<List<BranchDTO>>() {}.getType();

		List<BranchDTO> branchesDTO = this.modelMapper
										  .map(branchModel,listType);
		
		
		branchesDTO.forEach(e->{
				e.setDepartments(this.sortDepartmentsByCode(e.getDepartments())						 
			);
		});

		return branchesDTO;
	}

	@GetMapping("/dto/{code}")
	public @ResponseBody ResponseEntity<?> getByCode(@PathVariable("code") final String code,
													@RequestParam(value="isEnd",required=false) final Boolean isEnd) {		
		
		try{
			Branch branchModel = service
								 .getByCode(code,Optional.ofNullable(isEnd))
								 .orElseThrow();		
			BranchDTODefault branchDTO = this.modelMapper
											 .map(branchModel,BranchDTODefault.class);
			return ResponseEntity.ok().body(branchDTO);
		}catch(NoSuchElementException ex){
			return ResponseEntity
            .notFound()
            .build();
		}
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<BranchDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Branch> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<BranchDTO> pageDTO = new Page<BranchDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<BranchDTO>>() {}.getType();

		List<BranchDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		resultsDTO.forEach(e->{
			e.setDepartments(this.sortDepartmentsByCode(e.getDepartments())
		);
	});
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@GetMapping("/dto/default")
	public @ResponseBody List<BranchDTODefault> getDTODefault() {
		
		List<Branch> branchModel = service.get();
		
		Type listType = new TypeToken<List<BranchDTODefault>>() {}.getType();

		List<BranchDTODefault> branchesDTO = this.modelMapper
										  .map(branchModel,listType);

		return branchesDTO;
	}
	
	@GetMapping("/dto/default/v2")
	public @ResponseBody Page<BranchDTODefault> paginateDefault(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Branch> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<BranchDTODefault> pageDTO = new Page<BranchDTODefault>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<BranchDTODefault>>() {}.getType();

		List<BranchDTODefault> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@GetMapping("/user/{userCode}")
	public @ResponseBody ResponseEntity<?> getBranchOfUser(
		@PathVariable(value="userCode") final Short userCode,
		@RequestParam(value="isEnd",defaultValue = "false") final Boolean isEnd) {
		try{
		var branchModel = service.getBranchOfUser(userCode,isEnd)
								 .orElseThrow();
		
		BranchDTODefault branchDTO = this.modelMapper
										.map(branchModel,BranchDTODefault.class);

		return ResponseEntity
			  .ok(branchDTO);
		}catch(NoResultException e) {
			return ResponseEntity
			  	   .notFound()
				   .build();
		 }
	}

	@PostMapping("/create")
	public ResponseEntity<?> createBranch(@Valid @RequestBody BranchCreateDTO branchDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Branch> branchResult = service.getUndeletedBranchByCode(branchDTO.getCode());
			if(branchResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_BRANCH));
			} else {
				Branch branchEntity = this.modelMapper.map(branchDTO, Branch.class);
				service.saveOrUpdate(branchEntity);
				String createdLink = "/api/branch/"+branchEntity.getId();
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
	public ResponseEntity<?> updateBranch(@Valid @RequestBody BranchUpdateDTO branchDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			var modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			modelMapper.addMappings(updateDTOToBranchEntityMap);

			Branch branchEntity = service.get(branchDTO.getId()).orElseThrow();
			modelMapper.map(branchDTO, branchEntity);
			service.saveOrUpdate(branchEntity);
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

	

	//sort department by code
	private List<DepartmentDTO> sortDepartmentsByCode(List<DepartmentDTO> departments){
		return departments
				.stream()
				.sorted((n1,n2)->Integer.valueOf(n1.getCode())
								.compareTo(Integer.valueOf(n2.getCode())))
								.collect(Collectors.toList());
	}
}