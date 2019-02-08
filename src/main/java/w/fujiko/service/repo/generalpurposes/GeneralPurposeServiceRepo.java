/**
 * 
 */
package w.fujiko.service.repo.generalpurposes;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.generalpurposes.GeneralPurposeDao;
import w.fujiko.model.masters.generalpurposes.GeneralPurpose;
import w.fujiko.service.generalpurposes.GeneralPurposeService;

/**
 * @author Yagami
 *
 */
@Service
@Transactional
public class GeneralPurposeServiceRepo extends UniversalServiceRepo<GeneralPurpose, GeneralPurposeDao, Integer>
		implements GeneralPurposeService {

        @Override
        public Optional<GeneralPurpose> getUndeletedItemByCode(String code) {
            return dao.getUndeletedItemByCode(code);
        }
}
