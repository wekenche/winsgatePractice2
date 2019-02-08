package w.fujiko.dao.repo.transactions.numgens;

/**
 * @author yagami
 */
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fte.api.universal.UniversalCrudRepo;

import w.fujiko.dao.transactions.numgens.QuotationNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.QuotationNumberGenerator;

@Repository
@Transactional
public class QuotationNumberGeneratorDaoRepo extends UniversalCrudRepo<QuotationNumberGenerator, Integer> implements QuotationNumberGeneratorDao {}
