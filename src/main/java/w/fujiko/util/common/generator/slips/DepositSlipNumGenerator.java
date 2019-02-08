package w.fujiko.util.common.generator.slips;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.DepositNumberGenerator;
import w.fujiko.service.transactions.numgens.DepositNumberGeneratorService;
import w.fujiko.service.users.UserService;

@Service
public class DepositSlipNumGenerator implements SlipNumberGenerator{

    private @Autowired DepositNumberGeneratorService depositNumberGeneratorService;
    private @Autowired UserService userService;
    
    private Integer userId;

    public DepositSlipNumGenerator(){}

    public DepositSlipNumGenerator(Integer userId){
        this.userId = userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    @Override
    public String generate() throws SlipNumberGenerationException {
        return this.generate(this.userId);
    }

    @Override
    public String generate(Integer userId) throws SlipNumberGenerationException {
        if(!this.isUserIdExisting(userId))
        throw new SlipNumberGenerationException("User id: '"+userId+"' doesn't exist.");
    
        User user = new User();
        user.setId(userId);
        DepositNumberGenerator depositNumberGenerator = new DepositNumberGenerator();
        depositNumberGenerator.setDateGenerated(new Date());
        depositNumberGenerator.setUser(user);
        depositNumberGeneratorService.saveOrUpdate(depositNumberGenerator);

        return String.format("%06d", depositNumberGenerator.getSlipNo());
    }
    
    private boolean isUserIdExisting(Integer userId){
        return userService.get(userId).isPresent();
    }

}