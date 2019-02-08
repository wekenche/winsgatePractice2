package w.fujiko.dao;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.Unit;

public interface UnitDao extends UniversalCrud<Unit, Integer> {

	public List<Unit> getUnitsByNameOrCode(String key, Integer index);
	public Optional<Unit> getUndeletedItemByCode(int code);

}