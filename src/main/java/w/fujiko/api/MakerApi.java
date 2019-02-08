package w.fujiko.api;

import static w.fujiko.util.common.constants.MakerConstants.MSG_500;
import static w.fujiko.util.common.constants.MakerConstants.MSG_CONFLICT_MAKER;

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
import w.fujiko.dto.makers.MakerCreateDTO;
import w.fujiko.dto.makers.MakerDTO;
import w.fujiko.dto.makers.MakerUpdateDTO;
import w.fujiko.model.masters.Maker;
import w.fujiko.service.MakerService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/maker")
public class MakerApi extends Api<MakerService, Maker, Integer> {
	
	private ModelMapper modelMapper;
	
	public MakerApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<MakerDTO> getDto() {
		
		List<Maker> makerModel = service.get();
		
		Type listType = new TypeToken<List<MakerDTO>>() {}.getType();

		List<MakerDTO> makerDTO = this.modelMapper
										  .map(makerModel,listType);

		return makerDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<MakerDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Maker> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<MakerDTO> pageDTO = new Page<MakerDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<MakerDTO>>() {}.getType();

		List<MakerDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Maker> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            Maker maker = response.get();

		
        MakerDTO makerDTO = this.modelMapper
										  .map(maker,MakerDTO.class);

		return ResponseEntity
				.ok(makerDTO);
	}

	@GetMapping("/code-search")
	public ResponseEntity<?> getUndeletedItemByCode(
			@RequestParam(value = "code", defaultValue = "") String code) {
		
		try {
			int mkrCode = Integer.parseInt(code);
			Optional<Maker> result = service.getUndeletedItemByCode(mkrCode);
			
			if(result.isPresent()) {
				Type type = new TypeToken<MakerDTO>() {}.getType();
				MakerDTO makerDTO = this.modelMapper.map(result.get(), type);
				return ResponseEntity.ok().body(makerDTO);
			} else {
				return ResponseEntity.ok().body(null);
			}
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> getByName(@PathVariable("name")final String name,
									   @RequestParam(value = "isEnd", required = false) Boolean isEnd) {
		
		try {			
			Maker maker = service.getByName(name, Optional.ofNullable(isEnd))
								 .orElseThrow();
			return ResponseEntity.ok().body(modelMapper.map(maker, MakerDTO.class));
		}catch (NoSuchElementException ex) {
			return ResponseEntity
					.notFound()
					.build();
		}catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}

	@PostMapping("/create")
	public ResponseEntity<?> createMaker(@Valid @RequestBody MakerCreateDTO makerDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Maker> makerResult = service.getUndeletedItemByCode(makerDTO.getCode());
			if(makerResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_MAKER));
			} else {
				Maker makerEntity = this.modelMapper.map(makerDTO, Maker.class);
				service.saveOrUpdate(makerEntity);
				String createdLink = "/api/maker/"+makerEntity.getId();
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
	public ResponseEntity<?> updateMaker(@Valid @RequestBody MakerUpdateDTO makerDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Maker makerEntity = service.get(makerDTO.getId()).orElseThrow();
			this.modelMapper.map(makerDTO, makerEntity);
			service.saveOrUpdate(makerEntity);
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