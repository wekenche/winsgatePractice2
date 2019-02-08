package w.fujiko.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.GenericClassificationItem;

@Service
public interface GenericClassificationItemService extends UniversalCrud<GenericClassificationItem, Integer> {
	public Optional<GenericClassificationItem> 
		getUndeletedItemByCode(String genericClassCode, Integer genericClassItemCode);
	public List<GenericClassificationItem> 
		getByGenericClassificationId(String genericClassId);
}
