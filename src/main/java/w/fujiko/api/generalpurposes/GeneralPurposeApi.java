package w.fujiko.api.generalpurposes;

import static w.fujiko.util.common.constants.GeneralPurposeConstants.MSG_500;
import static w.fujiko.util.common.constants.GeneralPurposeConstants.MSG_CONFLICT_GENERALPURPOSE;

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

import fte.api.Api;
import fte.api.Created;
import fte.api.Page;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.generalpurposes.GeneralPurposeCreateDTO;
import w.fujiko.dto.generalpurposes.GeneralPurposeDTO;
import w.fujiko.dto.generalpurposes.GeneralPurposeUpdateDTO;
import w.fujiko.model.masters.generalpurposes.GeneralPurpose;
import w.fujiko.service.generalpurposes.GeneralPurposeService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/general-purpose")
public class GeneralPurposeApi 
	extends Api<GeneralPurposeService, GeneralPurpose, Integer> {
	
	private ModelMapper modelMapper;

	public GeneralPurposeApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@GetMapping("/{genPurposeCode}/code-search")
	public ResponseEntity<?> getUndeletedItemByCode(
			@PathVariable(value="genPurposeCode") String genPurposeCode) {
		
		try {
			Optional<GeneralPurpose> result = service.getUndeletedItemByCode(genPurposeCode);
			
			if(result.isPresent()) {
				Type type = new TypeToken<GeneralPurposeDTO>() {}.getType();
				GeneralPurposeDTO genPurposeDTO = this.modelMapper.map(result.get(), type);
				return ResponseEntity.ok().body(genPurposeDTO);
			} else {
				return ResponseEntity.ok().body(null);
			}
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}

	@GetMapping("/dto")
	public @ResponseBody List<GeneralPurposeDTO> getDTO() {
		
		List<GeneralPurpose> generalPurposeModel = service.get();
		
		Type listType = new TypeToken<List<GeneralPurposeDTO>>() {}.getType();

		List<GeneralPurposeDTO> generalPurposeDTO = this.modelMapper
                                                        .map(generalPurposeModel,listType);

		return generalPurposeDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<GeneralPurposeDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<GeneralPurpose> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<GeneralPurposeDTO> pageDTO = new Page<GeneralPurposeDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<GeneralPurposeDTO>>() {}.getType();

		List<GeneralPurposeDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<GeneralPurpose> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            GeneralPurpose generalPurpose = response.get();

			GeneralPurposeDTO generalPurposeDTO = this.modelMapper
                                                      .map(generalPurpose,GeneralPurposeDTO.class);

		return ResponseEntity
			   .ok(generalPurposeDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createGeneralPurpose(@Valid @RequestBody GeneralPurposeCreateDTO generalPurposeCreateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<GeneralPurpose> generalPurposeResult = service.getUndeletedItemByCode(generalPurposeCreateDTO.getCode());
			if(generalPurposeResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_GENERALPURPOSE));
			} else {
				GeneralPurpose generalPurposeEntity = this.modelMapper.map(generalPurposeCreateDTO, GeneralPurpose.class);
				service.saveOrUpdate(generalPurposeEntity);
				String createdLink = "/api/general-purpose/"+generalPurposeEntity.getId();
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
	public ResponseEntity<?> updateGeneralPurpose(@Valid @RequestBody GeneralPurposeUpdateDTO generalPurposeUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());

		try {
			GeneralPurpose generalPurposeEntity = service.get(generalPurposeUpdateDTO.getId()).orElseThrow();
			this.modelMapper.map(generalPurposeUpdateDTO, generalPurposeEntity);
			service.saveOrUpdate(generalPurposeEntity);
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