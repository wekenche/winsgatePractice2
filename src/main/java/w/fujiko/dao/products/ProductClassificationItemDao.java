package w.fujiko.dao.products;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.products.ProductClassificationItem;

public interface ProductClassificationItemDao 
	extends UniversalCrud<ProductClassificationItem, Integer> {
	
	public List<ProductClassificationItem> 
		getProductsByClassificationId(Integer productClassId, Integer index);
	
	public List<ProductClassificationItem> 
		getProductsClassificationItemByNameOrCode(
			Integer classificationId, String key, Integer index);
	
	public Optional<ProductClassificationItem> 
		getUndeletedProductClassificationItemByCode(Integer classificationId, Integer code);


}