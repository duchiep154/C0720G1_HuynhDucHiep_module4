package com.codegym.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckPhoneValidator implements ConstraintValidator<CheckPhone, String> {
    private static final String CHECKPHONE ="^0\\d{9}$";
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value.matches(CHECKPHONE);
    }
}
