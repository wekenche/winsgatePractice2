package w.fujiko.dao;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.GenericClassificationItem;

public interface GenericClassificationItemDao extends UniversalCrud<GenericClassificationItem, Integer> {
	
	public Optional<GenericClassificationItem> 
		getUndeletedItemByCode(String genericClassCode, Integer genericClassItemCode);

	public List<GenericClassificationItem>
	    getByGenericClassificationId(String genericClassId);
}