package w.fujiko.dao.generalpurposes.items;

import java.util.List;
import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem;

public interface GeneralPurposeItemDao extends UniversalCrud<GeneralPurposeItem, Integer> {

    public Optional<GeneralPurposeItem> getUndeletedItemByCode(String genericPurposeCode, String genericPurposeItemCode);
    
    public List<GeneralPurposeItem>  getByGeneralPurposeId(Integer genericPurposeId,Optional<String> code);

    List<GeneralPurposeItem> getByGeneralPurposeCode(String generalPurposeCode,Boolean isEnd);
    Optional<GeneralPurposeItem> getByCodeAndByGeneralPurposeCode(String code,String generalPurposeCode,Boolean isEnd);
}