package w.fujiko.dao;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.Maker;

public interface MakerDao extends UniversalCrud<Maker, Integer> {

	public Optional<Maker> getUndeletedItemByCode(Integer code);
	Optional<Maker> getByName(String name,Optional<Boolean> isEnd);
}