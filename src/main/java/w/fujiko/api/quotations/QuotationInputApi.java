package w.fujiko.api.quotations;

import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;
import w.fujiko.util.common.constants.QuotationConstants;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Created;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.model.transactions.quotations.QuotationDetail;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.model.wrappers.QuotationInputWrapper;
import w.fujiko.service.transactions.quotations.QuotationHeaderService;
import w.fujiko.service.transactions.quotations.QuotationDetailService;
import w.fujiko.service.transactions.quotations.QuotationInputService;
import w.fujiko.util.common.quotations.QuotationTransaction;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.quotations.details.CreateDTOToQuotationDetailMap;
import w.fujiko.util.mapper.propertymaps.quotations.details.QuotationDetailToDTOMap;
import w.fujiko.util.mapper.propertymaps.quotations.headers.CreateDTOToQuotationHeaderMap;
import w.fujiko.util.mapper.propertymaps.quotations.headers.QuotationHeaderToDTOMap;
import w.fujiko.dto.quotations.QuotationDTO;
import w.fujiko.dto.quotations.headers.QuotationHeaderDTO;
import w.fujiko.dto.quotations.details.QuotationDetailDTO;

@RestController
@RequestMapping("/api/quotation")
public class QuotationInputApi {

	@Autowired QuotationInputService quotationInputService;
	@Autowired QuotationHeaderService quotationHeaderService;
	@Autowired QuotationDetailService quotationDetailService;

    private ModelMapper createDTOToQuotationHeaderMapper;
	private ModelMapper createDTOToQuotationDetailMapper;
	private ModelMapper QuotationHeaderToDTOMapper;
	private ModelMapper QuotationDetailToDTOMapper;

	public QuotationInputApi() {
		createDTOToQuotationHeaderMapper = new ModelMapper();
		createDTOToQuotationHeaderMapper.getConfiguration().setAmbiguityIgnored(true);
		
		createDTOToQuotationDetailMapper = new ModelMapper();	
		createDTOToQuotationDetailMapper.getConfiguration().setAmbiguityIgnored(true);

		QuotationHeaderToDTOMapper = new ModelMapper();		
		QuotationHeaderToDTOMapper.getConfiguration().setAmbiguityIgnored(true);
		
		QuotationDetailToDTOMapper = new ModelMapper();	
		QuotationDetailToDTOMapper.getConfiguration().setAmbiguityIgnored(true);

        createDTOToQuotationHeaderMapper.addMappings(new CreateDTOToQuotationHeaderMap());
		createDTOToQuotationDetailMapper.addMappings(new CreateDTOToQuotationDetailMap());
		QuotationHeaderToDTOMapper.addMappings(new QuotationHeaderToDTOMap());
        QuotationDetailToDTOMapper.addMappings(new QuotationDetailToDTOMap());
	}

