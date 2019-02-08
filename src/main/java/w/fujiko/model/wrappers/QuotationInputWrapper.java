package w.fujiko.model.wrappers;

import java.util.List;

import javax.validation.constraints.NotNull;

import w.fujiko.dto.quotations.details.QuotationDetailCreateDTO;
import w.fujiko.dto.quotations.headers.QuotationHeaderCreateDTO;

public class QuotationInputWrapper {
    
    @NotNull
    private QuotationHeaderCreateDTO header;

    @NotNull
    private List<@NotNull QuotationDetailCreateDTO> details;

    public QuotationInputWrapper(){

    }
    
    public List<QuotationDetailCreateDTO> getDetails(){
        return this.details;
    }

    public void setDetails(List<QuotationDetailCreateDTO> details){
        this.details = details;
    }

    public QuotationHeaderCreateDTO getHeader(){
        return this.header;
    }

    public void setHeader(QuotationHeaderCreateDTO header){
        this.header = header;
    }

}
