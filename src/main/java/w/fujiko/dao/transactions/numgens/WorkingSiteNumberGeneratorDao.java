package w.fujiko.dao.transactions.numgens;
import w.fujiko.model.transactions.numbergens.WorkingSiteNumberGenerator;

import fte.api.universal.UniversalCrud;

public interface WorkingSiteNumberGeneratorDao extends UniversalCrud<WorkingSiteNumberGenerator, Integer> {
    void deleteByUserID(Integer userId);
}