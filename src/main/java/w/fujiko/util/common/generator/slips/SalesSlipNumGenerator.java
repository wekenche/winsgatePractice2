package w.fujiko.util.common.generator.slips;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.SalesNumberGenerator;
import w.fujiko.service.transactions.numgens.SalesNumberGeneratorService;
import w.fujiko.service.users.UserService;

@Service
public class SalesSlipNumGenerator implements SlipNumberGenerator{

    private @Autowired SalesNumberGeneratorService salesNumberGeneratorService;
    private @Autowired UserService userService;
    
    private Integer userId;

    public SalesSlipNumGenerator(){}

    public SalesSlipNumGenerator(Integer userId){
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
        if(!this.isUserIdExisting(userId))
            throw new SlipNumberGenerationException("User id: '"+userId+"' doesn't exist.");
        
        User user = new User();
        user.setId(userId);
        SalesNumberGenerator salesNumberGenerator = new SalesNumberGenerator();
        salesNumberGenerator.setDateGenerated(new Date());
        salesNumberGenerator.setUser(user);
        salesNumberGeneratorService.saveOrUpdate(salesNumberGenerator);

        return String.format("%06d", salesNumberGenerator.getSlipNo());
    }
    
    private boolean isUserIdExisting(Integer userId){
        return userService.get(userId).isPresent();
    }

}