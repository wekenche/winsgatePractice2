package w.fujiko.util.common.generator.slips;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.PurchaseNumberGenerator;
import w.fujiko.service.transactions.numgens.PurchaseNumberGeneratorService;
import w.fujiko.service.users.UserService;

public class PurchaseSlipNumGenerator implements SlipNumberGenerator{

    private @Autowired PurchaseNumberGeneratorService purchaseNumberGeneratorService;
    private @Autowired UserService userService;
    
    private Integer userId;

    public PurchaseSlipNumGenerator(){}

    public PurchaseSlipNumGenerator(Integer userId){
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
        PurchaseNumberGenerator purchaseNumberGenerator = new PurchaseNumberGenerator();
        purchaseNumberGenerator.setDateGenerated(new Date());
        purchaseNumberGenerator.setUser(user);
        purchaseNumberGeneratorService.saveOrUpdate(purchaseNumberGenerator);

        return String.format("%06d", purchaseNumberGenerator.getSlipNo());
    }

    private boolean isUserIdExisting(Integer userId){
        return userService.get(userId).isPresent();
    }

}