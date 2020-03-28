package com.techstack.iplocatorservice.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class InputIpAddressSizeCheckValidator implements ConstraintValidator<InputIpAddressSizeCheck, List<String>> {

    private int min;
    private int max;

    public void initialize(InputIpAddressSizeCheck constraint) {
        this.min = constraint.min();
        this.max = constraint.max();
    }

    public boolean isValid(List<String> inputIpAddresses, ConstraintValidatorContext context) {
        return this.min >= inputIpAddresses.size() && this.max <= inputIpAddresses.size();
    }
}
