package w.fujiko.util.common.generator.slips;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.ConstructionNumberGenerator;
import w.fujiko.service.transactions.numgens.ConstructionNumberGeneratorService;
import w.fujiko.service.users.UserService;

@Service
public class ConstructionSlipNumGenerator implements SlipNumberGenerator{

    private @Autowired ConstructionNumberGeneratorService constructionNumberGeneratorService;
    private @Autowired UserService userService;
    
    private Integer userId;

    public ConstructionSlipNumGenerator(){}

    public ConstructionSlipNumGenerator(Integer userId){
        this.userId = userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    @Override
    public String generate() throws SlipNumberGenerationException {
        return generate(this.userId);
    }

    @Override
    public String generate(Integer userId) throws SlipNumberGenerationException {
        Optional<User> userGetResult = userService.get(userId);
        if(!userGetResult.isPresent())
            throw new SlipNumberGenerationException("User id: '"+userId+"' doesn't exist.");
  
        User user = userGetResult.get();

        ConstructionNumberGenerator constructionNumberGeneratorRecord = this.getConstructionNumberRecordByUserid(userId);

        return formattedString(user.getCode(),constructionNumberGeneratorRecord.getSlipNumber());
    }
    
    //gets the record by userId. return record if exist otherwise create new record and return the newly created record. 
    private ConstructionNumberGenerator getConstructionNumberRecordByUserid(Integer userId) {

        return constructionNumberGeneratorService.getByUserId(userId)
                                                 .orElseGet(()->{
                                                    ConstructionNumberGenerator numberGenerator = new ConstructionNumberGenerator();
                                                    numberGenerator.setUser(this.createUser(userId));
                                                    numberGenerator.setSlipNumber(1);
                                                    numberGenerator.setDateCreated(new Date());
                                                    constructionNumberGeneratorService.saveOrUpdate(numberGenerator); // persist
                                                    return numberGenerator;
                                                 });
    }

    private User createUser(Integer userId){
        User user = new User();
        user.setId(userId);
        return user;
    }

    //sample output: 515000031. userCode:515, slipNumber:30
    private String formattedString(short userCode,int slipNumber){
        return String.valueOf(userCode)
                     .concat(String.format("%06d", slipNumber+1));
                     
    }

}