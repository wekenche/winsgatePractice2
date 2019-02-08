/**
 * 
 */
package w.fujiko.service.generalpurposes;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.generalpurposes.GeneralPurpose;

/**
 * @author Yagami
 *
 */
public interface GeneralPurposeService extends UniversalCrud<GeneralPurpose,Integer> {
    public Optional<GeneralPurpose> getUndeletedItemByCode(String code);
}
