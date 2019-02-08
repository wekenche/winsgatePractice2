package w.fujiko.api.payments;

import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;
import static w.fujiko.util.common.constants.PaymentConstants.MSG_CONFLICT_PAYMENT;

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
import w.fujiko.dto.payments.PaymentCreateDTO;
import w.fujiko.dto.payments.PaymentDTO;
import w.fujiko.dto.payments.PaymentUpdateDTO;
import w.fujiko.model.masters.payments.Payment;
import w.fujiko.service.payments.PaymentService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.payments.UpdateDTOToPaymentMap;

@RestController
@RequestMapping("/api/payment")
public class PaymentApi extends Api<PaymentService, Payment, Integer> {
	
	private ModelMapper modelMapper;
	
	public PaymentApi(){
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setAmbiguityIgnored(true);
	}
	
	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Payment> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
		Payment payment = response.get();

		PaymentDTO paymentDTO = this.modelMapper
										  .map(payment, PaymentDTO.class);
		return ResponseEntity
				.ok(paymentDTO);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<PaymentDTO> getDTO() {
		
		List<Payment> paymentModel = service.get();
		
		Type listType = new TypeToken<List<PaymentDTO>>() {}.getType();

		List<PaymentDTO> paymentsDTO = this.modelMapper
										  .map(paymentModel, listType);

		return paymentsDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<PaymentDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Payment> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<PaymentDTO> pageDTO = new Page<PaymentDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<PaymentDTO>>() {}.getType();

		List<PaymentDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentCreateDTO paymentDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Payment> paymentResult = service.getUndeletedPaymentByCode(paymentDTO.getCode());
			if(paymentResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_PAYMENT));
			} else {
				Payment paymentEntity = this.modelMapper.map(paymentDTO, Payment.class);
				service.saveOrUpdate(paymentEntity);
				String createdLink = "/api/payment/"+paymentEntity.getId();
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
	public ResponseEntity<?> updatePayment(@Valid @RequestBody PaymentUpdateDTO paymentDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Payment paymentEntity = service.get(paymentDTO.getId()).orElseThrow();
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.addMappings(new UpdateDTOToPaymentMap());
			modelMapper.map(paymentDTO, paymentEntity);
			service.saveOrUpdate(paymentEntity);
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