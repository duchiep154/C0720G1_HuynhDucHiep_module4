package com.codegym.controllers.login;

import com.codegym.entity.login.AppUser;
import com.codegym.service.login.UserDetailsServiceImpl;
import com.codegym.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping({"", "/login"})
    public String goLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("appRoleList", this.userDetailsService.allAppRole());
        model.addAttribute("newUser", new AppUser());
        return "register";
    }

    @PostMapping("/registerNew")
    public String registerNew(@ModelAttribute AppUser appUser, @RequestParam Long appRoleId,
                              RedirectAttributes redirectAttributes) {
        String message = this.userDetailsService.saveUser(appUser, appRoleId);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/login/";
    }

    @GetMapping("/no-accessing")
    public String goNoAccessing(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Sorry ! You do not have access !");
        return "redirect:/customer";
    }

    @GetMapping("/wrong-password")
    public String wrongPassword(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("messageWrong", "Sorry ! " +
                "The password you entered is incorrect !");
        return "redirect:/login/";
    }
}