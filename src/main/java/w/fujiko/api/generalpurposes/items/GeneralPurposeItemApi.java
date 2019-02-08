package w.fujiko.api.generalpurposes.items;

import static w.fujiko.util.common.constants.GeneralPurposeItemConstants.MSG_500;
import static w.fujiko.util.common.constants.GeneralPurposeItemConstants.MSG_CONFLICT_GENERALPURPOSEITEM;

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
import w.fujiko.dto.generalpurposes.items.GeneralPurposeItemCreateDTO;
import w.fujiko.dto.generalpurposes.items.GeneralPurposeItemDTO;
import w.fujiko.dto.generalpurposes.items.GeneralPurposeItemUpdateDTO;
import w.fujiko.model.masters.generalpurposes.GeneralPurpose;
import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem;
import w.fujiko.service.generalpurposes.GeneralPurposeService;
import w.fujiko.service.generalpurposes.items.GeneralPurposeItemService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/general-purpose/item")
public class GeneralPurposeItemApi 
	extends Api<GeneralPurposeItemService, GeneralPurposeItem, Integer> {
	
	private ModelMapper modelMapper;
    @Autowired GeneralPurposeService generalPurposeService;

	public GeneralPurposeItemApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}

	@GetMapping("/dto")
	public @ResponseBody List<GeneralPurposeItemDTO> getDTO() {
		
		List<GeneralPurposeItem> generalPurposeItemModel = service.get();
		
		Type listType = new TypeToken<List<GeneralPurposeItemDTO>>() {}.getType();

		List<GeneralPurposeItemDTO> generalPurposeItemDTO = this.modelMapper
                                                                .map(generalPurposeItemModel,listType);

		return generalPurposeItemDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<GeneralPurposeItemDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<GeneralPurposeItem> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<GeneralPurposeItemDTO> pageDTO = new Page<GeneralPurposeItemDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<GeneralPurposeItemDTO>>() {}.getType();

		List<GeneralPurposeItemDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<GeneralPurposeItem> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            GeneralPurposeItem generalPurposeItem = response.get();

			GeneralPurposeItemDTO generalPurposeItemDTO = this.modelMapper
                                                              .map(generalPurposeItem,GeneralPurposeItemDTO.class);

		return ResponseEntity
			   .ok(generalPurposeItemDTO);
    }
    
    @GetMapping("/general-purpose/{generalPurposeId}")
	public List<GeneralPurposeItemDTO> getByGeneralPurposeId(
			@PathVariable(value="generalPurposeId") Integer generalPurposeId,
			@RequestParam(value="code",required=false) final String code) {
		
			List<GeneralPurposeItem> generalPurposeItemEntity = service.getByGeneralPurposeId(generalPurposeId,
																							  Optional.ofNullable(code));
	
			Type listType = new TypeToken<List<GeneralPurposeItemDTO>>() {}.getType();
	
			List<GeneralPurposeItemDTO> generalPurposeItemDTO = this.modelMapper
                                                                    .map(generalPurposeItemEntity,listType);
			return generalPurposeItemDTO;
	}

	@GetMapping("/general-purpose/code/{generalPurposeCode}")
	public ResponseEntity<?> getByGeneralPurposeCode(
			@PathVariable(value="generalPurposeCode") String generalPurposeCode,
			@RequestParam(value="isEnd",defaultValue="false") final Boolean isEnd) {
		
			List<GeneralPurposeItem> generalPurposeItemEntity = service
																.getByGeneralPurposeCode(
																	generalPurposeCode,
																	isEnd
																);
	
			Type listType = new TypeToken<List<GeneralPurposeItemDTO>>() {}.getType();
	
			List<GeneralPurposeItemDTO> generalPurposeItemDTO = this.modelMapper
                                                                    .map(generalPurposeItemEntity,listType);
			return ResponseEntity
				  .ok(generalPurposeItemDTO);
	}

	@GetMapping("/code/{code}/general-purpose/code/{generalPurposeCode}")
	public ResponseEntity<?> getByCodeAndByGenPurposeCode(
			@PathVariable(value="code") String code,
			@PathVariable(value="generalPurposeCode") String generalPurposeCode,
			@RequestParam(value="isEnd",defaultValue="false") final Boolean isEnd) {
				
			try {
			var generalPurposeItemEntity = service
										   .getByCodeAndByGeneralPurposeCode(
												code,
												generalPurposeCode,
												isEnd
										   ).orElseThrow();

			GeneralPurposeItemDTO generalPurposeItemDTO = this.modelMapper
															  .map(generalPurposeItemEntity,GeneralPurposeItemDTO.class);
			return ResponseEntity
				  .ok(generalPurposeItemDTO);
			}catch(NoSuchElementException e){
				return ResponseEntity
					   .notFound()
					   .build();
			}
	}

	@PostMapping("/create")
	public ResponseEntity<?> createGeneralPurposeItem(@Valid @RequestBody GeneralPurposeItemCreateDTO generalPurposeItemCreateDTO, Errors error) {
		if(error.hasErrors()) 
            return ResponseEntity.badRequest().body(error.getAllErrors());
            
		GeneralPurpose generalPurpose = generalPurposeService.get(generalPurposeItemCreateDTO.getGeneralPurposeId())
                                                             .orElseThrow();
		try {
			Optional<GeneralPurposeItem> generalPurposeItemResult = service.getUndeletedItemByCode(generalPurpose.getCode(),generalPurposeItemCreateDTO.getCode());
			if(generalPurposeItemResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_GENERALPURPOSEITEM));
			} else {
				GeneralPurposeItem generalPurposeItemEntity = this.modelMapper.map(generalPurposeItemCreateDTO, GeneralPurposeItem.class);
				service.saveOrUpdate(generalPurposeItemEntity);
				String createdLink = "/api/general-purpose/item/"+generalPurposeItemEntity.getId();
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
	public ResponseEntity<?> updateGenerPurposeItem(@Valid @RequestBody GeneralPurposeItemUpdateDTO generalPurposeItemUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());

		try {
			GeneralPurposeItem generalPurposeItemEntity = service.get(generalPurposeItemUpdateDTO.getId()).orElseThrow();
			this.modelMapper.map(generalPurposeItemUpdateDTO, generalPurposeItemEntity);
			service.saveOrUpdate(generalPurposeItemEntity);
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