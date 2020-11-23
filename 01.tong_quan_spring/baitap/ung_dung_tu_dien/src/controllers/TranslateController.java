package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.TranslateService;


@Controller
public class TranslateController {
    @Autowired
    private TranslateService translateServiceDictionary;

    @GetMapping("/home")
    public String translateEnglish() {
        return "home";
    }

    @GetMapping("/translate")
    public String translate(@RequestParam String word, Model model) {
        String result = translateServiceDictionary.translate(word);
        model.addAttribute("result", result);
        return "home";
    }
}
