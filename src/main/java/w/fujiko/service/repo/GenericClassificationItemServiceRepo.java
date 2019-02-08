package w.fujiko.service.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.GenericClassificationItemDao;
import w.fujiko.model.masters.GenericClassificationItem;
import w.fujiko.service.GenericClassificationItemService;

@Service
@Transactional
public class GenericClassificationItemServiceRepo 
	extends UniversalServiceRepo<GenericClassificationItem, GenericClassificationItemDao, Integer> 
	implements GenericClassificationItemService {

	@Override
	public Optional<GenericClassificationItem> 
		getUndeletedItemByCode(String genericClassCode, Integer genericClassItemCode) {
		return dao.getUndeletedItemByCode(genericClassCode, genericClassItemCode);
	}

	@Override
	public List<GenericClassificationItem> getByGenericClassificationId(String genericClassId) {
		return dao.getByGenericClassificationId(genericClassId);
	}

}
