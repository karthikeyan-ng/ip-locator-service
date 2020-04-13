package com.techstack.iplocatorservice.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * This class is responsible for checking the given IP address size. If IP address size is =< min and >= max
 * it will prepare a {@code ConstraintViolationException}
 *
 * @author Karthikeyan
 */
public class IpAddressSizeCheckValidator implements ConstraintValidator<IpAddressSizeCheck, List<String>> {

    private int min;
    private int max;

    /**
     * Initialize the param before execute the actual validation logic
     *
     * @param constraint
     */
    public void initialize(IpAddressSizeCheck constraint) {
        this.min = constraint.min();
        this.max = constraint.max();
    }

    /**
     * Perform size validation logic defined by the presets in {@code @IpAddressSizeCheck}
     *
     * @param inputIpAddresses
     * @param context
     * @return
     */
    public boolean isValid(List<String> inputIpAddresses, ConstraintValidatorContext context) {
        return inputIpAddresses.size() >= this.min && inputIpAddresses.size() <= this.max;
    }
}
