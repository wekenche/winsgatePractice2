package w.fujiko.api.courses;

import static w.fujiko.util.common.constants.CourseConstants.MSG_500;
import static w.fujiko.util.common.constants.CourseConstants.MSG_CONFLICT_COURSE;

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
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Created;
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.courses.CourseCreateDTO;
import w.fujiko.dto.courses.CourseDTO;
import w.fujiko.dto.courses.CourseUpdateDTO;
import w.fujiko.model.masters.courses.Course;
import w.fujiko.service.courses.CourseService;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/course")
public class CourseApi extends Api<CourseService, Course, Integer> {
	
	private ModelMapper modelMapper;
	
	public CourseApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}

	@GetMapping("/search")
	public ResponseEntity<?> getCoursesByNameOrCode(
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			int indexNo = Integer.parseInt(index);
			List<Course> entities = service.getCoursesByNameOrCode(key, indexNo);
			
			Type listType = new TypeToken<List<CourseDTO>>() {}.getType();
			List<CourseDTO> coursesDTO = this.modelMapper.map(entities, listType);
			
			return ResponseEntity.ok().body(coursesDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createCourse(@Valid @RequestBody CourseCreateDTO courseDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Course> courseResult = service.getUndeletedCourseByCode(courseDTO.getCode());
			
			if(courseResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_COURSE));
			} else {
				Course courseEntity = this.modelMapper.map(courseDTO, Course.class);
				service.saveOrUpdate(courseEntity);
				String createdLink = "/api/course/"+courseEntity.getId();
				return ResponseEntity
						.created(new URI(createdLink))
						.body(new Created(createdLink));
			}
		}catch(ConstraintViolationException cve){
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe) {
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
	public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseUpdateDTO courseDTO, Errors error) {
		
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Course courseEntity = service.get(courseDTO.getId()).orElseThrow();
			this.modelMapper.map(courseDTO, courseEntity);
			service.saveOrUpdate(courseEntity);
			return ResponseEntity.ok().body(new Success());
		}catch(ConstraintViolationException cve) {
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe) {
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