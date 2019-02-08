package w.fujiko.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.Maker;

@Service
public interface MakerService extends UniversalCrud<Maker, Integer> {
	public Optional<Maker> getUndeletedItemByCode(Integer code);
	Optional<Maker> getByName(String name,Optional<Boolean> isEnd);
}
