package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.Translate;


@Controller
public class TranslateController {
    @Autowired
    private Translate translateDictionary;

    @GetMapping("/home")
    public String translateEnglish() {
        return "home";
    }

    @GetMapping("/findDic")
    public String translate(@RequestParam String word, Model model) {
        String result = translateDictionary.translate(word);
        model.addAttribute("result", result);
        return "home";
    }
}
