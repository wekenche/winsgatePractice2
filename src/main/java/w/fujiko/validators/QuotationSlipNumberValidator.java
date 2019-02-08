package w.fujiko.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import w.fujiko.annotations.QuotationSlipNumberConstraint;
import w.fujiko.service.transactions.quotations.QuotationHeaderService;

public class QuotationSlipNumberValidator implements ConstraintValidator<QuotationSlipNumberConstraint, String> {

    @Autowired QuotationHeaderService quotationHeaderService;

	@Override
	public boolean isValid(String slipNumber, ConstraintValidatorContext arg1) {
        if(StringUtils.isBlank(slipNumber))
            return true;

        return quotationHeaderService.getBySlipNumber(slipNumber).isPresent();		
	}
	
}