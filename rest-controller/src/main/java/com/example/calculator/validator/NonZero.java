package com.example.calculator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NonZeroValidator.class)
@Target( { ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NonZero {
    String message() default "invalid value of b, Cannot divide by zero";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
