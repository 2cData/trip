package com.toseedata.wetripout.trip.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EscapeCharacterValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EscapeCharacterConstraint {
    String message() default "Invalid characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
