package com.kodilla.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RangeValidator.class)
public @interface Range {
    String message() default "Must be grater or equal 1 and lower or equal 5";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String min() default "1";
    String max() default "5";
}
