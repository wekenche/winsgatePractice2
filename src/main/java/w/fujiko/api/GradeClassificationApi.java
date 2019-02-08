package w.fujiko.api;

import static w.fujiko.util.common.constants.GradeClassificationConstants.MSG_500;
import static w.fujiko.util.common.constants.GradeClassificationConstants.MSG_CONFLICT_GRADE_CLASSIFICATION;

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
import w.fujiko.dto.gradeclassifications.GradeClassificationCreateDTO;
import w.fujiko.dto.gradeclassifications.GradeClassificationDTO;
import w.fujiko.dto.gradeclassifications.GradeClassificationUpdateDTO;
import w.fujiko.model.masters.GradeClassification;
import w.fujiko.service.GradeClassificationService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/grade/classification")
public class GradeClassificationApi extends Api<GradeClassificationService, GradeClassification, Short> {
	
	private ModelMapper modelMapper;
	
	public GradeClassificationApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<GradeClassificationDTO> getDto() {
		
		List<GradeClassification> gradeClassificationModel = service.get();
		
		Type listType = new TypeToken<List<GradeClassificationDTO>>() {}.getType();

		List<GradeClassificationDTO> gradeClassificationDTO = this.modelMapper
										                          .map(gradeClassificationModel,listType);
		return gradeClassificationDTO;
    }
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<GradeClassificationDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<GradeClassification> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<GradeClassificationDTO> pageDTO = new Page<GradeClassificationDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<GradeClassificationDTO>>() {}.getType();

		List<GradeClassificationDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
    
    @GetMapping("/maker/{makerId}")
	public @ResponseBody List<GradeClassificationDTO> getByMakerId(@PathVariable("makerId") final Integer makerId) {
		
		List<GradeClassification> gradeClassificationModel = service.getByMakerId(makerId);
		
		Type listType = new TypeToken<List<GradeClassificationDTO>>() {}.getType();

		List<GradeClassificationDTO> gradeClassificationDTO = this.modelMapper
										                          .map(gradeClassificationModel,listType);
		return gradeClassificationDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<GradeClassification> response = service.get(id.shortValue());
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
        GradeClassification gradeClassification = response.get();
		
        GradeClassificationDTO gradeClassificationDTO = this.modelMapper
                                                            .map(gradeClassification,GradeClassificationDTO.class);
		return ResponseEntity
				.ok(gradeClassificationDTO);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createMaker(@Valid @RequestBody GradeClassificationCreateDTO gradeClassCreateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
            Optional<GradeClassification> gradeClassificationResult = service
                                                                     .getUndeletedItemByCode(gradeClassCreateDTO.getCode(),
                                                                                             gradeClassCreateDTO.getMakerId());
			if(gradeClassificationResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_GRADE_CLASSIFICATION));
			} else {
                GradeClassification gradeClassificationEntity = this.modelMapper.map(gradeClassCreateDTO, GradeClassification.class);
                gradeClassificationEntity.setId(null);                
				service.saveOrUpdate(gradeClassificationEntity);
				String createdLink = "/api/grade/classification/"+gradeClassificationEntity.getId();
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
	public ResponseEntity<?> updateMaker(@Valid @RequestBody GradeClassificationUpdateDTO gradeClassUpdateDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
            GradeClassification gradeClassEntity = service.get(gradeClassUpdateDTO.getId())
                                                          .orElseThrow();
            this.modelMapper.map(gradeClassUpdateDTO, gradeClassEntity);
			service.saveOrUpdate(gradeClassEntity);
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