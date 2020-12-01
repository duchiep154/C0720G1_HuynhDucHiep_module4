package com.codegym.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CheckBirthdayValidator implements ConstraintValidator<CheckBirthday, String> {
    private static final String CHECKBIRTHDAY ="^((0[1-9])|([1-2][0-9])|(3[0-1]))[/]((0[1-9])|(1[0-2]))[/]((19[0-9]{2})|(200[0-2]))$";
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.equals("")){
            return false;
        }else{ LocalDate now = LocalDate.now();
            LocalDate bd = LocalDate.parse(value);
            return now.compareTo(bd) - 18 > 0;}

//        return value.matches(CHECKBIRTHDAY);
    }
}