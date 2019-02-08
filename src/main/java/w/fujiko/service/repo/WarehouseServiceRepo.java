package w.fujiko.service.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.WarehouseDao;
import w.fujiko.model.masters.Warehouse;
import w.fujiko.service.WarehouseService;

@Service
@Transactional
public class WarehouseServiceRepo 
	extends UniversalServiceRepo<Warehouse, WarehouseDao, Integer> 
		implements WarehouseService {

	@Override
	public List<Warehouse> getWarehousesByNameOrCode(String key, Integer index) {
		return dao.getWarehousesByNameOrCode(key, index);
	}

	@Override
	public Optional<Warehouse> getUndeletedWarehouseByCode(Long code) {
		return dao.getUndeletedWarehouseByCode(code);
	}

}