package w.fujiko.dao.suppliers;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.suppliers.SupplierBase;

public interface SupplierBaseDao extends UniversalCrud<SupplierBase, Integer> {
	public Optional<SupplierBase> getUndeletedSupplierBaseByCode(Integer groupId, String code);
	public List<SupplierBase> getByUserId(Integer userId);
	Optional<SupplierBase> getByCode(String code, Optional<String> groupCode, Optional<Boolean> isEnd);
}