package w.fujiko.util.common.generator.slips;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.QuotationNumberGenerator;
import w.fujiko.service.transactions.numgens.QuotationNumberGeneratorService;
import w.fujiko.service.users.UserService;

@Service
public class QuotationSlipNumGenerator implements SlipNumberGenerator{

    private @Autowired QuotationNumberGeneratorService quotationNumberGeneratorService;
    private @Autowired UserService userService;
    
    private Integer userId;

    public QuotationSlipNumGenerator(){}

    public QuotationSlipNumGenerator(Integer userId){
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
        QuotationNumberGenerator quotationNumberGenerator = new QuotationNumberGenerator();
        quotationNumberGenerator.setDateGenerated(new Date());
        quotationNumberGenerator.setUser(user);
        quotationNumberGeneratorService.saveOrUpdate(quotationNumberGenerator);

        return String.format("%06d", quotationNumberGenerator.getSlipNo());
    }

    private boolean isUserIdExisting(Integer userId){
        return userService.get(userId).isPresent();
    }

}