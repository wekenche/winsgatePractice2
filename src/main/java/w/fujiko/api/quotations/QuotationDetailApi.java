package w.fujiko.api.quotations;

import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Created;
import fte.api.Page;
import fte.api.Response;
import w.fujiko.dto.quotations.details.QuotationDetailCreateDTO;
import w.fujiko.dto.quotations.details.QuotationDetailDTO;
import w.fujiko.model.transactions.quotations.QuotationDetail;
import w.fujiko.model.transactions.quotations.QuotationDetailPk;
import w.fujiko.service.transactions.quotations.QuotationDetailService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.quotations.details.CreateDTOToQuotationDetailMap;
import w.fujiko.util.mapper.propertymaps.quotations.details.QuotationDetailToDTOMap;

@RestController
@RequestMapping("/api/quotation/detail")
public class QuotationDetailApi extends Api<QuotationDetailService, QuotationDetail, QuotationDetailPk> {

	private ModelMapper quotationDetailToDTOMapper;
	private ModelMapper createDTOToQuotationDetailMapper;

	public QuotationDetailApi() {
		quotationDetailToDTOMapper = new ModelMapper();
		quotationDetailToDTOMapper.getConfiguration()
								  .setAmbiguityIgnored(true);

		createDTOToQuotationDetailMapper = new ModelMapper();
		createDTOToQuotationDetailMapper.getConfiguration()
										.setAmbiguityIgnored(true);
		
		quotationDetailToDTOMapper.addMappings(new QuotationDetailToDTOMap());
		createDTOToQuotationDetailMapper.addMappings(new CreateDTOToQuotationDetailMap());
	}

	@GetMapping("/dto")
	public @ResponseBody List<QuotationDetailDTO> getDTO() {
		
		List<QuotationDetail> quotationDetailModel = service.get();
		
		Type listType = new TypeToken<List<QuotationDetailDTO>>() {}.getType();
		
		List<QuotationDetailDTO> quotationDetailDTO = this.quotationDetailToDTOMapper
										 				  .map(quotationDetailModel,listType);
		return quotationDetailDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<QuotationDetailDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<QuotationDetail> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<QuotationDetailDTO> pageDTO = new Page<QuotationDetailDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<QuotationDetailDTO>>() {}.getType();

		List<QuotationDetailDTO> resultsDTO = this.quotationDetailToDTOMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@GetMapping("/header/{quotationHeaderId}")
	public @ResponseBody List<QuotationDetailDTO> getByQuotationHeaderId(@PathVariable("quotationHeaderId") final Integer quotationHeaderId) {
		
		List<QuotationDetail> quotationDetailModel = service.getByQuotationHeaderId(quotationHeaderId);
		
		Type listType = new TypeToken<List<QuotationDetailDTO>>() {}.getType();
		
		List<QuotationDetailDTO> quotationDetailDTO = this.quotationDetailToDTOMapper
										 				  .map(quotationDetailModel,listType);
		return quotationDetailDTO;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createQuotationDetail(@Valid @RequestBody QuotationDetailCreateDTO quotationDetailDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			QuotationDetail quotationDetailEntity = this.createDTOToQuotationDetailMapper.map(quotationDetailDTO, QuotationDetail.class);
			service.saveOrUpdate(quotationDetailEntity);
			String createdLink = "/api/quotation/detail/header/"+quotationDetailEntity.getId().getQuotationHeaderId()+"/taskId/"+quotationDetailEntity.getId().getTaskId();
			return ResponseEntity
				  .created(new URI(createdLink))
				  .body(new Created(createdLink));
		}catch(ConstraintViolationException cve) {
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe) {
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
	
}
