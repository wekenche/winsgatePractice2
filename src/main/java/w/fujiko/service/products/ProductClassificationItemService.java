package w.fujiko.service.products;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.products.ProductClassificationItem;

@Service
public interface ProductClassificationItemService 
	extends UniversalCrud<ProductClassificationItem, Integer> {
	
	public List<ProductClassificationItem> 
		getProductsByClassificationId(Integer productClassId, Integer index);
	
	public List<ProductClassificationItem> 
		getProductsByClassificationItemNameOrCode(Integer classificationId, String key, Integer index);
	
	public Optional<ProductClassificationItem> 
		getUndeletedProductClassificationItemByCode(Integer classificationId, Integer code);

}