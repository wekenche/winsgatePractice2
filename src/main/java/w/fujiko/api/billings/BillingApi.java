package w.fujiko.api.billings;

import static w.fujiko.util.common.constants.BillingConstants.MSG_500;
import static w.fujiko.util.common.constants.BillingConstants.MSG_CONFLICT_BILLING;

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
import w.fujiko.dto.billings.BillingCreateDTO;
import w.fujiko.dto.billings.BillingDTO;
import w.fujiko.dto.billings.BillingUpdateDTO;
import w.fujiko.model.masters.billings.Billing;
import w.fujiko.service.billings.BillingService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.billings.CreateDTOToBillingEntityMap;
import w.fujiko.util.mapper.propertymaps.billings.UpdateDTOToBillingEntityMap;


@RestController
@RequestMapping("/api/billing")
public class BillingApi
	extends Api<BillingService,Billing,Integer> {
	
	private ModelMapper modelMapper;
	
	public BillingApi(){
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setAmbiguityIgnored(true);			
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Billing> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
        Billing billing = response.get();

		BillingDTO billingDTO = this.modelMapper
										  .map(billing,BillingDTO.class);
		return ResponseEntity
				.ok(billingDTO);
	}

	@GetMapping("/dto")
	public @ResponseBody List<BillingDTO> getDTO() {
		
		List<Billing> billingModel = service.get();
		
		Type listType = new TypeToken<List<BillingDTO>>() {}.getType();

		List<BillingDTO> billingsDTO = this.modelMapper
										  .map(billingModel,listType);

		return billingsDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<BillingDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Billing> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<BillingDTO> pageDTO = new Page<BillingDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<BillingDTO>>() {}.getType();

		List<BillingDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createBilling(@Valid @RequestBody BillingCreateDTO billingDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Billing> billingResult = service.getUndeletedBillingByCode(billingDTO.getCode());
			if(billingResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_BILLING));
			} else {
				var modelMapper = new ModelMapper();
				modelMapper.getConfiguration().setAmbiguityIgnored(true);
				modelMapper.addMappings(new CreateDTOToBillingEntityMap());

				Billing billingEntity = modelMapper.map(billingDTO, Billing.class);
				service.saveOrUpdate(billingEntity);
				String createdLink = "/api/billing/"+billingEntity.getId();
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
	public ResponseEntity<?> updateBranch(@Valid @RequestBody BillingUpdateDTO billingDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			var modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			modelMapper.addMappings(new UpdateDTOToBillingEntityMap());
			
			Billing billingEntity = service.get(billingDTO.getId()).orElseThrow();
			
			modelMapper.map(billingDTO, billingEntity);
			service.saveOrUpdate(billingEntity);
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