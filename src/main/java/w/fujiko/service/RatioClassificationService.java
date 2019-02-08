package w.fujiko.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.RatioClassification;

@Service
public interface RatioClassificationService extends UniversalCrud<RatioClassification, Integer> {
	public Optional<RatioClassification> getUndeletedItemByCode(Integer code, Integer makerId);
}
