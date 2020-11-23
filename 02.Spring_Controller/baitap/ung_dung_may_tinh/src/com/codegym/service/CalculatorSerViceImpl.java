package com.codegym.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorSerViceImpl implements CalculatorService {
    @Override
    public String calculate(double num1, double num2, String calculate) {
        double result;
        switch (calculate){
            case "add":
                result =  num1 + num2;
                break;
            case "subs":
                result =  num1 - num2;
                break;
            case "multi":
                result =  num1 * num2;
                break;
            default:
                if (num2 == 0) {
                    return "Cannot be divided by 0 !";
                } else {
                    result =  num1 / num2;
                }
        }
        return String.valueOf(result);
    }
}
