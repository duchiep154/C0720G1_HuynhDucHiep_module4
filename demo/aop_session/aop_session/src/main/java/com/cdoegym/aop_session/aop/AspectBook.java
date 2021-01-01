package com.cdoegym.aop_session.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AspectBook {

    @AfterReturning(pointcut = "execution(public * com.cdoegym.aop_session.controller.CardStudentBookController.*(..))")
    public void writeLog() {
        System.out.println("Hello");
    }
}
