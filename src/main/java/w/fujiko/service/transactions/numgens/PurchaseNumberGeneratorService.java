package w.fujiko.service.transactions.numgens;

import org.springframework.stereotype.Service;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.transactions.numbergens.PurchaseNumberGenerator;

@Service
public interface PurchaseNumberGeneratorService extends UniversalCrud<PurchaseNumberGenerator, Integer> {}
