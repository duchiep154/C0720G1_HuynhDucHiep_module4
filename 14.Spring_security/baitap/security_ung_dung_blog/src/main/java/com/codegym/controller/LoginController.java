package com.codegym.controller;

import com.codegym.entity.AppUser;
import com.codegym.service.UserService;
import com.codegym.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@SessionAttributes(value = "userLogin")
@RequestMapping({"", "/login"})
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"", "/login"})
    public String goLogin() {
        return "login";
    }
    @GetMapping("/403")
    public String go403() {
        return "403";
    }


//    @RequestMapping(value = "/403", method = RequestMethod.GET)
//    public String accessDenied(Model model, Principal principal) {
//
//        if (principal != null) {
//            AppUser loginedUser = (AppUser) ((Authentication) principal).getPrincipal();
//
//            String userInfo = WebUtils.toString(loginedUser);
//
//            model.addAttribute("userInfo", userInfo);
//
//            String message = "Hi " + principal.getName() //
//                    + "<br> You do not have permission to access this page!";
//            model.addAttribute("message", message);
//
//        }
//
//        return "403Page";
//    }



//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("newAppUser", new AppUser());
//        model.addAttribute("appRoleList", this.userDetailsServiceImpl.allAppRole());
//        return "register";
//    }

//    @PostMapping("/registerNew")
//    public String registerNew(@ModelAttribute UserRegistrationValidate userRegistrationValidate, @RequestParam Long[] appRole,
//                              RedirectAttributes redirectAttributes) {
//        boolean check = true;
//        List<AppUser> appUserList = this.userDetailsServiceImpl.allAppUser();
//        for (AppUser appUser : appUserList) {
//            if (appUser.getUserName().equals(userRegistrationValidate.getUserName())) {
//                check = false;
//                break;
//            }
//        }
//        if (check) {
//            userRegistrationValidate.setEncrytedPassword(this.bCryptPasswordEncoder.encode(newAppUser.getEncrytedPassword()));
////            newAppUser.setUserId((long) (Math.random()*10000));
//            this.userDetailsServiceImpl.saveNewUser(newAppUser);
//            for (Long element:appRole){
//                this.userDetailsServiceImpl.saveUserRole(newAppUser,element);
//            }
//            redirectAttributes.addFlashAttribute("message","Create Complete");
//            return "redirect:/login";
//        } else {
//            redirectAttributes.addFlashAttribute("message","UserName is existed");
//            return "redirect:/register";
//        }
//    }


}
