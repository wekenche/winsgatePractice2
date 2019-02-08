package w.fujiko.dto.slipnumbers;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SlipNumberDTO implements Serializable{

    private static final long serialVersionUID = 7257921460512063270L;

    @JsonProperty("slipNumber")
    private String slipNumber;


    public String getSlipNumber(){
        return slipNumber;
    }
    public void setSlipNumber(String slipNumber){
        this.slipNumber=slipNumber;
    }
}