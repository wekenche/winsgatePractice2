package w.fujiko.dao.suppliers.departments.users;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.suppliers.SupplierDepartmentUser;

public interface SupplierDepartmentUserDao  extends UniversalCrud<SupplierDepartmentUser, Integer> {
	public List<SupplierDepartmentUser> getByDepartmentId(Integer departmentId);
}