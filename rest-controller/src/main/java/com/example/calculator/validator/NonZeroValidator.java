package com.example.calculator.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NonZeroValidator implements ConstraintValidator<NonZero, Double> {
    private final Logger LOGGER = LoggerFactory.getLogger(NonZeroValidator.class);

    @Override
    public void initialize(NonZero nonZero) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        return Double.compare(value, 0.0) != 0;
    }

}

