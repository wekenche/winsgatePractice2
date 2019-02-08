package w.fujiko.service.repo.transactions.numgens;

import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.service.transactions.numgens.SlipNumberGeneratorService;
import w.fujiko.util.common.generator.slips.SlipNumberGenerator;

@Service
public class SlipNumberGeneratorServiceRepo implements SlipNumberGeneratorService {

    @Override
    public String generateSlipNumber(SlipNumberGenerator slipNumberGenerator) throws SlipNumberGenerationException {
		return slipNumberGenerator.generate();
	}

  @Override
  public String generateSlipNumber(SlipNumberGenerator slipNumberGenerator, Integer userId)
      throws SlipNumberGenerationException {
        return slipNumberGenerator.generate(userId);
  }
}
