package w.fujiko.dao;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.Warehouse;

public interface WarehouseDao extends UniversalCrud<Warehouse, Integer>{
	public List<Warehouse> getWarehousesByNameOrCode(String key, Integer index);
	public Optional<Warehouse> getUndeletedWarehouseByCode(Long code);
}