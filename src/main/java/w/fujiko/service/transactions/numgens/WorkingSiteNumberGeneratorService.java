package w.fujiko.service.transactions.numgens;


import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.numbergens.WorkingSiteNumberGenerator;

@Service
public interface WorkingSiteNumberGeneratorService extends UniversalCrud<WorkingSiteNumberGenerator, Integer> {
    void deleteByUserID(Integer userId);
}
