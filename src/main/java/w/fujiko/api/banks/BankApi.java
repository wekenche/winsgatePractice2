package w.fujiko.api.banks;

import static w.fujiko.util.common.constants.BankConstants.MSG_500;
import static w.fujiko.util.common.constants.BankConstants.MSG_CONFLICT_BANK;

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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import w.fujiko.dto.banks.BankCreateDTO;
import w.fujiko.dto.banks.BankDTO;
import w.fujiko.dto.banks.BankUpdateDTO;
import w.fujiko.model.masters.banks.Bank;
import w.fujiko.service.banks.BankService;
import w.fujiko.util.common.constants.BankConstants;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/bank")
public class BankApi extends Api<BankService, Bank, Integer> {
	
	private ModelMapper modelMapper;
	
	public BankApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}

	@GetMapping("/dto")
	public @ResponseBody List<BankDTO> getDTO() {
		
		List<Bank> bankModel = service.get();
		
		Type listType = new TypeToken<List<BankDTO>>() {}.getType();

		List<BankDTO> bankDTO = this.modelMapper
										  .map(bankModel,listType);

		return bankDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<BankDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Bank> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<BankDTO> pageDTO = new Page<BankDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<BankDTO>>() {}.getType();

		List<BankDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Bank> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
        Bank bank = response.get();

		BankDTO bankDTO = this.modelMapper
										  .map(bank,BankDTO.class);
		return ResponseEntity
				.ok(bankDTO);
	}

	@GetMapping("/list/search")
	public ResponseEntity<?> getBanksByNameOrCode(
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			List<Bank> entities = 
					service.getBanksByNameOrCode(
							key, Integer.parseInt(index));
			
			Type listType = new TypeToken<List<BankDTO>>() {}.getType();
			List<BankDTO> banksDTO = this.modelMapper.map(entities,listType);
			
			return ResponseEntity.ok().body(banksDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
				
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createBank(@Valid @RequestBody BankCreateDTO bankDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Bank> bankResult = service.getUndeletedBankByCode(bankDTO.getBankCode());
			if(bankResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_BANK));
			} else {
				Bank bankEntity = this.modelMapper.map(bankDTO, Bank.class);
				service.saveOrUpdate(bankEntity);
				String createdLink = "/api/bank/"+bankEntity.getId();
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
	public ResponseEntity<?> updateBank(@Valid @RequestBody BankUpdateDTO bankDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Bank bankEntity = service.get(bankDTO.getId()).orElseThrow();
			this.modelMapper.map(bankDTO, bankEntity);
			service.saveOrUpdate(bankEntity);
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
	
	@GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> generatePDF(
			@RequestParam(value = "from", defaultValue = "") String from,
			@RequestParam(value = "to", defaultValue = "") String to) {
				
		try {			
			HttpHeaders headers = getHeaders(BankConstants.PDF_FILE_NAME);
			int codeFrom = Integer.parseInt(from);
			int codeTo = Integer.parseInt(to);
			return ResponseEntity.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(service.exportToPDF(codeFrom, codeTo)));
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		} 
		
	}
	
	@GetMapping(value = "/export-csv", produces = "text/csv")
	public ResponseEntity<?> generateCSV(
			@RequestParam(value = "from", defaultValue = "") String from,
			@RequestParam(value = "to", defaultValue = "") String to) {
					
		try {
			HttpHeaders headers = getHeaders(BankConstants.CSV_FILE_NAME);
			int codeFrom = Integer.parseInt(from);
			int codeTo = Integer.parseInt(to);
			return ResponseEntity.ok()
					.headers(headers)
					.body(service.exportToCSV(codeFrom, codeTo));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}
	
	private HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}
		
}