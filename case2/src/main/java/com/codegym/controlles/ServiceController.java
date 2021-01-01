package com.codegym.controlles;

import com.codegym.entity.serviceFurama.ServiceFurama;
import com.codegym.service.service_furama.ServiceFuramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/serviceFurama")
public class ServiceController {
    @Autowired
    private ServiceFuramaService serviceFuramaService;

    @GetMapping
    public String serviceNew(Model model) {
        model.addAttribute("serviceFurama", new ServiceFurama());
        model.addAttribute("serviceTypeList", this.serviceFuramaService.allServiceType());
        model.addAttribute("rentTypeList", this.serviceFuramaService.allRentType());
        return "service-furama";
    }

    @PostMapping("/createService")
    public String createService(RedirectAttributes redirectAttributes, Model model,
                                @Validated @ModelAttribute ServiceFurama serviceFurama,
                                BindingResult bindingResult) {
        boolean check = false;
        List<ServiceFurama> serviceFuramaList = this.serviceFuramaService.allService();
        for (ServiceFurama element : serviceFuramaList) {
            if (element.getId().equals(serviceFurama.getId())) {
                check = true;
                break;
            }
        }

        new ServiceFurama().validate(serviceFurama, bindingResult);
        if (bindingResult.hasFieldErrors() || check) {
            model.addAttribute("serviceTypeList", this.serviceFuramaService.allServiceType());
            model.addAttribute("rentTypeList", this.serviceFuramaService.allRentType());
            if (!bindingResult.hasFieldErrors()) {
                model.addAttribute("messageIdExist", "ID is exist ! Please input ID other !");
            }
            return "service-furama";
        }

        this.serviceFuramaService.save(serviceFurama);
        redirectAttributes.addFlashAttribute("message", "Create New Service Complete !");
        return "redirect:/home";
    }
}
