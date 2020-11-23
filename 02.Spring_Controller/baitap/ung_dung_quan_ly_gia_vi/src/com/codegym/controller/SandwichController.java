package com.codegym.controller;

import com.codegym.service.Sandwich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {

    @Autowired
    private Sandwich sandwichImpl;

    @GetMapping("/")
    public String sandwich(){
        return "home";
    }

    @GetMapping("/save")
    public String condimentSandwich(@RequestParam String condiment , Model model) {
        model.addAttribute("condiments", sandwichImpl.save(condiment));

        return "home";
    }

}
