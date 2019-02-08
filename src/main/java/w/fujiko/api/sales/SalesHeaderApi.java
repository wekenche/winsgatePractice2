package w.fujiko.api.sales;

import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Page;

import w.fujiko.dto.sales.headers.SalesHeaderDTO;
import w.fujiko.model.transactions.sales.SalesHeader;
import w.fujiko.service.transactions.sales.SalesHeaderService;
import w.fujiko.util.mapper.propertymaps.sales.headers.CreateDTOToSalesHeaderMap;
import w.fujiko.util.mapper.propertymaps.sales.headers.SalesHeaderToDTOMap;

@RestController
@RequestMapping("/api/sales/header")
public class SalesHeaderApi
	extends Api<SalesHeaderService, SalesHeader, Integer> {
	
	private ModelMapper salesHeaderToDTOMapper;
	private ModelMapper createDTOToSalesHeaderMapper;
	
	public SalesHeaderApi() {
		salesHeaderToDTOMapper = new ModelMapper();
		salesHeaderToDTOMapper.getConfiguration().setAmbiguityIgnored(true);
		salesHeaderToDTOMapper.addMappings(new SalesHeaderToDTOMap());
		
		createDTOToSalesHeaderMapper = new ModelMapper();
		createDTOToSalesHeaderMapper.getConfiguration().setAmbiguityIgnored(true);
		createDTOToSalesHeaderMapper.addMappings(new CreateDTOToSalesHeaderMap());
	}
	
	@GetMapping("/slipNum/{slipNum}")
	public @ResponseBody ResponseEntity<?> getBySlipNumber(@PathVariable("slipNum")final String slipNum) {
        
        try{
		var salesHeader = service.getBySlipNumber(slipNum).orElseThrow();
		
		SalesHeaderDTO salesHeaderDTO = this.salesHeaderToDTOMapper
                                            .map(salesHeader, SalesHeaderDTO.class);
        return ResponseEntity
              .ok()
              .body(salesHeaderDTO);
        }catch(NoSuchElementException e){
            return ResponseEntity
                   .notFound()
                   .build();
        }
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<SalesHeaderDTO> getDTO() {
		
		List<SalesHeader> salesHeaderModel = service.get();

		Type listType = new TypeToken<List<SalesHeaderDTO>>() {}.getType();
		
		List<SalesHeaderDTO> salesHeaderDTO = this.salesHeaderToDTOMapper
                                                  .map(salesHeaderModel,listType);
		return salesHeaderDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<SalesHeaderDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<SalesHeader> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<SalesHeaderDTO> pageDTO = new Page<SalesHeaderDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<SalesHeaderDTO>>() {}.getType();

		List<SalesHeaderDTO> resultsDTO = this.salesHeaderToDTOMapper
										  .map(pageEntity.getResults(), listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	// @GetMapping("/search")
	// public Page<PurchaseOrderHeaderDTO> search(PurchaseOrderHeaderSearchDTO searchKeys) {
		
	// 	Page<PurchaseOrderHeader> entity = service.purchaseOrderHeaderSearch(searchKeys);
		
	// 	Page<PurchaseOrderHeaderDTO> pageDTO = new Page<PurchaseOrderHeaderDTO>();
	// 	pageDTO.setFirst(entity.getFirst());
	// 	pageDTO.setMax(entity.getMax());
	// 	pageDTO.setTotal(entity.getTotal());
	// 	pageDTO.setSuccess(entity.getSuccess());
		
	// 	Type listType = new TypeToken<List<PurchaseOrderHeaderDTO>>() {}.getType();

	// 	List<PurchaseOrderHeaderDTO> resultsDTO = this.purchaseOrderHeaderToDTOMapper.map(entity.getResults(), listType);
	// 	pageDTO.setResults(resultsDTO);

	// 	return pageDTO;

	// }

}