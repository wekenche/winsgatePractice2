package w.fujiko.dao.products;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.products.ProductClassification;

public interface ProductClassificationDao
	extends UniversalCrud<ProductClassification, Integer> {
	
	public List<ProductClassification> 
		getProductClassificationsByNameOrCode(String key, Integer index);
	
	public Optional<ProductClassification> 
		getUndeletedProductClassificationByCode(Integer code);
	
	public List<ProductClassification> 
		getProductClassificationsByCode(Integer from, Integer to);

}