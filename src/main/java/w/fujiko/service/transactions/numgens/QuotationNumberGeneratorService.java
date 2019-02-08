package w.fujiko.service.transactions.numgens;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.numbergens.QuotationNumberGenerator;

@Service
public interface QuotationNumberGeneratorService extends UniversalCrud<QuotationNumberGenerator, Integer> {}
