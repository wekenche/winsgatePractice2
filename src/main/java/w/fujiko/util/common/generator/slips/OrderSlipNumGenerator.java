package w.fujiko.util.common.generator.slips;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import w.fujiko.exceptions.SlipNumberGenerationException;
import w.fujiko.model.masters.users.User;
import w.fujiko.model.transactions.numbergens.OrderNumberGenerator;
import w.fujiko.service.transactions.numgens.OrderNumberGeneratorService;
import w.fujiko.service.users.UserService;

@Service
public class OrderSlipNumGenerator implements SlipNumberGenerator{

    private @Autowired OrderNumberGeneratorService orderNumberGeneratorService;
    private @Autowired UserService userService;
    
    private Integer userId;

    public OrderSlipNumGenerator(){}

    public OrderSlipNumGenerator(Integer userId){
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
        OrderNumberGenerator orderNumberGenerator = new OrderNumberGenerator();
        orderNumberGenerator.setDateGenerated(new Date());
        orderNumberGenerator.setUser(user);
        orderNumberGeneratorService.saveOrUpdate(orderNumberGenerator);

        return String.format("%06d", orderNumberGenerator.getSlipNo());
    }

    private boolean isUserIdExisting(Integer userId){
        return userService.get(userId).isPresent();
    }
}