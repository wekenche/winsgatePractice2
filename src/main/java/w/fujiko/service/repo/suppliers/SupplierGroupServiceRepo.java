package w.fujiko.service.repo.suppliers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.suppliers.SupplierGroupDao;
import w.fujiko.model.masters.suppliers.SupplierGroup;
import w.fujiko.service.suppliers.SupplierGroupService;

@Service
@Transactional
public class SupplierGroupServiceRepo 
	extends UniversalServiceRepo<SupplierGroup, SupplierGroupDao, Integer>
	implements SupplierGroupService {

	@Override
	public Optional<SupplierGroup> getUndeletedSupplierGroupByCode(String code) {
		return dao.getUndeletedSupplierGroupByCode(code);
	}

	@Override
	public List<SupplierGroup> getByUserId(Integer userId) {
		return dao.getByUserId(userId);
	}

}