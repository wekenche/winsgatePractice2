package w.fujiko.api.generators.slip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import w.fujiko.dto.slipnumbers.SlipNumberDTO;
import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.service.transactions.numgens.WorkingSiteNumberGeneratorService;
import w.fujiko.service.transactions.numgens.SlipNumberGeneratorService;
import w.fujiko.util.common.generator.slips.WorkingSiteSlipNumGenerator;

@RestController
@RequestMapping("/api/generator/slip/number/workingsite")
public class WorkingSiteSlipNumberGeneratorApi {

    @Autowired SlipNumberGeneratorService slipNumberGeneratorService;
    @Autowired WorkingSiteSlipNumGenerator workingSiteSlipNumGenerator;
    @Autowired WorkingSiteNumberGeneratorService workingSiteNumberGeneratorService;
    
	public WorkingSiteSlipNumberGeneratorApi() {}

    @GetMapping("/user/{userId}")
	public @ResponseBody SlipNumberDTO get(@PathVariable(value="userId") final Integer userId) throws SlipNumberGenerationException {
        SlipNumberDTO slipNumber = new SlipNumberDTO();        
        String generatedSlipNumber = slipNumberGeneratorService.generateSlipNumber(workingSiteSlipNumGenerator,userId);
        slipNumber.setSlipNumber(generatedSlipNumber);
        return slipNumber;
	}
}
