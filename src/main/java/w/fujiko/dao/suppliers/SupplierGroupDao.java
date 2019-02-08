package w.fujiko.dao.suppliers;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.suppliers.SupplierGroup;

public interface SupplierGroupDao  extends UniversalCrud<SupplierGroup, Integer> {
	public Optional<SupplierGroup> getUndeletedSupplierGroupByCode(String code);
	public List<SupplierGroup> getByUserId(Integer userId);
}