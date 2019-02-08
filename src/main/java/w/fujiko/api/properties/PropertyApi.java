package w.fujiko.api.properties;

import static w.fujiko.util.common.constants.PropertyConstants.MSG_500;
import static w.fujiko.util.common.constants.PropertyConstants.MSG_CANNOT_DELETE;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.properties.PropertyCreateDTO;
import w.fujiko.dto.properties.PropertyDTO;
import w.fujiko.dto.properties.PropertyDefaultDTO;
import w.fujiko.dto.properties.PropertyUpdateDTO;
import w.fujiko.model.masters.properties.Property;
import w.fujiko.service.properties.PropertyService;
import w.fujiko.service.transactions.quotations.QuotationHeaderService;
import w.fujiko.service.transactions.quotations.histories.QuotationHeaderHistoryService;
import w.fujiko.util.common.constants.PropertyConstants;
import w.fujiko.util.common.generator.slips.WorkingSiteSlipNumGenerator;
import w.fujiko.util.common.stringconverter.StringConverter;
import w.fujiko.util.mapper.propertymaps.properties.CreateDTOToPropertyEntityMap;
import w.fujiko.util.mapper.propertymaps.properties.UpdateDTOToPropertyEntityMap;

@RestController
@RequestMapping("/api/property")
public class PropertyApi extends Api<PropertyService, Property, Integer> {
	
	@Autowired
	private QuotationHeaderService quotationHeaderService;
	
	@Autowired
	private QuotationHeaderHistoryService quotationHeaderHistoryService;
	
	@Autowired
	private WorkingSiteSlipNumGenerator workingSiteSlipNumGenerator;
	
	private ModelMapper modelMapper;
	
	public PropertyApi() {
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setAmbiguityIgnored(false);
	}
	
	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Property> response = service.get(id);

		if(!response.isPresent())
			return ResponseEntity.notFound().build();

		Property property = response.get();
		PropertyDefaultDTO dto = this.modelMapper.map(property, PropertyDefaultDTO.class);

		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/{propertyNo}/property-no-search")
	public ResponseEntity<?> getPropertyByNo(
			@PathVariable(value="propertyNo") Integer propertyNo) {
		
		try {
			Optional<Property> result = service.getPropertyByNo(propertyNo);
			
			if(result.isPresent()) {
				Type type = new TypeToken<PropertyDTO>() {}.getType();
				PropertyDTO propertyDTO = this.modelMapper.map(result.get(), type);
				return ResponseEntity.ok().body(propertyDTO);
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
	public ResponseEntity<?> getProperties(
			@RequestParam(value = "dept_id", defaultValue = "-1") String departmentId,
			@RequestParam(value = "date_from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom,
			@RequestParam(value = "date_to", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTo,
			@RequestParam(value = "no", defaultValue = "-1") String propertyNo,
			@RequestParam(value = "name", defaultValue = "") String propertyName,
			@RequestParam(value = "kana", defaultValue = "") String propertyKana,
			@RequestParam(value = "user_cd", defaultValue = "-1") String registrationOfficerCode,
			@RequestParam(value = "user_name", defaultValue = "") String registrationOfficerName,
			@RequestParam(value = "branch_cd", defaultValue = "-1") String branchCode,
			@RequestParam(value = "branch_name", defaultValue = "") String branchName,
			@RequestParam(value = "owner", defaultValue = "") String owner,
			@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			List<Property> entities = service.getProperties(
					Integer.parseInt(departmentId),
					dateFrom,
					dateTo,
					Integer.parseInt(propertyNo),
					propertyName,
					propertyKana,
					Integer.parseInt(registrationOfficerCode),
					registrationOfficerName,
					Integer.parseInt(branchCode),
					branchName,
					owner,
					Integer.parseInt(index));
			
			Type listType = new TypeToken<List<PropertyDTO>>() {}.getType();
			List<PropertyDTO> propertiesDTO = this.modelMapper.map(entities,listType);
			
			List<PropertyDTO> results = propertiesDTO.stream()
					.map(element -> {
						PropertyDTO dto = element;
						long count = quotationHeaderService.getCountByWorkingSiteId(element.getId());
						count += quotationHeaderHistoryService.getCountByWorkingSiteId(element.getId());
						dto.setPropertyCount(count);
						return dto;
					})
					.collect(Collectors.toList());
			
			return ResponseEntity.ok().body(results);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}

	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProperty(@Valid @RequestBody PropertyCreateDTO propertyDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			var modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			modelMapper.addMappings(new CreateDTOToPropertyEntityMap());
			
			Property propertyEntity = modelMapper.map(propertyDTO, Property.class);

			String generatedSlipNumber = workingSiteSlipNumGenerator.generate(propertyDTO.getCreatedById());
			propertyEntity.setPropertyNo(Integer.parseInt(generatedSlipNumber));
			service.saveOrUpdate(propertyEntity);
			String createdLink = "/api/property/"+propertyEntity.getId();
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
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProperty(@Valid @RequestBody PropertyUpdateDTO propertyDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			var modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setAmbiguityIgnored(true);
			modelMapper.addMappings(new UpdateDTOToPropertyEntityMap());
			
			Property propertyEntity = service.get(propertyDTO.getId()).orElseThrow();
			Integer propertyNo = propertyEntity.getPropertyNo();

			modelMapper.map(propertyDTO, propertyEntity);
			propertyEntity.setPropertyNo(propertyNo);
			service.saveOrUpdate(propertyEntity);
			
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
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProperty(@Valid @RequestBody PropertyUpdateDTO propertyDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		long count = quotationHeaderService.getCountByWorkingSiteId(propertyDTO.getId());
		count += quotationHeaderHistoryService.getCountByWorkingSiteId(propertyDTO.getId());
		
		if(count == 0) {
			propertyDTO.setDeletedFLG(true);
			return updateProperty(propertyDTO, error);
		}

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(StringConverter.convertToReadable(MSG_CANNOT_DELETE));

	}
	
	@GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<?> generatePDF(
			@RequestParam(value = "from", defaultValue = "0") String from,
			@RequestParam(value = "to", defaultValue = "0") String to) {
				
		try {			
			HttpHeaders headers = getHeaders(PropertyConstants.PDF_FILE_NAME);
			int codeFrom = Integer.parseInt(from);
			int codeTo = Integer.parseInt(to);
			return ResponseEntity.ok()
					.headers(headers)
					.contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(service.exportToPDF(codeFrom, codeTo)));
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		} 
		
	}
	
	@GetMapping(value = "/export-csv", produces = "text/csv")
	public ResponseEntity<?> generateCSV(
			@RequestParam(value = "from", defaultValue = "0") String from,
			@RequestParam(value = "to", defaultValue = "0") String to) {
					
		try {
			HttpHeaders headers = getHeaders(PropertyConstants.CSV_FILE_NAME);
			int codeFrom = Integer.parseInt(from);
			int codeTo = Integer.parseInt(to);
			return ResponseEntity.ok()
					.headers(headers)
					.body(service.exportToCSV(codeFrom, codeTo));
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}
	}
	
	private HttpHeaders getHeaders(String fileName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}

}