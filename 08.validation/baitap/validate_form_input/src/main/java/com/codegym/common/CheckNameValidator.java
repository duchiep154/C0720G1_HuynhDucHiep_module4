package com.codegym.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNameValidator implements ConstraintValidator<CheckName, String> {
    private static final String CHECKSIZE = "^[A-Za-z]{2,45}$";


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) return false;


        return value.matches(CHECKSIZE);
    }


}