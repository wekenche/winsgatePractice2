package w.fujiko.api.generators.slip;




import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fte.api.Response;
import fte.api.Success;
import w.fujiko.dto.slipnumbers.SlipNumberDTO;
import w.fujiko.dto.slipnumbers.constructions.ConstructionSlipNumberGeneratorUpdateDTO;
import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.transactions.numbergens.ConstructionNumberGenerator;
import w.fujiko.service.transactions.numgens.ConstructionNumberGeneratorService;
import w.fujiko.service.transactions.numgens.SlipNumberGeneratorService;
import w.fujiko.util.common.generator.slips.ConstructionSlipNumGenerator;
import w.fujiko.util.common.stringconverter.StringConverter;

@RestController
@RequestMapping("/api/generator/slip/number/construction")
public class ConstructionSlipNumberGeneratorApi {

    @Autowired SlipNumberGeneratorService slipNumberGeneratorService;
    @Autowired ConstructionSlipNumGenerator constructionSlipNumGenerator;
    @Autowired ConstructionNumberGeneratorService constructionNumberGeneratorService;
    
	public ConstructionSlipNumberGeneratorApi() {}

    @GetMapping("/user/{userId}")
	public @ResponseBody SlipNumberDTO get(@PathVariable(value="userId")final Integer userId) throws SlipNumberGenerationException {
        SlipNumberDTO slipNumber = new SlipNumberDTO();        
        String generatedSlipNumber = slipNumberGeneratorService.generateSlipNumber(constructionSlipNumGenerator,userId);
        slipNumber.setSlipNumber(generatedSlipNumber);
        return slipNumber;
	}
	
	@PutMapping("/")
    public ResponseEntity<?> updateConstructionGeneratorSlipNumber(@Valid @RequestBody ConstructionSlipNumberGeneratorUpdateDTO updateDTO, Errors error) {
        if(error.hasErrors()) 
            return ResponseEntity.badRequest().body(error.getAllErrors());
            
		try {

            ConstructionNumberGenerator constructionNumberGenerator = constructionNumberGeneratorService
                                                                      .getByUserId(updateDTO.getUserId())
                                                                      .orElseThrow();

            //update only if slipNumber is greater than the slipNumber of the existing record.
            if(this.getSlipNumber(updateDTO.getSlipNumber()) > constructionNumberGenerator.getSlipNumber()){                                    
                constructionNumberGenerator.setSlipNumber(constructionNumberGenerator.getSlipNumber()+1);
                constructionNumberGenerator.setDateUpdated(updateDTO.getDateUpdated());
                constructionNumberGeneratorService.saveOrUpdate(constructionNumberGenerator);
            }

            return ResponseEntity.ok().body(new Success());
                                                                                                                            
		}catch(NoSuchElementException ex){
            throw new ResponseStatusException(
                      HttpStatus.NOT_FOUND, "このIDは存在しません", ex);
		}catch(ConstraintViolationException cve){
			List<Response> errorResponse = new ArrayList<>();
			cve.getConstraintViolations().stream().forEach(e -> errorResponse.add(new fte.api.Errors().builder(e)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(PersistenceException pe){
			List<Response> errorResponse = new ArrayList<>();
			fte.api.Errors err = new fte.api.Errors();
			err.setDefaultMessage(pe.getLocalizedMessage());			
			errorResponse.add(err);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(StringConverter.convertToReadable(null));
		}		
	}
    
    //get last 6 digit of the slipNumber and remove leading zeros
    private int getSlipNumber(String formattedSlipNumber){
        final byte LAST_SIX_DIGIT=6;
        String slipNumber = formattedSlipNumber;
        slipNumber = slipNumber
                    .substring(slipNumber.length()-LAST_SIX_DIGIT,slipNumber.length())
                    .replaceFirst("^0+(?!$)", ""); // remove leading zeros
        
        return Integer.valueOf(slipNumber);

    }
}
