package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EmailController {
    private static Pattern pattern;
    private Matcher matcher;
    private static final String REGEX_EMAIL = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    @GetMapping("/home")
    String getIndex() {
        return "index";
    }

    @PostMapping("/validate")
    String validateEmail(@RequestParam("email") String email, Model model) {
        boolean isvalid = this.validate(email);
        if (!isvalid) {
            model.addAttribute("message", "Email is invalid");
            return "index";
        }

        model.addAttribute("email", email);
        return "success";
    }

    private boolean validate(String regex) {
        pattern = Pattern.compile(REGEX_EMAIL);
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
