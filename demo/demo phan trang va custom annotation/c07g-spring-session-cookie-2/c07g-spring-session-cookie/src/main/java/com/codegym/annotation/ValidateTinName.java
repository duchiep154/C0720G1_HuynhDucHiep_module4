package com.codegym.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidateTinName implements ConstraintValidator<TinName, String> {
   public void initialize(TinName constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return obj.matches("[A-Za-z]+");
   }
}
