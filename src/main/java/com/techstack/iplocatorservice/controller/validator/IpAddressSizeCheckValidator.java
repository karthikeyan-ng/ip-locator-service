package com.techstack.iplocatorservice.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IpAddressSizeCheckValidator implements ConstraintValidator<IpAddressSizeCheck, List<String>> {

    private int min;
    private int max;

    public void initialize(IpAddressSizeCheck constraint) {
        this.min = constraint.min();
        this.max = constraint.max();
    }

    public boolean isValid(List<String> inputIpAddresses, ConstraintValidatorContext context) {
        return inputIpAddresses.size() >= this.min && inputIpAddresses.size() <= this.max;
    }
}
