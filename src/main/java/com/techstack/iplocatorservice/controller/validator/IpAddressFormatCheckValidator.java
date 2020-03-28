package com.techstack.iplocatorservice.controller.validator;

import org.apache.commons.validator.routines.InetAddressValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This class is responsible for checking the given IP address format. If IP address is not in IPv4 standard,
 * it will prepare a {@code ConstraintViolationException}
 *
 * @author Karthikeyan
 */
public class IpAddressFormatCheckValidator implements ConstraintValidator<IpAddressFormatCheck, List<String>> {

    private String message;

    /**
     * Initialize the param before execute the actual validation logic
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IpAddressFormatCheck constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    /**
     * Perform IPv4 format check validation and prepares error messages
     *
     * @param strings
     * @param constraintValidatorContext
     * @return
     */
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
