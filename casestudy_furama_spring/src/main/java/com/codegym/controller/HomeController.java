package com.codegym.controller;

import com.codegym.entity.login.AppRole;
import com.codegym.entity.login.AppUser;

import com.codegym.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping({"","/home"})
public class HomeController {


    @Autowired
    private UserService userService;



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("registerUser", new AppUser());
        model.addAttribute("appRoleList", this.userService.allAppRole());

        return "login";
    }

    @GetMapping("/403")
    public String go403() {
        return "403";
    }
    @GetMapping
    public String home() {
        return "home";
    }



//    @GetMapping("/login")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("registerUser", new AppUser());
//        model.addAttribute("appRoleList", this.userService.allAppRole());
//
//        return "login";
//    }

    @PostMapping("/registerNew")
    public String registerUserAccount(@Valid @ModelAttribute("registerUser")   AppUser registerUser,
                                      BindingResult bindingResult, AppRole appRole, RedirectAttributes redirectAttributes , Model model) {

        List<AppUser> appUserList = this.userService.allAppUser();
        for (AppUser appUser : appUserList) {
            if (appUser.getUserName().equals(registerUser.getUserName())) {
                bindingResult
                        .rejectValue("userName", "error.userName",
                                "There is already a user registered with the user name provided");
            }
        }
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("registerUser", this.userService.allAppUser());
            return "login";
        }

        else {
            AppUser newAppUser = new AppUser();
            newAppUser.setUserName(registerUser.getUserName());
            newAppUser.setEncrytedPassword(passwordEncoder.encode(registerUser.getEncrytedPassword()));
            newAppUser.setEnabled(registerUser.isEnabled());
            this.userService.saveNewUser(newAppUser);
            this.userService.saveUserRole(newAppUser,appRole);
            redirectAttributes.addFlashAttribute("message","Create Complete");
            return "redirect:/customer";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}