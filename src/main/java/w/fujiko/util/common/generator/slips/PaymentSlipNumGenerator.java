package w.fujiko.util.common.generator.slips;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.PaymentNumberGenerator;
import w.fujiko.service.transactions.numgens.PaymentNumberGeneratorService;
import w.fujiko.service.users.UserService;

public class PaymentSlipNumGenerator implements SlipNumberGenerator{

    private @Autowired PaymentNumberGeneratorService paymentNumberGeneratorService;
    private @Autowired UserService userService;
    
    private Integer userId;

    public PaymentSlipNumGenerator(){}

    public PaymentSlipNumGenerator(Integer userId){
        this.userId = userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    @Override
    public String generate() throws SlipNumberGenerationException {
       return generate(this.userId);
    }
    
    private boolean isUserIdExisting(Integer userId){
        return userService.get(userId).isPresent();
    }

    @Override
    public String generate(Integer userId) throws SlipNumberGenerationException {
        if(!this.isUserIdExisting(userId))
        throw new SlipNumberGenerationException("User id: '"+userId+"' doesn't exist.");
    
        User user = new User();
        user.setId(userId);
        PaymentNumberGenerator paymentNumberGenerator = new PaymentNumberGenerator();
        paymentNumberGenerator.setDateGenerated(new Date());
        paymentNumberGenerator.setUser(user);
        paymentNumberGeneratorService.saveOrUpdate(paymentNumberGenerator);

        return String.format("%06d", paymentNumberGenerator.getSlipNo());
    }

}