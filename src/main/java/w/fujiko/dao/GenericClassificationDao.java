package w.fujiko.dao;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.GenericClassification;

public interface GenericClassificationDao extends UniversalCrud<GenericClassification, Integer> {

	public Optional<GenericClassification> getUndeletedItemByCode(String code);

}