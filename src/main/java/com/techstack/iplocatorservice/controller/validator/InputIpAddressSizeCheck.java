package com.techstack.iplocatorservice.controller.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This class evaluates incoming REST RequestParam for {@code ip} and determine that given ip collection size is
 * in valid range (1-5).
 *
 * @author Karthikeyan N
 */
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = InputIpAddressSizeCheckValidator.class)
public @interface InputIpAddressSizeCheck {

    String message() default "Accepted input is minimum 1 and maximum 5 IP addresses at a time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 1;

    int max() default 5;

}
