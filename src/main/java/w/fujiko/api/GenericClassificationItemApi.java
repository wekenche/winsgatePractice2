package w.fujiko.api;

import static w.fujiko.util.common.constants.GenericClassificationItemConstants.MSG_500;
import static w.fujiko.util.common.constants.GenericClassificationItemConstants.MSG_CONFLICT_GENERALCLASSIFICATIONITEM;

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
import org.springframework.beans.factory.annotation.Autowired;
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
import w.fujiko.dto.genericclassifications.items.GenericClassificationItemCreateDTO;
import w.fujiko.dto.genericclassifications.items.GenericClassificationItemDTO;
import w.fujiko.dto.genericclassifications.items.GenericClassificationItemUpdateDTO;
import w.fujiko.model.masters.GenericClassification;
import w.fujiko.model.masters.GenericClassificationItem;
import w.fujiko.service.GenericClassificationItemService;
import w.fujiko.service.GenericClassificationService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/generic-classification/items")
public class GenericClassificationItemApi 
	extends Api<GenericClassificationItemService, GenericClassificationItem, Integer> {
	
	private ModelMapper modelMapper;
	@Autowired GenericClassificationService genericClassificationService;

	public GenericClassificationItemApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}
	
	@GetMapping("/{genClassCode}/code-search")
	public ResponseEntity<?> getUndeletedItemByCode(
			@PathVariable(value="genClassCode") String genClassCode,
			@RequestParam(value = "code", defaultValue = "") String genClassItemCode) {
		
		try {
			int cd = Integer.parseInt(genClassItemCode);
			Optional<GenericClassificationItem> result = service.getUndeletedItemByCode(genClassCode, cd);
			
			if(result.isPresent()) {
				Type type = new TypeToken<GenericClassificationItemDTO>() {}.getType();
				GenericClassificationItemDTO genClassItemDTO = this.modelMapper.map(result.get(), type);
				return ResponseEntity.ok().body(genClassItemDTO);
			} else {
				return ResponseEntity.ok().body(null);
			}
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}

	@GetMapping("/generic-classification/{genericClassificationId}")
	public List<GenericClassificationItemDTO> getByGenericClassificationId(
			@PathVariable(value="genericClassificationId") String genericClassificationId) {
		
			List<GenericClassificationItem> genericClassificationItemEntity = service.getByGenericClassificationId(genericClassificationId);
	
			Type listType = new TypeToken<List<GenericClassificationItemDTO>>() {}.getType();
	
			List<GenericClassificationItemDTO> genericClassificationItemDTO = this.modelMapper
																				  .map(genericClassificationItemEntity,listType);
			return genericClassificationItemDTO;
	}

	@GetMapping("/dto")
	public @ResponseBody List<GenericClassificationItemDTO> getDTO() {
		
		List<GenericClassificationItem> genericClassificationItemModel = service.get();
		
		Type listType = new TypeToken<List<GenericClassificationItemDTO>>() {}.getType();

		List<GenericClassificationItemDTO> genericClassificationItemDTO = this.modelMapper
										  									  .map(genericClassificationItemModel,listType);

		return genericClassificationItemDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<GenericClassificationItemDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<GenericClassificationItem> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<GenericClassificationItemDTO> pageDTO = new Page<GenericClassificationItemDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<GenericClassificationItemDTO>>() {}.getType();

		List<GenericClassificationItemDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<GenericClassificationItem> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            GenericClassificationItem genericClassificationItem = response.get();

			GenericClassificationItemDTO genericClassificationItemDTO = this.modelMapper
										  									.map(genericClassificationItem,GenericClassificationItemDTO.class);

		return ResponseEntity
			   .ok(genericClassificationItemDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createGenericClassificationItem(@Valid @RequestBody GenericClassificationItemCreateDTO genericClassificationItemCreateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		GenericClassification genericClassification = genericClassificationService.get(genericClassificationItemCreateDTO.getGenericClassificationId())
																				  .orElseThrow();
		try {
			Optional<GenericClassificationItem> genericClassificationItemResult = service.getUndeletedItemByCode(genericClassification.getCode(),genericClassificationItemCreateDTO.getCode());
			if(genericClassificationItemResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_GENERALCLASSIFICATIONITEM));
			} else {
				GenericClassificationItem genericClassificationItemEntity = this.modelMapper.map(genericClassificationItemCreateDTO, GenericClassificationItem.class);
				service.saveOrUpdate(genericClassificationItemEntity);
				String createdLink = "/api/generic-classification/items/"+genericClassificationItemEntity.getId();
				return ResponseEntity
					  .created(new URI(createdLink))
					  .body(new Created(createdLink));
			}
		}catch(NoSuchElementException ex){
			return ResponseEntity
            .notFound()
            .build();
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
	public ResponseEntity<?> updateGenericClassificationItem(@Valid @RequestBody GenericClassificationItemUpdateDTO genericClassificationItemUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());

		try {
			GenericClassificationItem genericClassificationItemEntity = service.get(genericClassificationItemUpdateDTO.getId()).orElseThrow();
			this.modelMapper.map(genericClassificationItemUpdateDTO, genericClassificationItemEntity);
			service.saveOrUpdate(genericClassificationItemEntity);
			return ResponseEntity.ok().body(new Success());	
		}catch(NoSuchElementException ex){
			return ResponseEntity
            .notFound()
            .build();
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