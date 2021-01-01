package com.codegym.controllers;

import com.codegym.model.Account;
import com.codegym.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String getHomePage(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("123");
        accountService.save(new Account("admin", password,"ROLE_ADMIN",true));
        accountService.save(new Account("user", password,"ROLE_USER",true));
        return "home";
    }

    @GetMapping("/hello")
    public String getHelloPage(){
        return "hello";
    }
}
