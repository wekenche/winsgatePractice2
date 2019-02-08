/**
 * 
 */
package w.fujiko.service;

import java.util.Optional;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.GenericClassification;

/**
 * @author Try-Parser
 *
 */
public interface GenericClassificationService extends UniversalCrud<GenericClassification,Integer> {
    public Optional<GenericClassification> getUndeletedItemByCode(String code);
}
