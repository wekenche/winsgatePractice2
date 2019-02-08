package w.fujiko.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import w.fujiko.validators.QuotationSlipNumberValidator;

@Target({ElementType.METHOD,ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = QuotationSlipNumberValidator.class)
public @interface QuotationSlipNumberConstraint {

    String message() default "見積伝票番号は存在しません";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int[] value() default {};
}