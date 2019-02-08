package w.fujiko.api.purchaseorders;


import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fte.api.Api;
import fte.api.Page;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderDTO;
import w.fujiko.dto.purchaseorders.headers.PurchaseOrderHeaderSearchDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderHeader;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderHeaderService;
import w.fujiko.util.mapper.propertymaps.purchaseorders.headers.CreateDTOToPurchaseOrderHeaderMap;
import w.fujiko.util.mapper.propertymaps.purchaseorders.headers.PurchaseOrderHeaderToDTOMap;

@RestController
@RequestMapping("/api/po/header")
public class PurchaseOrderHeaderApi
	extends Api<PurchaseOrderHeaderService, PurchaseOrderHeader, Integer> {
	
	private ModelMapper purchaseOrderHeaderToDTOMapper;
	private ModelMapper createDTOToPurchaseOrderHeaderMapper;
	
	public PurchaseOrderHeaderApi() {
		purchaseOrderHeaderToDTOMapper = new ModelMapper();
		purchaseOrderHeaderToDTOMapper.getConfiguration().setAmbiguityIgnored(true);
		purchaseOrderHeaderToDTOMapper.addMappings(new PurchaseOrderHeaderToDTOMap());
		
		createDTOToPurchaseOrderHeaderMapper = new ModelMapper();
		createDTOToPurchaseOrderHeaderMapper.getConfiguration().setAmbiguityIgnored(true);
		createDTOToPurchaseOrderHeaderMapper.addMappings(new CreateDTOToPurchaseOrderHeaderMap());
	}
	
	@GetMapping("/slipNum/{slipNum}")
	public @ResponseBody PurchaseOrderHeaderDTO getBySlipNumber(@PathVariable("slipNum")final String slipNum) {
		
		PurchaseOrderHeader purchaseOrderHeader = service.getBySlipNumber(slipNum).orElseThrow();
		
		PurchaseOrderHeaderDTO purchaseOrderHeaderDTO = this.purchaseOrderHeaderToDTOMapper
													.map(purchaseOrderHeader, PurchaseOrderHeaderDTO.class);
		return purchaseOrderHeaderDTO;
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<PurchaseOrderHeaderDTO> getDTO() {
		
		List<PurchaseOrderHeader> purchaseOrderHeaderModel = service.get();

		Type listType = new TypeToken<List<PurchaseOrderHeaderDTO>>() {}.getType();
		
		List<PurchaseOrderHeaderDTO> purchaseOrderHeaderDTO = this.purchaseOrderHeaderToDTOMapper
										 				  .map(purchaseOrderHeaderModel,listType);
		return purchaseOrderHeaderDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<PurchaseOrderHeaderDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<PurchaseOrderHeader> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<PurchaseOrderHeaderDTO> pageDTO = new Page<PurchaseOrderHeaderDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<PurchaseOrderHeaderDTO>>() {}.getType();

		List<PurchaseOrderHeaderDTO> resultsDTO = this.purchaseOrderHeaderToDTOMapper
										  .map(pageEntity.getResults(), listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/search")
	public Page<PurchaseOrderHeaderDTO> search(PurchaseOrderHeaderSearchDTO searchKeys) {
		
		Page<PurchaseOrderHeader> entity = service.purchaseOrderHeaderSearch(searchKeys);
		
		Page<PurchaseOrderHeaderDTO> pageDTO = new Page<PurchaseOrderHeaderDTO>();
		pageDTO.setFirst(entity.getFirst());
		pageDTO.setMax(entity.getMax());
		pageDTO.setTotal(entity.getTotal());
		pageDTO.setSuccess(entity.getSuccess());
		
		Type listType = new TypeToken<List<PurchaseOrderHeaderDTO>>() {}.getType();

		List<PurchaseOrderHeaderDTO> resultsDTO = this.purchaseOrderHeaderToDTOMapper.map(entity.getResults(), listType);
		pageDTO.setResults(resultsDTO);

		return pageDTO;

	}
	
	@GetMapping("/report/{slipNumber}")
	public void generateReport(
			@PathVariable("slipNumber") String slipNumber, 
			HttpServletResponse response) {
		
		service.generateReport(slipNumber, response);
	}

}