	@PostMapping
	public ResponseEntity<?> createQuotation(@Valid @RequestBody QuotationInputWrapper quotationInputWrapper, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {			
			QuotationHeader quotationHeaderEntity = this.createDTOToQuotationHeaderMapper
                                                        .map(quotationInputWrapper.getHeader(), QuotationHeader.class);
			quotationHeaderEntity.setQuotationRank(QuotationConstants.CHECKING);
            Type listType = new TypeToken<List<QuotationDetail>>() {}.getType();
            
            List<QuotationDetail> quotationDetails = this.createDTOToQuotationDetailMapper
                                                         .map(quotationInputWrapper.getDetails(),listType);
        
			QuotationTransaction transaction = new QuotationTransaction(quotationHeaderEntity,quotationDetails);
			quotationInputService.add(transaction);
			String createdLink = "/api/quotation/"+quotationHeaderEntity.getId();
			return ResponseEntity
				  .created(new URI(createdLink))
				  .body(new Created(createdLink));
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

	@PostMapping("/cancel")
	public ResponseEntity<?> QuotationCanceled(@Valid @RequestBody QuotationInputWrapper quotationInputWrapper, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {			
			QuotationHeader quotationHeaderEntity = this.createDTOToQuotationHeaderMapper
                                                        .map(quotationInputWrapper.getHeader(), QuotationHeader.class);
			quotationHeaderEntity.setQuotationRank(QuotationConstants.CANCELED);

            Type listType = new TypeToken<List<QuotationDetail>>() {}.getType();
            
            List<QuotationDetail> quotationDetails = this.createDTOToQuotationDetailMapper
                                                         .map(quotationInputWrapper.getDetails(),listType);
        
			QuotationTransaction transaction = new QuotationTransaction(quotationHeaderEntity,quotationDetails);
			quotationInputService.add(transaction);
			return ResponseEntity
				  .ok(new Success());

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

	@PostMapping("/delete")
	public ResponseEntity<?> QuotationDeleted(@Valid @RequestBody QuotationInputWrapper quotationInputWrapper, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {			
			QuotationHeader quotationHeaderEntity = this.createDTOToQuotationHeaderMapper
                                                        .map(quotationInputWrapper.getHeader(), QuotationHeader.class);
			quotationHeaderEntity.setQuotationRank(QuotationConstants.DELETED);
			
            Type listType = new TypeToken<List<QuotationDetail>>() {}.getType();
            
            List<QuotationDetail> quotationDetails = this.createDTOToQuotationDetailMapper
                                                         .map(quotationInputWrapper.getDetails(),listType);
        
			QuotationTransaction transaction = new QuotationTransaction(quotationHeaderEntity,quotationDetails);
			quotationInputService.add(transaction);
			return ResponseEntity
				  .ok(new Success());

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

	@GetMapping("/{quotationHeaderId}")
	public @ResponseBody QuotationDTO getQuotationByQuotationHeaderId(@PathVariable("quotationHeaderId") final Integer quotationHeaderId) {
		
		var quotationHeader = quotationHeaderService.get(quotationHeaderId).orElseThrow();
		var quotationDetails= quotationDetailService.getByQuotationHeaderId(quotationHeaderId);
		var quotationDTO = new QuotationDTO();

		var quotationHeaderDTO = this.QuotationHeaderToDTOMapper
								     .map(quotationHeader,QuotationHeaderDTO.class);

		Type quotationDetailDTOListType = new TypeToken<List<QuotationDetailDTO>>() {}.getType();

		List<QuotationDetailDTO> quotationDetailsDTO = this.QuotationDetailToDTOMapper
									  					   .map(quotationDetails,quotationDetailDTOListType);
															 
		quotationDTO.setQuotationHeader(quotationHeaderDTO);
		quotationDTO.setQuotationDetails(quotationDetailsDTO);

		return quotationDTO;
	}

	@GetMapping("/slipnumber/{slipNumber}")
	public @ResponseBody QuotationDTO getQuotationBySlipNo(@PathVariable("slipNumber") final String slipNumber) {
		
		var quotationHeader = quotationHeaderService.getBySlipNumber(slipNumber).orElseThrow();
		var quotationDetails= quotationDetailService.getByQuotationHeaderId(quotationHeader.getId());
		var quotationDTO = new QuotationDTO();

		var quotationHeaderDTO = this.QuotationHeaderToDTOMapper
								     .map(quotationHeader,QuotationHeaderDTO.class);

		Type quotationDetailDTOListType = new TypeToken<List<QuotationDetailDTO>>() {}.getType();

		List<QuotationDetailDTO> quotationDetailsDTO = this.QuotationDetailToDTOMapper
									  					   .map(quotationDetails,quotationDetailDTOListType);
															 
		quotationDTO.setQuotationHeader(quotationHeaderDTO);
		quotationDTO.setQuotationDetails(quotationDetailsDTO);

		return quotationDTO;
	}
	
}