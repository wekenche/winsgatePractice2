package w.fujiko.service.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.UnitDao;
import w.fujiko.model.masters.Unit;
import w.fujiko.service.UnitService;

@Service
@Transactional
public class UnitServiceRepo extends UniversalServiceRepo<Unit, UnitDao, Integer> implements UnitService {

	@Override
	public List<Unit> getUnitsByNameOrCode(String key, Integer index) {
		return dao.getUnitsByNameOrCode(key, index);
	}
	
	@Override
	public Optional<Unit> getUndeletedItemByCode(Integer code) {
		return dao.getUndeletedItemByCode(code);
	}
	
}