package w.fujiko.service.transactions.numgens;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.numbergens.SalesNumberGenerator;

@Service
public interface SalesNumberGeneratorService extends UniversalCrud<SalesNumberGenerator, Integer> {}
