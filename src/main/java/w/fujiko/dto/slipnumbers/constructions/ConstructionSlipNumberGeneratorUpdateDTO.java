package w.fujiko.dto.slipnumbers.constructions;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.format.annotation.NumberFormat;


public class ConstructionSlipNumberGeneratorUpdateDTO implements Serializable{

    private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("userId")
    @NotNull
    @NumberFormat
    private Integer userId;

    @JsonProperty("slipNumber")
    @NotNull
    @NotBlank
    @NotEmpty
    private String slipNumber;

    @JsonIgnore
    private Date dateUpdated = new Date();

    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId=userId;
    }

    public String getSlipNumber(){
        return slipNumber;
    }
    public void setSlipNumber(String slipNumber){
        this.slipNumber=slipNumber;
    }

    public Date getDateUpdated(){
        return dateUpdated;
    }
}