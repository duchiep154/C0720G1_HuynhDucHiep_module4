package com.codegym.controller;

import com.codegym.entity.AppUser;
import com.codegym.service.UserService;
import com.codegym.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @ModelAttribute("AppUser")
    public UserRegistrationValidate userRegistrationValidate() {
        return new UserRegistrationValidate();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("AppUser", new UserRegistrationValidate());
        model.addAttribute("appRoleList", this.userService.allAppRole());

        return "registration";
    }

    @PostMapping("/registerNew")
    public String registerUserAccount(@ModelAttribute("AppUser")  @Valid UserRegistrationValidate userRegistrationValidate,
                                      BindingResult result,@RequestParam Long[] appRole, RedirectAttributes redirectAttributes) {
        boolean check = true;
        List<AppUser> appUserList = this.userService.allAppUser();
        for (AppUser appUser : appUserList) {
            if (appUser.getUserName().equals(userRegistrationValidate.getUserName())) {
                check = false;
                break;
            }
        }


        if (check) {
            AppUser newAppUser = new AppUser();
            newAppUser.setFirstName(userRegistrationValidate.getFirstName());
            newAppUser.setLastName(userRegistrationValidate.getLastName());
            newAppUser.setEmail(userRegistrationValidate.getEmail());
            newAppUser.setPassword(passwordEncoder.encode(userRegistrationValidate.getPassword()));
            newAppUser.setUserName(userRegistrationValidate.getUserName());
            newAppUser.setEnabled(userRegistrationValidate.getTerms());


            this.userService.saveNewUser(newAppUser);
            for (Long element:appRole){
                this.userService.saveUserRole(newAppUser,element);
            }
            redirectAttributes.addFlashAttribute("message","Create Complete");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("message","UserName is existed");
            return "redirect:/registration";
        }
    }
}