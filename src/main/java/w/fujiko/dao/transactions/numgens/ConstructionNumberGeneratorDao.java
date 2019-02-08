package w.fujiko.dao.transactions.numgens;

import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.ConstructionNumberGenerator;

import java.util.Optional;

import fte.api.universal.UniversalCrud;


public interface ConstructionNumberGeneratorDao extends UniversalCrud<ConstructionNumberGenerator, User> {
    public Optional<ConstructionNumberGenerator> getByUserId(Integer userId);
}