package com.codegym.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
@Documented
public @interface CheckEmail {
    String message() default "Wrong Format \n vd: abc@abc.com.vn";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
