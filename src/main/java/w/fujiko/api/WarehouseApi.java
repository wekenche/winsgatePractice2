package w.fujiko.api;

import static w.fujiko.util.common.constants.WarehouseConstants.MSG_500;
import static w.fujiko.util.common.constants.WarehouseConstants.MSG_CONFLICT_WAREHOUSE;

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
import w.fujiko.dto.warehouses.WarehouseCreateDTO;
import w.fujiko.dto.warehouses.WarehouseDTO;
import w.fujiko.dto.warehouses.WarehouseUpdateDTO;
import w.fujiko.model.masters.Warehouse;
import w.fujiko.service.WarehouseService;
import w.fujiko.util.common.stringconverter.StringConverter;

/**
 * 
 * @author johnl
 *
 */
@RestController
@RequestMapping("/api/warehouse")
public class WarehouseApi extends Api<WarehouseService, Warehouse, Integer>{

    private ModelMapper modelMapper;
	
	public WarehouseApi(){
		modelMapper = new ModelMapper();	
		modelMapper.getConfiguration()
					.setFieldMatchingEnabled(true)
					.setFieldAccessLevel(AccessLevel.PRIVATE);
    }
    
    @GetMapping("/dto")
	public @ResponseBody List<WarehouseDTO> getDto() {
		
		List<Warehouse> warehouseModel = service.get();
		
		Type listType = new TypeToken<List<WarehouseDTO>>() {}.getType();

		List<WarehouseDTO> warehouseDTO = this.modelMapper
										  .map(warehouseModel,listType);

		return warehouseDTO;
	}
    
    @GetMapping("/dto/v2")
	public @ResponseBody Page<WarehouseDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<Warehouse> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<WarehouseDTO> pageDTO = new Page<WarehouseDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<WarehouseDTO>>() {}.getType();

		List<WarehouseDTO> resultsDTO = this.modelMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}

	@Override
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<?> getById(@PathVariable(value="id") final Integer id) {
		Optional<Warehouse> response = service.get(id);
		
		if(!response.isPresent())
			return ResponseEntity.notFound().build();
		
            Warehouse warehouse = response.get();

		
        WarehouseDTO warehouseDTO = this.modelMapper
										  .map(warehouse,WarehouseDTO.class);

		return ResponseEntity
				.ok(warehouseDTO);
	}

    @GetMapping("/list/search")
	public ResponseEntity<?> getWarehousesByNameOrCode(
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "index", defaultValue = "0") String index) {
		
		try {
			List<Warehouse> entities = 
					service.getWarehousesByNameOrCode(
							key, Integer.parseInt(index));
			
			Type listType = new TypeToken<List<WarehouseDTO>>() {}.getType();
			List<WarehouseDTO> warehousesDTO = this.modelMapper.map(entities,listType);
			
			return ResponseEntity.ok().body(warehousesDTO);
		} catch (Exception ex) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(MSG_500));
		}

	}
    
    @PostMapping("/create")
	public ResponseEntity<?> createWarehouse(@Valid @RequestBody WarehouseCreateDTO warehouseDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
		
		try {
			Optional<Warehouse> warehouseResult = service.getUndeletedWarehouseByCode(warehouseDTO.getCode());
			if(warehouseResult.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(StringConverter.convertToReadable(MSG_CONFLICT_WAREHOUSE));
			} else {
				Warehouse warehouseEntity = this.modelMapper.map(warehouseDTO, Warehouse.class);
				service.saveOrUpdate(warehouseEntity);
				String createdLink = "/api/warehouse/"+warehouseEntity.getId();
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
	public ResponseEntity<?> updateWarehouse(@Valid @RequestBody WarehouseUpdateDTO warehouseDTO, Errors error) {
		if(error.hasErrors()) 
			return ResponseEntity.badRequest().body(error.getAllErrors());
	
		try {
			Warehouse warehouseEntity = service.get(warehouseDTO.getId()).orElseThrow();
			this.modelMapper.map(warehouseDTO, warehouseEntity);
			service.saveOrUpdate(warehouseEntity);
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