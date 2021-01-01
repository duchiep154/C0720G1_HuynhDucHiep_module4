package com.codegym.controlles;

import com.codegym.entity.employee.AppUser;
import com.codegym.service.UserDetailsServiceImpl;
import com.codegym.service.employee.impl.EmployeeServiceImpl;
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

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping({"", "/login"})
    public String goLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("appRoleList", this.userDetailsService.allAppRole());
        model.addAttribute("employeeNameList", this.employeeService.allNameEmployee());
        model.addAttribute("newUser", new AppUser());
        return "register";
    }

    @PostMapping("/registerNew")
    public String registerNew(@ModelAttribute AppUser appUser, @RequestParam Long appRoleId,
                              @RequestParam String verification, RedirectAttributes redirectAttributes) {
        if (this.employeeService.testRole(appUser, appRoleId)) {
            redirectAttributes.addFlashAttribute("messageWrong", "You are not brave " +
                    "enough to do ADMIN !");
            return "redirect:/login/register";
        } else {
            String message = this.employeeService.saveUser(appUser, appRoleId, verification);
            redirectAttributes.addFlashAttribute("message", message);
            if (message.contains("wrong")) {
                return "redirect:/login/register";
            }
            return "redirect:/login/";
        }
    }

    @GetMapping("/no-accessing")
    public String goNoAccessing(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Sorry ! You do not have access !");
        return "redirect:/home";
    }

    @GetMapping("/wrong-password")
    public String wrongPassword(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("messageWrong", "Sorry ! " +
                "The password you entered is incorrect !");
        return "redirect:/login/";
    }
}