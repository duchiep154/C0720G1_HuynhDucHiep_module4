package com.codegym.controller;

import com.codegym.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class LoginController {

//    @ModelAttribute("user")
//    public User createUserSession() {
//        return new User();
//    }

    @GetMapping("/login")
    public String goLogin(Model model,
                          @CookieValue(defaultValue = "") String cookieUserName,
                          @CookieValue(value = "cookiePassword", defaultValue = "") String cookieP) {
        model.addAttribute("user", new User(cookieUserName, cookieP));
        return "login";
    }

    @PostMapping("/login")
    public String checkLogin(@ModelAttribute User user, HttpServletResponse response, HttpServletRequest request) {
        // Huy session
        //        request.getSession().setAttribute("user", null);
        Cookie cookieUsername = new Cookie("cookieUserName", user.getUsername());
        cookieUsername.setMaxAge(30);
        Cookie cookiePassword = new Cookie("cookiePassword", user.getPasswordUser());
        cookiePassword.setMaxAge(30);
        response.addCookie(cookieUsername);
        response.addCookie(cookiePassword);

        // valid user
        return "redirect:/student/list";
    }
}
