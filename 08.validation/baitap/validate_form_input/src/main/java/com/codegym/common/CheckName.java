package com.codegym.common;

import com.codegym.entity.User;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckNameValidator.class)
@Documented
public @interface CheckName {

    String message() default " vui long nhap tu 2 den 45 ky tu ban nhe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}