package w.fujiko.api.products;

import static w.fujiko.util.common.constants.ProductConstants.MSG_500;
import static w.fujiko.util.common.constants.ProductConstants.MSG_CONFLICT;

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
import w.fujiko.dto.products.ProductClassificationCreateDTO;
import w.fujiko.dto.products.ProductClassificationDTO;
import w.fujiko.dto.products.ProductClassificationUpdateDTO;
import w.fujiko.model.masters.products.ProductClassification;
import w.fujiko.service.products.ProductClassificationService;
import w.fujiko.util.common.constants.ProductConstants;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/product/classifications")
public class ProductClassificationApi 
	extends Api<ProductClassificationService, ProductClassification, Integer> {
	
	private ModelMapper modelMapper;
	
	public ProductClassificationApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}

	@GetMapping("/dto")
	public @ResponseBody List<ProductClassificationDTO> getDTO() {
		
		List<ProductClassification> productClassificationModel = service.get();
		
		Type listType = new TypeToken<List<ProductClassificationDTO>>() {}.getType();

		List<ProductClassificationDTO> productClassifcationDTO = this.modelMapper
										  .map(productClassificationModel,listType);

		return productClassifcationDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<ProductClassificationDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<ProductClassification> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<ProductClassificationDTO> pageDTO = new Page<ProductClassificationDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<ProductClassificationDTO>>() {}.getType();

		List<ProductClassificationDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/code-search")
	public ResponseEntity<?> getUndeletedItemByCode(
			@RequestParam(value = "code", defaultValue = "") String code) {
		
		try {
			int cd = Integer.parseInt(code);
			Optional<ProductClassification> result = service.getUndeletedProductClassificationByCode(cd);
			
			if(result.isPresent()) {
				Type type = new TypeToken<ProductClassificationDTO>() {}.getType();
				ProductClassificationDTO prodClassDTO = this.modelMapper.map(result.get(), type);
				return ResponseEntity.ok().body(prodClassDTO);
			} else {
				return ResponseEntity.ok().body(null);
			}
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getProductClassificationsByItemNameOrCode(
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			List<ProductClassification> entities = 
				service.getProductClassificationsByItemNameOrCode(
						key, Integer.parseInt(index));
			
			Type listType = new TypeToken<List<ProductClassificationDTO>>() {}.getType();
			List<ProductClassificationDTO> productClassificationsDTO = this.modelMapper.map(entities, listType);
			
			return ResponseEntity.ok().body(productClassificationsDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProductClassification
		(@Valid @RequestBody ProductClassificationCreateDTO classificationDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<ProductClassification> classificationResult = 
					service.getUndeletedProductClassificationByCode(classificationDTO.getCode());
			
			if(classificationResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT));
			} else {
				ProductClassification prodEntity = this.modelMapper.map(classificationDTO, ProductClassification.class);
				service.saveOrUpdate(prodEntity);
				String createdLink = "/api/product/classifications/"+prodEntity.getId();
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
	public ResponseEntity<?> updateProductClassification
		(@Valid @RequestBody ProductClassificationUpdateDTO classificationDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			ProductClassification prodEntity = service.get(classificationDTO.getId()).orElseThrow();
			this.modelMapper.map(classificationDTO, prodEntity);
			service.saveOrUpdate(prodEntity);
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
			HttpHeaders headers = getHeaders(ProductConstants.PDF_FILE_NAME);
			int codeFrom = Integer.parseInt(from);
			int codeTo = Integer.parseInt(to);
			return ResponseEntity.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(service.exportToPDF(codeFrom, codeTo)));
		} catch(Exception ex) {
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
			HttpHeaders headers = getHeaders(ProductConstants.CSV_FILE_NAME);
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