package w.fujiko.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.Warehouse;

@Service
public interface WarehouseService extends UniversalCrud<Warehouse, Integer> {

	public List<Warehouse> getWarehousesByNameOrCode(String key, Integer index);
	public Optional<Warehouse> getUndeletedWarehouseByCode(Long code);
	
}