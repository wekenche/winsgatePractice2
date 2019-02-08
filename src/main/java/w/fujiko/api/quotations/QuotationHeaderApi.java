package w.fujiko.api.quotations;

import static w.fujiko.util.common.constants.messages.error.ErrorMessages.MSG_500;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import w.fujiko.dto.quotations.headers.ConstructionListDTO;
import w.fujiko.dto.quotations.headers.QuotationHeaderCreateDTO;
import w.fujiko.dto.quotations.headers.QuotationHeaderDTO;
import w.fujiko.dto.quotations.headers.histories.QuotationHeaderHistoryDTO;
import w.fujiko.model.transactions.quotations.QuotationHeader;
import w.fujiko.service.transactions.quotations.QuotationHeaderService;
import w.fujiko.service.transactions.quotations.histories.QuotationHeaderHistoryService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.quotations.headers.CreateDTOToQuotationHeaderMap;
import w.fujiko.util.mapper.propertymaps.quotations.headers.QuotationHeaderToDTOMap;

@RestController
@RequestMapping("/api/quotation/header")
public class QuotationHeaderApi extends Api<QuotationHeaderService, QuotationHeader, Integer> {

	private ModelMapper quotationHeaderToDTOMapper;
	private ModelMapper createDTOToQuotationHeaderMapper;
	@Autowired QuotationHeaderHistoryService quotationHeaderHistoryService;
	public QuotationHeaderApi() {
		quotationHeaderToDTOMapper = new ModelMapper();
		quotationHeaderToDTOMapper.getConfiguration().setAmbiguityIgnored(true);
		createDTOToQuotationHeaderMapper = new ModelMapper();
		createDTOToQuotationHeaderMapper.getConfiguration().setAmbiguityIgnored(true);

		quotationHeaderToDTOMapper.addMappings(new QuotationHeaderToDTOMap());
		createDTOToQuotationHeaderMapper.addMappings(new CreateDTOToQuotationHeaderMap());
	}

	@GetMapping("/slipNum/{slipNum}")
	public @ResponseBody QuotationHeaderDTO getBySlipNumber(@PathVariable("slipNum")final String slipNum) {
		
		QuotationHeader quotationHeader = service.getBySlipNumber(slipNum).orElseThrow();
		
		QuotationHeaderDTO quotationHeaderDTO = this.quotationHeaderToDTOMapper
													.map(quotationHeader,QuotationHeaderDTO.class);
		return quotationHeaderDTO;
	}
	
	@GetMapping("/working-site-count/{id}")
	public @ResponseBody Long getCountByWorkingsiteId(@PathVariable("id")final Integer id) {
		return service.getCountByWorkingSiteId(id);
	}

