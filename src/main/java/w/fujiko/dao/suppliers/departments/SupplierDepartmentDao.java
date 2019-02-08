package w.fujiko.dao.suppliers.departments;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.suppliers.SupplierDepartment;

public interface SupplierDepartmentDao extends UniversalCrud<SupplierDepartment, Integer> {
	public List<SupplierDepartment> getByBaseId(Integer baseId);
	public Optional<SupplierDepartment> getSupplierDepartmentByCode(Integer baseId, Integer code);
}