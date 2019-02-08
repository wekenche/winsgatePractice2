package w.fujiko.api.sales;

import java.lang.reflect.Type;
import java.util.List;

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
import w.fujiko.dto.sales.details.SalesDetailDTO;

import w.fujiko.model.transactions.sales.SalesDetail;
import w.fujiko.model.transactions.sales.SalesDetailPk;

import w.fujiko.service.transactions.sales.SalesDetailService;

import w.fujiko.util.mapper.propertymaps.sales.details.SalesDetailToDTOMap;

@RestController
@RequestMapping("/api/sales/detail")
public class SalesDetailApi 
	extends Api<SalesDetailService, SalesDetail, SalesDetailPk> {
	
	private ModelMapper salesDetailToDTOMapper;
	
	public SalesDetailApi() {
		salesDetailToDTOMapper = new ModelMapper();
		salesDetailToDTOMapper.getConfiguration().setAmbiguityIgnored(true);
		salesDetailToDTOMapper.addMappings(new SalesDetailToDTOMap());
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<SalesDetailDTO> getDTO() {
		
		List<SalesDetail> salesDetailModel = service.get();
		
		Type listType = new TypeToken<List<SalesDetailDTO>>() {}.getType();
		
		List<SalesDetailDTO> salesDetailDTO = this.salesDetailToDTOMapper
                                                  .map(salesDetailModel,listType);
		return salesDetailDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<SalesDetailDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<SalesDetail> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<SalesDetailDTO> pageDTO = new Page<SalesDetailDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<SalesDetailDTO>>() {}.getType();

		List<SalesDetailDTO> resultsDTO = this.salesDetailToDTOMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/header/{salesHeaderId}")
	public @ResponseBody List<SalesDetailDTO> getBySalesHeaderId(
			@PathVariable("salesHeaderId") final Integer salesHeaderId) {
		
		List<SalesDetail> salesDetailModel = service.getBySalesHeaderId(salesHeaderId);
		
		Type listType = new TypeToken<List<SalesDetailDTO>>() {}.getType();
		
		List<SalesDetailDTO> salesDetailDTO = this.salesDetailToDTOMapper
										 				  .map(salesDetailModel,listType);
		return salesDetailDTO;
	}

}