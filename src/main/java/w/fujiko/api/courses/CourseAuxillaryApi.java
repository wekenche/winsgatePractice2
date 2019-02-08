package w.fujiko.api.courses;

import static w.fujiko.util.common.constants.CourseConstants.MSG_500;
import static w.fujiko.util.common.constants.CourseConstants.MSG_CONFLICT_COURSE_AUX;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Created;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.courses.CourseAuxillaryCreateDTO;
import w.fujiko.dto.courses.CourseAuxillaryDTO;
import w.fujiko.dto.courses.CourseAuxillaryUpdateDTO;
import w.fujiko.model.masters.courses.CourseAuxillary;
import w.fujiko.service.courses.CourseAuxillaryService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/course/auxillary")
public class CourseAuxillaryApi extends Api<CourseAuxillaryService, CourseAuxillary, Integer> {
	
	private ModelMapper modelMapper;
	
	public CourseAuxillaryApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<CourseAuxillary> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            CourseAuxillary courseAuxillary = response.get();

		
        CourseAuxillaryDTO bankBranchDTO = this.modelMapper
										  .map(courseAuxillary,CourseAuxillaryDTO.class);

		return ResponseEntity
				.ok(bankBranchDTO);
	}
	
	@GetMapping("/{courseId}/search")
	public ResponseEntity<?> getCourseAuxillariesByNameOrCode(
				@PathVariable(value="courseId") Integer courseId,
				@RequestParam(value = "key", defaultValue = "") String key,
				@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			int indexNo = Integer.parseInt(index);
			List<CourseAuxillary> entities = 
					service.getCourseAuxillariesByNameOrCode(courseId, key, indexNo);
			
			Type listType = new TypeToken<List<CourseAuxillaryDTO>>() {}.getType();
			List<CourseAuxillaryDTO> auxillariesDTO = this.modelMapper.map(entities, listType);
			
			return ResponseEntity.ok().body(auxillariesDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createCourseAuxillary
		(@Valid @RequestBody CourseAuxillaryCreateDTO courseAuxillaryDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			int courseId = courseAuxillaryDTO.getCourseId();
			Optional<CourseAuxillary> courseAuxillaryResult = 
					service.getUndeletedCourseAuxillaryByCode(courseId, courseAuxillaryDTO.getCode());
			
			if(courseAuxillaryResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_COURSE_AUX));
			} else {
				CourseAuxillary auxillaryEntity = this.modelMapper.map(courseAuxillaryDTO, CourseAuxillary.class);
				service.saveOrUpdate(auxillaryEntity);
				String createdLink = "/api/course/auxillary/"+auxillaryEntity.getId();
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
	public ResponseEntity<?> updateCourseAuxillary
		(@Valid @RequestBody CourseAuxillaryUpdateDTO courseAuxillaryDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			CourseAuxillary auxillaryEntity = service.get(courseAuxillaryDTO.getId()).orElseThrow();
			this.modelMapper.map(courseAuxillaryDTO, auxillaryEntity);
			service.saveOrUpdate(auxillaryEntity);
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