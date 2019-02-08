/**
 * 
 */
package w.fujiko.service.repo.generalpurposes.items;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.generalpurposes.items.GeneralPurposeItemDao;
import w.fujiko.model.masters.generalpurposes.GeneralPurposeItem;
import w.fujiko.service.generalpurposes.items.GeneralPurposeItemService;

/**
 * @author Yagami
 *
 */
@Service
@Transactional
public class GeneralPurposeItemServiceRepo extends
        UniversalServiceRepo<GeneralPurposeItem, GeneralPurposeItemDao, Integer> implements GeneralPurposeItemService {

    @Override
    public Optional<GeneralPurposeItem> getUndeletedItemByCode(String genericPurposeCode,
            String genericPurposeItemCode) {
        return dao.getUndeletedItemByCode(genericPurposeCode,genericPurposeItemCode);
    }

    @Override
    public List<GeneralPurposeItem> getByGeneralPurposeId(Integer genericPurposeId,Optional<String> code) {
        return dao.getByGeneralPurposeId(genericPurposeId,code);
	}

    @Override
    public List<GeneralPurposeItem> getByGeneralPurposeCode(String generalPurposeCode, Boolean isEnd) {
        return dao.getByGeneralPurposeCode(generalPurposeCode, isEnd);
    }

    @Override
    public Optional<GeneralPurposeItem> getByCodeAndByGeneralPurposeCode(String code, String generalPurposeCode,
            Boolean isEnd) {
        return dao.getByCodeAndByGeneralPurposeCode(code, generalPurposeCode, isEnd);
    }

      
}
