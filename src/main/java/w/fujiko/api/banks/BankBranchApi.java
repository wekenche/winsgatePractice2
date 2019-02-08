package w.fujiko.api.banks;

import static w.fujiko.util.common.constants.BankConstants.MSG_500;
import static w.fujiko.util.common.constants.BankConstants.MSG_CONFLICT_BRANCH;

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
import w.fujiko.dto.banks.BankBranchCreateDTO;
import w.fujiko.dto.banks.BankBranchDTO;
import w.fujiko.dto.banks.BankBranchUpdateDTO;
import w.fujiko.model.masters.banks.BankBranch;
import w.fujiko.service.banks.BankBranchService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/bank/branches")
public class BankBranchApi extends Api<BankBranchService, BankBranch, Integer> {
	
	private ModelMapper modelMapper;
	
	public BankBranchApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}

	@GetMapping("/dto")
	public @ResponseBody List<BankBranchDTO> getDTO() {
		
		List<BankBranch> bankBranchModel = service.get();
		
		Type listType = new TypeToken<List<BankBranchDTO>>() {}.getType();

		List<BankBranchDTO> bankBranchDTO = this.modelMapper
										  .map(bankBranchModel,listType);

		return bankBranchDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<BankBranchDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<BankBranch> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<BankBranchDTO> pageDTO = new Page<BankBranchDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<BankBranchDTO>>() {}.getType();

		List<BankBranchDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<BankBranch> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            BankBranch bankBranch = response.get();

		
        BankBranchDTO bankBranchDTO = this.modelMapper
										  .map(bankBranch,BankBranchDTO.class);

		return ResponseEntity
				.ok(bankBranchDTO);
	}

	@GetMapping("/{bankId}/list")
	public ResponseEntity<?> getBranchesByBankId(
		@PathVariable(value="bankId") String bankId,
		@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			Integer id = Integer.parseInt(bankId);
			Integer indexNo = Integer.parseInt(index);
			List<BankBranch> entities = service.getBranchesByBankId(id, indexNo);
			
			Type listType = new TypeToken<List<BankBranchDTO>>() {}.getType();
			List<BankBranchDTO> bankBranchesDTO = this.modelMapper.map(entities, listType);
			
			return ResponseEntity.ok().body(bankBranchesDTO);
		} catch(Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}		

	}
	
	@GetMapping("/{bankId}/list/search")
	public ResponseEntity<?> getBranchesByNameOrCode(
		@PathVariable(value="bankId") Integer bankId,
		@RequestParam(value = "key", defaultValue = "") String key,
		@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			Integer idx = Integer.parseInt(index);
			List<BankBranch> entities = service.getBranchesByNameOrCode(bankId, key, idx);
			
			Type listType = new TypeToken<List<BankBranchDTO>>() {}.getType();
			List<BankBranchDTO> bankBranchesDTO = this.modelMapper.map(entities, listType);
			
			return ResponseEntity.ok().body(bankBranchesDTO);
		} catch(Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createBranch(@Valid @RequestBody BankBranchCreateDTO branchDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			int bankId = branchDTO.getBankId();
			Optional<BankBranch> branchResult = service.getUndeletedBranchByCode(bankId, branchDTO.getBankBranchCode());
			if(branchResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_BRANCH));
			} else {
				BankBranch branchEntity = this.modelMapper.map(branchDTO, BankBranch.class);
				service.saveOrUpdate(branchEntity);
				String createdLink = "/api/bank/branches/"+branchEntity.getId();
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
	public ResponseEntity<?> updateBranch(@Valid @RequestBody BankBranchUpdateDTO branchDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			BankBranch branchEntity = service.get(branchDTO.getBankId()).orElseThrow();
			this.modelMapper.map(branchDTO, branchEntity);
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
	
}