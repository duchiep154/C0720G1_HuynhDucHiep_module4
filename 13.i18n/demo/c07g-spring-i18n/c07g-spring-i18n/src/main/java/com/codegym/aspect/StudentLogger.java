package com.codegym.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class StudentLogger {

    @AfterThrowing(pointcut = "execution(public * com.codegym.service.StudentService.*(..))",
            throwing = "e")
    public void checkError(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String name = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());


        System.err.println("Bi phat -5k");
        System.err.printf("%s %s %s %s", className, name, methodName, args);
    }
}
