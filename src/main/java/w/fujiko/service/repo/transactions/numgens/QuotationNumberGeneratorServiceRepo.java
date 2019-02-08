package w.fujiko.service.repo.transactions.numgens;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalServiceRepo;
import w.fujiko.dao.transactions.numgens.QuotationNumberGeneratorDao;
import w.fujiko.model.transactions.numbergens.QuotationNumberGenerator;
import w.fujiko.service.transactions.numgens.QuotationNumberGeneratorService;

@Service
@Transactional
public class QuotationNumberGeneratorServiceRepo extends UniversalServiceRepo<QuotationNumberGenerator, QuotationNumberGeneratorDao, Integer> implements QuotationNumberGeneratorService {}
