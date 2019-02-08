package w.fujiko.dao;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.RatioClassification;

public interface RatioClassificationDao extends UniversalCrud<RatioClassification, Integer> {

	public Optional<RatioClassification> getUndeletedItemByCode(Integer code, Integer makerId);

}