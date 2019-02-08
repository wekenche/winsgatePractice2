package w.fujiko.service.transactions.numgens;

import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.util.common.generator.slips.SlipNumberGenerator;

@Service
public interface SlipNumberGeneratorService {

    String generateSlipNumber(SlipNumberGenerator slipNumberGenerator) throws SlipNumberGenerationException;
    String generateSlipNumber(SlipNumberGenerator slipNumberGenerator,Integer userId) throws SlipNumberGenerationException;
}
