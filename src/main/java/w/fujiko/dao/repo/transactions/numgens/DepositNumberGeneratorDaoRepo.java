package w.fujiko.dao.repo.transactions.numgens;

/**
 * @author yagami
 */
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.numgens.DepositNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.DepositNumberGenerator;

@Repository
@Transactional
public class DepositNumberGeneratorDaoRepo extends UniversalCrudRepo<DepositNumberGenerator, Integer> implements DepositNumberGeneratorDao {}
