package w.fujiko.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.Unit;

@Service
public interface UnitService extends UniversalCrud<Unit, Integer> {
	public List<Unit> getUnitsByNameOrCode(String key, Integer index);
	public Optional<Unit> getUndeletedItemByCode(Integer code);
}