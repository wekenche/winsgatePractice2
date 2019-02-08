package w.fujiko.api.products;

import static w.fujiko.util.common.constants.ProductMasterConstants.MSG_500;
import static w.fujiko.util.common.constants.ProductMasterConstants.MSG_CONFLICT;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
import org.springframework.web.multipart.MultipartFile;

import fte.api.Api;
import fte.api.Created;
import fte.api.Page;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.products.ProductCreateDTO;
import w.fujiko.dto.products.ProductDTO;
import w.fujiko.dto.products.ProductUpdateDTO;
import w.fujiko.exceptions.MakerCodeNotFoundException;
import w.fujiko.model.masters.products.CSVUploadErrorResponse;
import w.fujiko.model.masters.products.Product;
import w.fujiko.service.products.ProductService;
import w.fujiko.util.common.csvfileuploadextractor.CSVExtractionError;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/product/master")
public class ProductApi  
	extends Api<ProductService, Product, Integer> {
	
private ModelMapper modelMapper;
	
	public ProductApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getProductsByModelNumberOrName(
			@RequestParam(value = "specialOrderClassificationFlag", required = false) final Boolean specialOrderClassificationFlag,
			@RequestParam(value = "abolishedNumberFlag", required = false) final Boolean abolishedNumberFlag,
			@RequestParam(value = "isEnd", required = false) final Boolean isEnd,
			@RequestParam(value = "makerName", required = false) final String makerName,
			@RequestParam(value = "makerCode", required = false) final Integer makerCode,
			@RequestParam(value = "modelNumber", required = false) final String modelNumber,
			@RequestParam(value = "first", defaultValue = "0") final int first,	
			@RequestParam(value = "max", defaultValue = "30") final int max) {

		try {
			var searchResult = service.search(Optional.ofNullable(specialOrderClassificationFlag),
								   Optional.ofNullable(abolishedNumberFlag),
								   Optional.ofNullable(isEnd),
								   Optional.ofNullable(makerName),
								   Optional.ofNullable(makerCode),
								   Optional.ofNullable(modelNumber),
								   first,
								   max);

			var paginatedProducts = new Page<ProductDTO>();
			paginatedProducts.setFirst(searchResult.getFirst());
			paginatedProducts.setMax(searchResult.getMax());
			paginatedProducts.setTotal(searchResult.getTotal());
			paginatedProducts.setSuccess(searchResult.getSuccess());

			Type listType = new TypeToken<List<ProductDTO>>() {}.getType();
			List<ProductDTO> productsDTO = this.modelMapper.map(searchResult.getResults(), listType);
			
			paginatedProducts.setResults(productsDTO);
			
			return ResponseEntity.ok().body(paginatedProducts);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}

	}

	@GetMapping("/except")
	public ResponseEntity<?> getExcept(
			@RequestParam(value = "makerName") final List<String> makerName,
			@RequestParam(value = "modelNumber", defaultValue="") final String modelNumber,
			@RequestParam(value = "isEnd", required = false) final Boolean isEnd,
			@RequestParam(value = "first", defaultValue = "0") final int first,
			@RequestParam(value = "max", defaultValue = "30") final int max) {

		try {
			var result = service.getExceptByMakerName(makerName,
													  modelNumber,
													  Optional.ofNullable(isEnd),
													  first,
													  max);

			var paginatedProducts = new Page<ProductDTO>();
			paginatedProducts.setFirst(result.getFirst());
			paginatedProducts.setMax(result.getMax());
			paginatedProducts.setTotal(result.getTotal());
			paginatedProducts.setSuccess(result.getSuccess());

			Type listType = new TypeToken<List<ProductDTO>>() {}.getType();
			List<ProductDTO> productsDTO = this.modelMapper.map(result.getResults(), listType);
			
			paginatedProducts.setResults(productsDTO);
			
			return ResponseEntity.ok().body(paginatedProducts);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}

	}

	@GetMapping("/maker/{makerId}")
	public ResponseEntity<?> getProductsByMakerWithModelNumberSearchPaginate(
			@PathVariable("makerId") final Integer makerId,
			@RequestParam(value = "first", defaultValue = "0") final String first,	
			@RequestParam(value = "max", defaultValue = "30") final String max,
			@RequestParam(value = "is_end", required = false) final Boolean isEnd,			
			@RequestParam(value = "modelNumber", defaultValue = "") final String modelNumber) {
				
		try {
			Page<Product> products = service.getByMakerWithModelNumberSearch(makerId, 
																			   modelNumber,
																			   Integer.parseInt(first),
																			   Integer.parseInt(max),
																			   Optional.ofNullable(isEnd));

			Page<ProductDTO> paginatedProducts = new Page<ProductDTO>();
			paginatedProducts.setFirst(products.getFirst());
			paginatedProducts.setMax(products.getMax());
			paginatedProducts.setTotal(products.getTotal());
			paginatedProducts.setSuccess(products.getSuccess());

			Type listType = new TypeToken<List<ProductDTO>>() {}.getType();
			List<ProductDTO> productsDTO = this.modelMapper.map(products.getResults(), listType);
			paginatedProducts.setResults(productsDTO);
			return ResponseEntity.ok().body(paginatedProducts);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}

	@GetMapping("/modelNumber/{modelNumber}")
	public ResponseEntity<?> getByModelNumber(@PathVariable("modelNumber") final String modelNumber) {
				
		try {			
			Product products = service.getByModelNumber(modelNumber).orElseThrow();

			ProductDTO productDTO = this.modelMapper.map(products, ProductDTO.class);
			
			return ResponseEntity.ok().body(productDTO);
			
		}catch(NoSuchElementException ex){
			return ResponseEntity
            .notFound()
            .build();
		}catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}

	@GetMapping("/dto")
	public @ResponseBody List<ProductDTO> getDTO() {
		
		List<Product> productModel = service.get();
		
		Type listType = new TypeToken<List<ProductDTO>>() {}.getType();

		List<ProductDTO> productDTO = this.modelMapper
										  .map(productModel,listType);

		return productDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<ProductDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) final String isEnd,
			@RequestParam(value = "modelNumber", required = false) final String modelNumber) {
		
		Page<Product> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), 
				Optional.ofNullable(Boolean.parseBoolean(isEnd)),
				Optional.ofNullable(modelNumber));
				
		Page<ProductDTO> pageDTO = new Page<ProductDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<ProductDTO>>() {}.getType();

		List<ProductDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductCreateDTO productDTO, Errors error) {

		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			int makerId = productDTO.getMakerId();
			Optional<Product> productResult = 
					service.getUndeletedItemByModelNumber(productDTO.getModelNumber(), makerId);
			
			if(productResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT));
			} else {
				Product productEntity = this.modelMapper.map(productDTO, Product.class);
				service.save(productEntity);
				String createdLink = "/api/product/master/"+productEntity.getId();
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
	public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductUpdateDTO productDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			int makerId = productDTO.getMakerId();
			Optional<Product> productResult = 
					service.getUndeletedItemByModelNumber(productDTO.getModelNumber(), makerId);
			
			if(productResult.isPresent()) {
				Product productEntity = service.get(productDTO.getId()).orElseThrow();
				String modelNumber = productDTO.getModelNumber().toLowerCase();
				Integer productResultId = productResult.get().getId();
				if((modelNumber.equals(productEntity.getModelNumber().toLowerCase()))
					&& productResultId.equals(productEntity.getId())) {
					
					this.modelMapper.map(productDTO, productEntity);
					service.saveOrUpdate(productEntity);
					return ResponseEntity.ok().body(new Success());
				} else {
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body(StringConverter.convertToReadable(MSG_CONFLICT));
				}
			} else {
				Product productEntity = service.get(productDTO.getId()).orElseThrow();
				this.modelMapper.map(productDTO, productEntity);
				service.saveOrUpdate(productEntity);
				return ResponseEntity.ok().body(new Success());	
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
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}		
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(
			@RequestParam("file") MultipartFile file, 
			@RequestParam("maker_code") String makerCode,
			@RequestParam("user_id") String userId) {

		try {			
			var extractionError = service.getErrors(file);
			if(extractionError.size() > 0) {
				var errorResponse = new CSVUploadErrorResponse<List<CSVExtractionError<Integer, String, String>>>(); 
				errorResponse.setSuccess(false);
				errorResponse.setErrors(extractionError);
				
				var csvFileName = service.createCSVErrorFile(extractionError);
				errorResponse.setCsvErrorFileLink("/file/product/error/csv?filename="+csvFileName);
				service.createCSVErrorFile(extractionError);
				return ResponseEntity.ok().body(errorResponse);
			} else {
				int mkrCode = Integer.parseInt(makerCode);
				Response result = service.saveCSVData(file, mkrCode, Integer.parseInt(userId));
				if(result instanceof CSVUploadErrorResponse){
					var errorResult = (CSVUploadErrorResponse<List<CSVExtractionError<Integer, String, String>>>)result;
					var csvFileName = service.createCSVErrorFile(errorResult.getErrors());
					errorResult.setCsvErrorFileLink(csvFileName);
					errorResult.setCsvErrorFileLink("/file/product/error/csv?filename="+csvFileName);
					return ResponseEntity.ok().body(errorResult);
				}	
				return ResponseEntity.ok().body(result);		
			}
		}catch (MakerCodeNotFoundException ex) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(StringConverter.convertToReadable(ex.getLocalizedMessage()));
		} catch (IllegalArgumentException iae) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(iae.getLocalizedMessage()));
		} catch(ConstraintViolationException cve) {
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		} catch(PersistenceException pe) {
			List<Response> errorResponse = new ArrayList<>();
			fte.api.Errors err = new fte.api.Errors();
			err.setDefaultMessage(pe.getLocalizedMessage());			
			errorResponse.add(err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	
	}
}