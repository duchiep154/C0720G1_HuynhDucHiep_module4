package com.codegym.controller;

import com.codegym.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorServiceImpl;

    @GetMapping("/")
    public String calculator(){
        return "home";
    }

    @GetMapping("/result")
    public String calculateMath(@RequestParam String calculate, double num1, double num2, Model model){
        model.addAttribute("calculate", calculatorServiceImpl.calculate(num1, num2, calculate));
        return "home";
    }
}
