package w.fujiko.api.purchaseorders;

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
import w.fujiko.dto.purchaseorders.details.PurchaseOrderDetailDTO;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetail;
import w.fujiko.model.transactions.purchaseorders.PurchaseOrderDetailPk;
import w.fujiko.service.transactions.purchaseorders.PurchaseOrderDetailService;
import w.fujiko.util.mapper.propertymaps.purchaseorders.details.PurchaseOrderDetailDTOMap;

@RestController
@RequestMapping("/api/po/detail")
public class PurchaseOrderDetailApi 
	extends Api<PurchaseOrderDetailService, PurchaseOrderDetail, PurchaseOrderDetailPk> {
	
	private ModelMapper purchaseOrderDetailToDTOMapper;
	
	public PurchaseOrderDetailApi() {
		purchaseOrderDetailToDTOMapper = new ModelMapper();
		purchaseOrderDetailToDTOMapper.getConfiguration().setAmbiguityIgnored(true);
		purchaseOrderDetailToDTOMapper.addMappings(new PurchaseOrderDetailDTOMap());
	}
	
	@GetMapping("/dto")
	public @ResponseBody List<PurchaseOrderDetailDTO> getDTO() {
		
		List<PurchaseOrderDetail> purchaseOrderDetailModel = service.get();
		
		Type listType = new TypeToken<List<PurchaseOrderDetailDTO>>() {}.getType();
		
		List<PurchaseOrderDetailDTO> purchaseOrderDetailDTO = this.purchaseOrderDetailToDTOMapper
										 				  .map(purchaseOrderDetailModel,listType);
		return purchaseOrderDetailDTO;
	}
	
	@GetMapping("/dto/v2")
	public @ResponseBody Page<PurchaseOrderDetailDTO> paginate(
			@RequestParam(value = "first", defaultValue = "0") String first,
			@RequestParam(value = "max", defaultValue = "30") String max,
			@RequestParam(value = "is_end", required = false) Boolean isEnd) {
		
		Page<PurchaseOrderDetail> pageEntity = service.paginate(
				Integer.parseInt(first), 
				Integer.parseInt(max), isEnd);
		Page<PurchaseOrderDetailDTO> pageDTO = new Page<PurchaseOrderDetailDTO>();
		pageDTO.setFirst(pageEntity.getFirst());
		pageDTO.setMax(pageEntity.getMax());
		pageDTO.setTotal(pageEntity.getTotal());
		pageDTO.setSuccess(pageEntity.getSuccess());
		
		Type listType = new TypeToken<List<PurchaseOrderDetailDTO>>() {}.getType();

		List<PurchaseOrderDetailDTO> resultsDTO = this.purchaseOrderDetailToDTOMapper
										  .map(pageEntity.getResults(),listType);
		
		pageDTO.setResults(resultsDTO);

		return pageDTO;
	}
	
	@GetMapping("/header/{purchaseOrderHeaderId}")
	public @ResponseBody List<PurchaseOrderDetailDTO> getByPurchaseOrderHeaderId(
			@PathVariable("purchaseOrderHeaderId") final Integer purchaseOrderHeaderId) {
		
		List<PurchaseOrderDetail> purchaseOrderDetailModel = service.getByPurchaseOrderHeaderId(purchaseOrderHeaderId);
		
		Type listType = new TypeToken<List<PurchaseOrderDetailDTO>>() {}.getType();
		
		List<PurchaseOrderDetailDTO> purchaseOrderDetailDTO = this.purchaseOrderDetailToDTOMapper
										 				  .map(purchaseOrderDetailModel,listType);
		return purchaseOrderDetailDTO;
	}

}