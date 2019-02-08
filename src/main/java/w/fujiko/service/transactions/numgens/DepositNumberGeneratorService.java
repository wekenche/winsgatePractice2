package w.fujiko.service.transactions.numgens;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.numbergens.DepositNumberGenerator;

@Service
public interface DepositNumberGeneratorService extends UniversalCrud<DepositNumberGenerator, Integer> {}
