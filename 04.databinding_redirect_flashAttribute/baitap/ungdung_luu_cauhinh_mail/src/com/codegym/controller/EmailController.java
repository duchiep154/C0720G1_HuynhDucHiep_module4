package com.codegym.controller;

import com.codegym.model.Email;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("email", new Email());
        return "home";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("email") Email email, Model model){
        Email email1 = email;
        model.addAttribute("language",email.getLanguage());
        model.addAttribute("pageSize",email.getPageSize());
        model.addAttribute("spamsFilter",email.getSpamsFilter());
        model.addAttribute("sign",email.getSign());
        model.addAttribute("email1",email1);
        return "info";
    }
}