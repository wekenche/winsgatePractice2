package w.fujiko.service.repo.suppliers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.suppliers.SupplierBaseDao;
import w.fujiko.model.masters.suppliers.SupplierBase;
import w.fujiko.service.suppliers.SupplierBaseService;

@Service
@Transactional
public class SupplierBaseServiceRepo 
	extends UniversalServiceRepo<SupplierBase, SupplierBaseDao, Integer>
	implements SupplierBaseService {

	@Override
	public Optional<SupplierBase> getUndeletedSupplierBaseByCode(Integer groupId, String code) {
		return dao.getUndeletedSupplierBaseByCode(groupId, code);
	}

	@Override
	public List<SupplierBase> getByUserId(Integer userId) {
		return dao.getByUserId(userId);
	}

	@Override
	public Optional<SupplierBase> getByCode(String code, Optional<String> groupCode, Optional<Boolean> isEnd) {
		return dao.getByCode(code, groupCode, isEnd);
	}

}
