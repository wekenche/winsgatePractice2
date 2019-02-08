package w.fujiko.util.common.generator.slips;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.WorkingSiteNumberGenerator;
import w.fujiko.service.transactions.numgens.WorkingSiteNumberGeneratorService;
import w.fujiko.service.users.UserService;

@Service
public class WorkingSiteSlipNumGenerator implements SlipNumberGenerator{

    private @Autowired WorkingSiteNumberGeneratorService workingSiteNumberGeneratorService;
    private @Autowired UserService userService;
    
    private Integer userId;

    public WorkingSiteSlipNumGenerator(){}

    public WorkingSiteSlipNumGenerator(Integer userId){
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
        
        this.deleteExistingUserId(userId); //delete existing record by userId

        User user = userGetResult.get();
        WorkingSiteNumberGenerator workingSiteNumberGenerator = new WorkingSiteNumberGenerator();
        workingSiteNumberGenerator.setDateGenerated(new Date());
        workingSiteNumberGenerator.setUser(user);
        workingSiteNumberGeneratorService.saveOrUpdate(workingSiteNumberGenerator);

        return formattedString(user.getCode(),workingSiteNumberGenerator.getSlipNo());
    }
    
    //deletes record by userId
    private void deleteExistingUserId(Integer userId){
        workingSiteNumberGeneratorService.deleteByUserID(userId);
    }

    //sample output: 40000007
    private String formattedString(short userCode,int slipNumber){
        return String.valueOf(String.valueOf(userCode)
                     .charAt(0))
                     .concat(String.format("%06d", slipNumber));
                     
    }

}