	@GetMapping("/dto")
	public @ResponseBody List<QuotationHeaderDTO> getDTO() {
		
		List<QuotationHeader> quotationHeaderModel = service.get();
		
		Type listType = new TypeToken<List<QuotationHeaderDTO>>() {}.getType();
		
		List<QuotationHeaderDTO> quotationHeaderDTO = this.quotationHeaderToDTOMapper
										 				  .map(quotationHeaderModel,listType);
		return quotationHeaderDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<QuotationHeaderDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<QuotationHeader> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<QuotationHeaderDTO> pageDTO = new Page<QuotationHeaderDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<QuotationHeaderDTO>>() {}.getType();

		List<QuotationHeaderDTO> resultsDTO = this.quotationHeaderToDTOMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createQuotation(@Valid @RequestBody QuotationHeaderCreateDTO quotationHeaderDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {			
			QuotationHeader quotationHeaderEntity = this.createDTOToQuotationHeaderMapper
														.map(quotationHeaderDTO, QuotationHeader.class);
			quotationHeaderEntity.setVersion(1);
			service.saveOrUpdate(quotationHeaderEntity);
			String createdLink = "/api/quotation/header/"+quotationHeaderEntity.getId();
			return ResponseEntity
				  .created(new URI(createdLink))
				  .body(new Created(createdLink));
		}catch(ConstraintViolationException cve){
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe){
			pe.printStackTrace();
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

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(value = "workingSiteNumberFrom", required = false) final Integer workingSiteNumberFrom,
									@RequestParam(value = "workingSiteNumberTo", required = false) final Integer workingSiteNumberTo,
									@RequestParam(value = "constructionNumberFrom", required = false) final Integer constructionNumberFrom,
									@RequestParam(value = "constructionNumberTo", required = false) final Integer constructionNumberTo,
									@RequestParam(value = "constructionCategoryFrom", required = false) final Integer constructionCategoryFrom,
									@RequestParam(value = "constructionCategoryTo", required = false) final Integer constructionCategoryTo,
									@RequestParam(value = "customerBaseCodeFrom", required = false) final Integer customerBaseCodeFrom,
									@RequestParam(value = "customerBaseCodeTo", required = false) final Integer customerBaseCodeTo,											
									@RequestParam(value = "userCodeFrom", required = false) final Short userCodeFrom,
									@RequestParam(value = "userCodeTo", required = false) final Short userCodeTo,
									@RequestParam(value = "departmentCodeFrom", required = false) final Integer departmentCodeFrom,
									@RequestParam(value = "departmentCodeTo", required = false) final Integer departmentCodeTo,
									@RequestParam(value = "quotationDateFrom", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date quotationDateFrom,
									@RequestParam(value = "quotationDateTo", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date quotationDateTo,
									@RequestParam(value = "deliveryDateFrom", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date deliveryDateFrom,
									@RequestParam(value = "deliveryDateTo", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date deliveryDateTo,
									@RequestParam(value = "createdDateFrom", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date createdDateFrom,
									@RequestParam(value = "createdDateTo", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") final Date createdDateTo,
									@RequestParam(value = "totalAmountFrom", required = false) final Double totalAmountFrom,
									@RequestParam(value = "totalAmountTo", required = false) final Double totalAmountTo,											
									@RequestParam(value = "grossMarginRatioFrom", required = false) final Double grossMarginRatioFrom,
									@RequestParam(value = "grossMarginRatioTo", required = false) final Double grossMarginRatioTo,											
									@RequestParam(value = "quotationRank", required = false) final List<Integer> quotationRank,
									@RequestParam(value = "orderStatus", required = false) final List<Byte> orderStatus,
									@RequestParam(value = "purchaseStatus", required = false) final List<Byte> purchaseStatus,
									@RequestParam(value = "salesStatus", required = false) final List<Byte> salesStatus,
									@RequestParam(value = "billingStatus", required = false) final List<Byte> billingStatus,
									@RequestParam(value = "paymentStatus", required = false) final List<Byte> paymentStatus,
									@RequestParam(value = "includeHistory", defaultValue = "false") final String includeHistory,
									@RequestParam(value = "first", defaultValue = "0") final int first,
									@RequestParam(value = "max", defaultValue = "30") final int max)
									{
		try {						
			var isQuotationHeaderHistoryIncluded = Boolean.valueOf(includeHistory); 
			var quotationHeaders = service.quotationHeaderSearch(Optional.ofNullable(workingSiteNumberFrom),
																Optional.ofNullable(workingSiteNumberTo),
																Optional.ofNullable(constructionNumberFrom),
																Optional.ofNullable(constructionNumberTo),
																Optional.ofNullable(constructionCategoryFrom),
																Optional.ofNullable(constructionCategoryTo),
																Optional.ofNullable(customerBaseCodeFrom),
																Optional.ofNullable(customerBaseCodeTo),											
																Optional.ofNullable(userCodeFrom),
																Optional.ofNullable(userCodeTo),
																Optional.ofNullable(departmentCodeFrom),
																Optional.ofNullable(departmentCodeTo),
																Optional.ofNullable(quotationDateFrom),
																Optional.ofNullable(quotationDateTo),
																Optional.ofNullable(deliveryDateFrom),
																Optional.ofNullable(deliveryDateTo),
																Optional.ofNullable(createdDateFrom),
																Optional.ofNullable(createdDateTo),
																Optional.ofNullable(totalAmountFrom),
																Optional.ofNullable(totalAmountTo),	
																Optional.ofNullable(grossMarginRatioFrom),
																Optional.ofNullable(grossMarginRatioTo),										
																Optional.ofNullable(quotationRank),
																Optional.ofNullable(orderStatus),
																Optional.ofNullable(purchaseStatus),
																Optional.ofNullable(salesStatus),
																Optional.ofNullable(billingStatus),
																Optional.ofNullable(paymentStatus),
																first,
																max);

			var paginatedQuotationHeader = new Page<QuotationHeaderDTO>();
			paginatedQuotationHeader.setFirst(quotationHeaders.getFirst());
			paginatedQuotationHeader.setMax(quotationHeaders.getMax());
			paginatedQuotationHeader.setTotal(quotationHeaders.getTotal());
			paginatedQuotationHeader.setSuccess(quotationHeaders.getSuccess());

			Type listType = new TypeToken<List<QuotationHeaderDTO>>() {}.getType();		
			List<QuotationHeaderDTO> quotationHeadersDTO = this.quotationHeaderToDTOMapper
															   .map(quotationHeaders.getResults(),listType);
								
			if(isQuotationHeaderHistoryIncluded){
				var quotationHistoryHeaders = quotationHeaderHistoryService.quotationHeaderSearch(Optional.ofNullable(workingSiteNumberFrom),
																								Optional.ofNullable(workingSiteNumberTo),
																								Optional.ofNullable(constructionNumberFrom),
																								Optional.ofNullable(constructionNumberTo),
																								Optional.ofNullable(constructionCategoryFrom),
																								Optional.ofNullable(constructionCategoryTo),
																								Optional.ofNullable(customerBaseCodeFrom),
																								Optional.ofNullable(customerBaseCodeTo),											
																								Optional.ofNullable(userCodeFrom),
																								Optional.ofNullable(userCodeTo),
																								Optional.ofNullable(departmentCodeFrom),
																								Optional.ofNullable(departmentCodeTo),
																								Optional.ofNullable(quotationDateFrom),
																								Optional.ofNullable(quotationDateTo),
																								Optional.ofNullable(deliveryDateFrom),
																								Optional.ofNullable(deliveryDateTo),
																								Optional.ofNullable(createdDateFrom),
																								Optional.ofNullable(createdDateTo),
																								Optional.ofNullable(totalAmountFrom),
																								Optional.ofNullable(totalAmountTo),		
																								Optional.ofNullable(grossMarginRatioFrom),
																								Optional.ofNullable(grossMarginRatioTo),									
																								Optional.ofNullable(quotationRank),
																								Optional.ofNullable(orderStatus),
																								Optional.ofNullable(purchaseStatus),
																								Optional.ofNullable(salesStatus),
																								Optional.ofNullable(billingStatus),
																								Optional.ofNullable(paymentStatus),
																								first,max);
				
				paginatedQuotationHeader.setTotal(paginatedQuotationHeader.getTotal()+quotationHistoryHeaders.getTotal());																						
				Type quotationHeaderHistorylist = new TypeToken<List<QuotationHeaderHistoryDTO>>() {}.getType();		
				List<QuotationHeaderHistoryDTO> quotationHeaderHistoriesDTO = this.quotationHeaderToDTOMapper
																			.map(quotationHistoryHeaders.getResults(),quotationHeaderHistorylist);
				quotationHeadersDTO.addAll(quotationHeaderHistoriesDTO);						
			}
			paginatedQuotationHeader.setResults(quotationHeadersDTO);	
			return ResponseEntity.ok().body(paginatedQuotationHeader);
		}catch (Exception e) {
			e.printStackTrace();
return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));	
		}
		
	}

	@GetMapping("/construction/search")
	public List<ConstructionListDTO> getConstructions(@RequestParam(value = "quotationDateFrom") @DateTimeFormat(pattern="yyyy-MM-dd") final Date quotationDateFrom,
										      @RequestParam(value = "quotationDateTo") @DateTimeFormat(pattern="yyyy-MM-dd") final Date quotationDateTo,
										      @RequestParam(value = "userId") final Integer userId,
										      @RequestParam(value = "customerBaseId") final Integer customerBaseId,
										      @RequestParam(value = "propertyId", required = false) final Integer propertyId,
										      @RequestParam(value = "quotationRank", required = false) final List<Integer> quotationRank,
										      @RequestParam(value = "constructionName", required = false) final String constructionName) {
		try {			
			var constructions = service.getConstructionList(quotationDateFrom, 
															quotationDateTo,
															userId,
															customerBaseId,
															Optional.ofNullable(propertyId),
															Optional.ofNullable(quotationRank),
															Optional.ofNullable(constructionName));

			Type listType = new TypeToken<List<ConstructionListDTO>>() {}.getType();		
			List<ConstructionListDTO> constructionListDTO = this.quotationHeaderToDTOMapper
															    .map(constructions,listType);
			return constructionListDTO;

		}catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();			
		}
		
	}
	
	@GetMapping("/report/{constructionNumber}")
	public void generateReport(
			@PathVariable("constructionNumber") String constructionNumber, 
			HttpServletResponse response) {
		
		service.generateReport(constructionNumber, response);
	}
	
}