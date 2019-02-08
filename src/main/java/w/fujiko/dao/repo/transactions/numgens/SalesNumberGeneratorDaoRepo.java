package w.fujiko.dao.repo.transactions.numgens;

/**
 * @author yagami
 */
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.numgens.SalesNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.SalesNumberGenerator;

@Repository
@Transactional
public class SalesNumberGeneratorDaoRepo extends UniversalCrudRepo<SalesNumberGenerator, Integer> implements SalesNumberGeneratorDao {}
