package com.codegym.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class ErrorLogger {

    @AfterThrowing(pointcut = "execution(public * com.codegym.service.*.*(..))",
            throwing = "e")
    public void checkError(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.err.println(String.format("[CMS] co loi xay ra: %s.%s%s: %s", className, method, args, e.getMessage()));
    }
}
