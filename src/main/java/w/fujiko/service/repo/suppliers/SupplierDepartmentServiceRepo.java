package w.fujiko.service.repo.suppliers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.suppliers.departments.SupplierDepartmentDao;
import w.fujiko.model.masters.suppliers.SupplierDepartment;
import w.fujiko.service.suppliers.departments.SupplierDepartmentService;

@Service
@Transactional
public class SupplierDepartmentServiceRepo 
	extends UniversalServiceRepo<SupplierDepartment, SupplierDepartmentDao, Integer> 
	implements SupplierDepartmentService {

	@Override
	public List<SupplierDepartment> getByBaseId(Integer baseId) {
		return dao.getByBaseId(baseId);
	}

	@Override
	public Optional<SupplierDepartment> getSupplierDepartmentByCode(Integer baseId, Integer code) {
		return dao.getSupplierDepartmentByCode(baseId, code);
	}

}