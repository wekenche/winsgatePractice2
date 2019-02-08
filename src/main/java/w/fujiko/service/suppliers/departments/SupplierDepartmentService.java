package w.fujiko.service.suppliers.departments;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.suppliers.SupplierDepartment;

@Service
public interface SupplierDepartmentService extends UniversalCrud<SupplierDepartment,Integer> {
	public List<SupplierDepartment> getByBaseId(Integer baseId);
	public Optional<SupplierDepartment> getSupplierDepartmentByCode(Integer baseId, Integer code);
}