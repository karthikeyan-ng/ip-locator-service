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

@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = IpAddressFormatCheckValidator.class)
public @interface IpAddressFormatCheck {

    String message() default "The following [%s] addresses are not in the exact IP address format.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
