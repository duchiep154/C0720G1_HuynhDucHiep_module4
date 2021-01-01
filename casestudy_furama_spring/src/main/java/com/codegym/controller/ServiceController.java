package com.codegym.controller;

import com.codegym.entity.service.RentType;
import com.codegym.entity.service.Service;
import com.codegym.entity.service.ServiceType;
import com.codegym.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/service"})
public class ServiceController {

    @Autowired
    private ServiceService xService;

    @GetMapping()
    public String home(Model model, RedirectAttributes redirectAttributes, @PageableDefault(size = 3) Pageable pageable,
                       @RequestParam Optional<String> nameSearch) {
//        List<Service> serviceList = xService.findAll();
//        model.addAttribute("serviceList", serviceList);
        redirectAttributes.addFlashAttribute("message", "");

        String keywordOld = "";
        if (nameSearch.isPresent()) {
            keywordOld = nameSearch.get();
            model.addAttribute("serviceList", xService.findByNameContaining(pageable, keywordOld));
        } else {
            model.addAttribute("serviceList", xService.findAll(pageable));
        }
        model.addAttribute("keywordOld", keywordOld);
        return "service/home_service";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<RentType> rentTypeList = this.xService.findAllRentType();
        model.addAttribute("rentTypeList", rentTypeList);

        List<ServiceType> serviceTypeList = this.xService.findAllServiceType();
        model.addAttribute("serviceTypeList", serviceTypeList);

        model.addAttribute("save", new Service());
        return "service/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(value = "save") Service service, BindingResult bindingResult,
                       RedirectAttributes redirect, Model model) {

        // ------------------- Validate -----------------------
        new Service().validate(service, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("serviceList", this.xService.findAll());

            List<RentType> rentTypeList = this.xService.findAllRentType();
            model.addAttribute("rentTypeList", rentTypeList);

            List<ServiceType> serviceTypeList = this.xService.findAllServiceType();
            model.addAttribute("serviceTypeList", serviceTypeList);

            return "service/create";
        }

        xService.save(service);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/service";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        List<RentType> rentTypeList = this.xService.findAllRentType();
        model.addAttribute("rentTypeList", rentTypeList);

        List<ServiceType> serviceTypeList = this.xService.findAllServiceType();
        model.addAttribute("serviceTypeList", serviceTypeList);

        model.addAttribute("edit", xService.findById(id));
        return "service/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(value = "edit") Service service, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
       // ------------------- Validate -----------------------
        new Service().validate(service, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("serviceList", this.xService.findAll());

            List<RentType> rentTypeList = this.xService.findAllRentType();
            model.addAttribute("rentTypeList", rentTypeList);

            List<ServiceType> serviceTypeList = this.xService.findAllServiceType();
            model.addAttribute("serviceTypeList", serviceTypeList);

            return "service/edit";
        }
        xService.update(service);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/service";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id, Model model) {
        model.addAttribute("delete", xService.findById(id));
//        return "service/delete";
        return "service/home_service";
    }

//    @PostMapping("/remove")
//    public String remove(Service service, RedirectAttributes redirectAttributes) {
//        xService.remove(service.getId());
//        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
//        return "redirect:/service";
//    }

    @PostMapping("/remove")
    public String remove(@RequestParam String deleteId, RedirectAttributes redirectAttributes) {
        xService.remove(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/service";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable String id, Model model) {
        model.addAttribute("view", xService.findById(id));
        return "service/detail_view";
    }
}
