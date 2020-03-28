package com.techstack.iplocatorservice.controller.validator;

import org.apache.commons.validator.routines.InetAddressValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IpAddressFormatCheckValidator implements ConstraintValidator<IpAddressFormatCheck, List<String>> {

    private String message;

    @Override
    public void initialize(IpAddressFormatCheck constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(List<String> strings, ConstraintValidatorContext constraintValidatorContext) {
        InetAddressValidator validator = InetAddressValidator.getInstance();

        String invalidIpAddresses = strings.stream()
                .filter(Predicate.not(validator::isValidInet4Address))
                .collect(Collectors.joining(","));

        String formattedMessage = String.format(this.message, invalidIpAddresses);
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(formattedMessage).addConstraintViolation();
        return invalidIpAddresses.length() == 0;
    }
}
