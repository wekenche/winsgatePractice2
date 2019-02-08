package w.fujiko.api.logs;

import java.net.URI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.lang.reflect.Type;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Created;
import fte.api.Response;
import fte.api.Page;

import w.fujiko.dto.logs.LogCreateDTO;
import w.fujiko.dto.logs.LogDTO;
import w.fujiko.model.logs.Log;
import w.fujiko.service.logs.LogService;

@RestController
@RequestMapping("/api/log")
public class LogApi
	extends Api<LogService,Log,Integer> {
	
	private ModelMapper modelMapper;
	
	public LogApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<LogDTO> getDTO() {
		
		List<Log> logModel = service.get();
		
		Type listType = new TypeToken<List<LogDTO>>() {}.getType();

		List<LogDTO> logDTO = this.modelMapper
										  .map(logModel,listType);

		return logDTO;
	}

	@GetMapping("/dto/paginate/{first}/{max}")
	public @ResponseBody Page<LogDTO> paginate(@PathVariable(value="first") final Integer first, @PathVariable(value="max") final Integer max) {
		
		Page<Log> logs = service.paginate(first, max);

		Page<LogDTO> paginatedLogs = new Page<LogDTO>();
		paginatedLogs.setFirst(logs.getFirst());
		paginatedLogs.setMax(logs.getMax());
		paginatedLogs.setTotal(logs.getTotal());
		paginatedLogs.setSuccess(logs.getSuccess());		

		Type listType = new TypeToken<List<LogDTO>>() {}.getType();

		List<LogDTO> logDTOs = this.modelMapper
										  .map(logs.getResults(),listType);
		paginatedLogs.setResults(logDTOs);
		return paginatedLogs;
	}

	@GetMapping("/dto/date/{from}/{to}")
	public @ResponseBody ResponseEntity<?> dateRange(@PathVariable(value="from") final String from, 
												     @PathVariable(value="to") final String to) {
		Date dateFrom;
		Date dateTo;

		try {
		    dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
			dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);  
				 
			Calendar cal = Calendar.getInstance(); // creates calendar
			cal.setTime(dateTo);
			cal.set(Calendar.HOUR_OF_DAY, 23); 
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59); 
			dateTo = cal.getTime();
		    
			List<Log> logs = service.findByDateRange(dateFrom,dateTo);			

			Type listType = new TypeToken<List<LogDTO>>() {}.getType();

			List<LogDTO> logDTOs = this.modelMapper
											  .map(logs,listType);
			
			return ResponseEntity
				  .ok()
				  .body(logDTOs);

	    } catch (ParseException e) {			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}

	@GetMapping("/dto/date/{from}/{to}/paginate/{first}/{max}")
	public @ResponseBody ResponseEntity<?> paginate(@PathVariable(value="from") final String from, 
												   @PathVariable(value="to") final String to,
												   @PathVariable(value="first") final Integer first,
												   @PathVariable(value="max") final Integer max) {
		Date dateFrom;
		Date dateTo;

		try {
		    dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
			dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);  	 
			
			Calendar cal = Calendar.getInstance(); // creates calendar
			cal.setTime(dateTo);
			cal.set(Calendar.HOUR_OF_DAY, 23); 
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59); 
			dateTo = cal.getTime();

			Page<Log> logs = service.findByDateRangeWithPagination(dateFrom,dateTo,first, max);

			Page<LogDTO> paginatedLogs = new Page<LogDTO>();
			paginatedLogs.setFirst(logs.getFirst());
			paginatedLogs.setMax(logs.getMax());
			paginatedLogs.setTotal(logs.getTotal());
			paginatedLogs.setSuccess(logs.getSuccess());		

			Type listType = new TypeToken<List<LogDTO>>() {}.getType();

			List<LogDTO> logDTOs = this.modelMapper
											  .map(logs.getResults(),listType);
			paginatedLogs.setResults(logDTOs);
			
			return ResponseEntity
				  .ok()
				  .body(paginatedLogs);

	    } catch (ParseException e) {			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}

	@PostMapping("/create")
	public @ResponseBody ResponseEntity<?> save(@Valid @RequestBody LogCreateDTO log,
												Errors error,
												HttpServletRequest request) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			
			Log logModel = this.modelMapper.map(log,Log.class);
			logModel.setIpAddress(request.getRemoteAddr());
			service.saveOrUpdate(logModel);
			String createdLink = "/api/log/"+logModel.getId();
			return ResponseEntity
				  .created(new URI(createdLink))
				  .body(new Created(createdLink));

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
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("{ status: 500, message: bad data. }");
		}
	}
}