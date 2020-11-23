package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.SumService;
import service.impl.SumServiceImpl;

@Controller
public class SumController {

    //    private SumService sumService = new SumServiceImpl();
    @Qualifier("sumServiceImpl")
    @Autowired
    private SumService sumService;

//    @GetMapping({"", "/homepage"})
//    public String goHomepage() {
//        return "homepage";
//    }

    // /WEB-INF/views/result_final.jsp
    @GetMapping({"/sum2Num"})
    public String sum2Number(@RequestParam String number1,
                             @RequestParam(value = "number2") String b,
                             Model model) {
//        int num1 = Integer.parseInt(number1);
//        int num2 = Integer.parseInt(b);
//        int result = num1 + num2;

        model.addAttribute("resultNumber",
                sumService.sum(Integer.parseInt(number1), Integer.parseInt(b)));

        return "result_final";
    }
}
