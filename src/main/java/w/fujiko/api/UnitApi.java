package w.fujiko.api;

import static w.fujiko.util.common.constants.UnitConstants.MSG_500;
import static w.fujiko.util.common.constants.UnitConstants.MSG_CONFLICT_UNIT;

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
import w.fujiko.dto.units.UnitCreateDTO;
import w.fujiko.dto.units.UnitDTO;
import w.fujiko.dto.units.UnitUpdateDTO;
import w.fujiko.model.masters.Unit;
import w.fujiko.service.UnitService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/unit")
public class UnitApi extends Api<UnitService, Unit, Integer> {
	
	private ModelMapper modelMapper;
	
	public UnitApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}

	@GetMapping("/dto")
	public @ResponseBody List<UnitDTO> getDto() {
		
		List<Unit> unitModel = service.get();
		
		Type listType = new TypeToken<List<UnitDTO>>() {}.getType();

		List<UnitDTO> unitDTO = this.modelMapper
										  .map(unitModel,listType);

		return unitDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<UnitDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Unit> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<UnitDTO> pageDTO = new Page<UnitDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<UnitDTO>>() {}.getType();

		List<UnitDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/code-search")
	public ResponseEntity<?> getUndeletedItemByCode(
			@RequestParam(value = "code", defaultValue = "") String code) {
		
		try {
			int cd = Integer.parseInt(code);
			Optional<Unit> result = service.getUndeletedItemByCode(cd);
			
			if(result.isPresent()) {
				Type type = new TypeToken<UnitDTO>() {}.getType();
				UnitDTO unitDTO = this.modelMapper.map(result.get(), type);
				return ResponseEntity.ok().body(unitDTO);
			} else {
				return ResponseEntity.ok().body(null);
			}
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}
	
	@GetMapping("/list/search")
	public ResponseEntity<?> getUnitsByNameOrCode(
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			List<Unit> entities = 
					service.getUnitsByNameOrCode(
							key, Integer.parseInt(index));
			
			Type listType = new TypeToken<List<UnitDTO>>() {}.getType();
			List<UnitDTO> unitsDTO = this.modelMapper.map(entities,listType);
			
			return ResponseEntity.ok().body(unitsDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
				
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createUnit(@Valid @RequestBody UnitCreateDTO unitDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Unit> unitResult = service.getUndeletedItemByCode(unitDTO.getCode());
			if(unitResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_UNIT));
			} else {
				Unit unitEntity = this.modelMapper.map(unitDTO, Unit.class);
				service.saveOrUpdate(unitEntity);
				String createdLink = "/api/unit/"+unitEntity.getId();
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
	public ResponseEntity<?> updateUnit(@Valid @RequestBody UnitUpdateDTO unitDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Unit unitEntity = service.get(unitDTO.getId()).orElseThrow();
			this.modelMapper.map(unitDTO, unitEntity);
			service.saveOrUpdate(unitEntity);
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