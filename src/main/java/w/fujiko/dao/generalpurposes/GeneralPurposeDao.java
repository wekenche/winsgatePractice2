package w.fujiko.dao.generalpurposes;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.generalpurposes.GeneralPurpose;

public interface GeneralPurposeDao extends UniversalCrud<GeneralPurpose, Integer> {

	public Optional<GeneralPurpose> getUndeletedItemByCode(String code);

}