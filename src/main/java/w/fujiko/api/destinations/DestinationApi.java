package w.fujiko.api.destinations;

import static w.fujiko.util.common.constants.DestinationConstants.MSG_500;
import static w.fujiko.util.common.constants.DestinationConstants.MSG_CONFLICT_DESTINATION;

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
import w.fujiko.dto.destinations.DestinationCreateDTO;
import w.fujiko.dto.destinations.DestinationDTO;
import w.fujiko.dto.destinations.DestinationUpdateDTO;
import w.fujiko.model.masters.destinations.Destination;
import w.fujiko.service.destinations.DestinationService;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.destinations.CreateDTOToDestinationEntityMap;
import w.fujiko.util.mapper.propertymaps.destinations.UpdateDTOToDestinationEnitityMap;

@RestController
@RequestMapping("/api/destination")
public class DestinationApi
	extends Api<DestinationService,Destination,Integer> {
	
	private ModelMapper modelMapper;
	
	public DestinationApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(true);
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<DestinationDTO> getDTO() {
		
		List<Destination> destinationModel = service.get();
		
		Type listType = new TypeToken<List<DestinationDTO>>() {}.getType();

		List<DestinationDTO> destinationDTO = this.modelMapper
										  .map(destinationModel,listType);

		return destinationDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<DestinationDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Destination> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<DestinationDTO> pageDTO = new Page<DestinationDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<DestinationDTO>>() {}.getType();

		List<DestinationDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Destination> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            Destination destination = response.get();

		
        DestinationDTO destinationDTO = this.modelMapper
										  .map(destination,DestinationDTO.class);

		return ResponseEntity
				.ok(destinationDTO);
	}

	@GetMapping("/customer/{customerCode}")
	public @ResponseBody List<DestinationDTO> getByCustomerCode(@PathVariable(value="customerCode") final String customerCode) {
		var response = service.getByCustomerCode(customerCode);

		Type listType = new TypeToken<List<DestinationDTO>>() {}.getType();

        var destinations = this.modelMapper
						   .<List<DestinationDTO>>map(response,listType);

		return destinations;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createDestination(@Valid @RequestBody DestinationCreateDTO destinationDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Destination> destinationResult = service.getUndeletedDestinationByCode(destinationDTO.getCode());
			var modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			modelMapper.addMappings(new CreateDTOToDestinationEntityMap());

			if(destinationResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_DESTINATION));
			} else {
				Destination destinationEntity = modelMapper.map(destinationDTO, Destination.class);
				service.saveOrUpdate(destinationEntity);
				String createdLink = "/api/destination/"+destinationEntity.getId();
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
	public ResponseEntity<?> updateMaker(@Valid @RequestBody DestinationUpdateDTO destinationDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Destination destinationEntity = service.get(destinationDTO.getId()).orElseThrow();
			var modelMapper = new ModelMapper();
	 		modelMapper.getConfiguration().setAmbiguityIgnored(true);
			modelMapper.addMappings(new UpdateDTOToDestinationEnitityMap());
			
			modelMapper.map(destinationDTO, destinationEntity);
			service.saveOrUpdate(destinationEntity);
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