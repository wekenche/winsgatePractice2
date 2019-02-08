package w.fujiko.service.repo.products;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.products.ProductClassificationItemDao;
import w.fujiko.model.masters.products.ProductClassificationItem;
import w.fujiko.service.products.ProductClassificationItemService;

@Service
@Transactional
public class ProductClassificationItemServiceRepo 
	extends UniversalServiceRepo<ProductClassificationItem, ProductClassificationItemDao, Integer> 
	implements ProductClassificationItemService {

	@Override
	public List<ProductClassificationItem> 
		getProductsByClassificationId(Integer productClassId, Integer index) {
		
		return dao.getProductsByClassificationId(productClassId, index);
	}

	@Override
	public List<ProductClassificationItem> 
		getProductsByClassificationItemNameOrCode(Integer classificationId, String key, Integer index) {
		
		return dao.getProductsClassificationItemByNameOrCode(classificationId, key, index);
	}

	@Override
	public Optional<ProductClassificationItem> 
		getUndeletedProductClassificationItemByCode(Integer classificationId, Integer code) {
		return dao.getUndeletedProductClassificationItemByCode(classificationId, code);
	}

}