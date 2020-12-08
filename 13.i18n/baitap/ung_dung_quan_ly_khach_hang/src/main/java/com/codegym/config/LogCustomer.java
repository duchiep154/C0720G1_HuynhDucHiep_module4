package com.codegym.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class LogCustomer {

    @AfterThrowing(pointcut = "execution(public * com.codegym.service.CustomerService.*(..))", throwing = "e")
    public void logError(JoinPoint joinPoint, Exception e) {
//        String className = joinPoint.getClass().getSimpleName();
        String className = String.valueOf(joinPoint.getTarget().getClass());
        String returnType = joinPoint.getSignature().getDeclaringType().getName();
        String methodName = joinPoint.getSignature().getName();
        String argsName = Arrays.toString(joinPoint.getArgs());
        System.err.printf("Have Errors!!! %s.%s.%s.%s: %s", className,
                returnType,
                methodName,
                argsName,
                e.getMessage());
    }
}
