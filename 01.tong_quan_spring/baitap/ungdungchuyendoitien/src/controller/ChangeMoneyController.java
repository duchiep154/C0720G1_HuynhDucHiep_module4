package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.ChangeMoney;

@Controller
public class ChangeMoneyController {

    @Autowired
    private ChangeMoney changeMoney;

    @GetMapping("/money_form")
    public String changeMoney(){
        return "money_form";
    }

    @GetMapping("/changeMoney")
    public String resultChange(@RequestParam String usd, Model model){
        double resultChange = changeMoney.changeMoney(Double.parseDouble(usd));
        model.addAttribute("result",resultChange);
        model.addAttribute("usd",usd);
        return "result";
//        return "money_form";
    }
}