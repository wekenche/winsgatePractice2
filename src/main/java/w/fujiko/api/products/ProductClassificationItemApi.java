package w.fujiko.api.products;

import static w.fujiko.util.common.constants.ProductConstants.MSG_500;
import static w.fujiko.util.common.constants.ProductConstants.MSG_CONFLICT_ITEM;

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
import w.fujiko.dto.products.ProductClassificationItemCreateDTO;
import w.fujiko.dto.products.ProductClassificationItemDTO;
import w.fujiko.dto.products.ProductClassificationItemUpdateDTO;
import w.fujiko.model.masters.products.ProductClassificationItem;
import w.fujiko.service.products.ProductClassificationItemService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/product")
public class ProductClassificationItemApi 
	extends Api<ProductClassificationItemService, ProductClassificationItem, Integer> {
	
	private ModelMapper modelMapper;
	
	public ProductClassificationItemApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}

	@GetMapping("/dto")
	public @ResponseBody List<ProductClassificationItemDTO> getDTO() {
		
		List<ProductClassificationItem> productClassificationItemModel = service.get();
		
		Type listType = new TypeToken<List<ProductClassificationItemDTO>>() {}.getType();

		List<ProductClassificationItemDTO> productClassificationItemDTO = this.modelMapper
										  .map(productClassificationItemModel,listType);

		return productClassificationItemDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<ProductClassificationItemDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<ProductClassificationItem> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<ProductClassificationItemDTO> pageDTO = new Page<ProductClassificationItemDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<ProductClassificationItemDTO>>() {}.getType();

		List<ProductClassificationItemDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<ProductClassificationItem> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            ProductClassificationItem productClassificationItem = response.get();

		
        ProductClassificationItemDTO productClassificationItemDTO = this.modelMapper
										  .map(productClassificationItem,ProductClassificationItemDTO.class);

		return ResponseEntity
				.ok(productClassificationItemDTO);
	}
	
	@GetMapping("{productClassId}/code-search")
	public ResponseEntity<?> getUndeletedItemByCode(
			@PathVariable(value="productClassId") String productClassId,
			@RequestParam(value = "code", defaultValue = "") String code) {
		
		try {
			int id = Integer.parseInt(productClassId);
			int cd = Integer.parseInt(code);
			Optional<ProductClassificationItem> result = service.getUndeletedProductClassificationItemByCode(id, cd);
			
			if(result.isPresent()) {
				Type type = new TypeToken<ProductClassificationItemDTO>() {}.getType();
				ProductClassificationItemDTO prodClassItemDTO = this.modelMapper.map(result.get(), type);
				return ResponseEntity.ok().body(prodClassItemDTO);
			} else {
				return ResponseEntity.ok().body(null);
			}
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@GetMapping("/{classificationId}/items")
	public ResponseEntity<?> getProductsByClassificationId(
				@PathVariable(value="classificationId") String productClassId,
				@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			Integer id = Integer.parseInt(productClassId);
			Integer indexNo = Integer.parseInt(index);
			List<ProductClassificationItem> entities = service.getProductsByClassificationId(id, indexNo);
			
			Type listType = new TypeToken<List<ProductClassificationItemDTO>>() {}.getType();
			List<ProductClassificationItemDTO> productClassificationItemsDTO = this.modelMapper.map(entities, listType);
			
			return ResponseEntity.ok().body(productClassificationItemsDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@GetMapping("/{classificationId}/items/search")
	public ResponseEntity<?> getProductsByClassificationItemNameOrCode(
				@PathVariable(value="classificationId") Integer classificationId,
				@RequestParam(value = "key", defaultValue = "") String key,
				@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			List<ProductClassificationItem> entities = 
					service.getProductsByClassificationItemNameOrCode(
							classificationId, key, Integer.parseInt(index));
			
			Type listType = new TypeToken<List<ProductClassificationItemDTO>>() {}.getType();
			List<ProductClassificationItemDTO> productClassificationItemsDTO = this.modelMapper.map(entities, listType);
			
			return ResponseEntity.ok().body(productClassificationItemsDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProductClassificationItem
		(@Valid @RequestBody ProductClassificationItemCreateDTO classificationItemDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			int classId = classificationItemDTO.getProductClassificationId();
			Optional<ProductClassificationItem> classificationItemResult = 
					service.getUndeletedProductClassificationItemByCode(classId, classificationItemDTO.getCode());
			
			if(classificationItemResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_ITEM));
			} else {
				ProductClassificationItem prodItemEntity = 
						this.modelMapper.map(classificationItemDTO, ProductClassificationItem.class);
				service.saveOrUpdate(prodItemEntity);
				String createdLink = "/api/product/"+prodItemEntity.getId();
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
	public ResponseEntity<?> updateProductClassificationItem
		(@Valid @RequestBody ProductClassificationItemUpdateDTO classificationItemDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			ProductClassificationItem prodItemEntity = service.get(classificationItemDTO.getId()).orElseThrow();
			this.modelMapper.map(classificationItemDTO, prodItemEntity);
			service.saveOrUpdate(prodItemEntity);
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