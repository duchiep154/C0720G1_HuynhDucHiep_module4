package com.codegym.controller;

import com.codegym.entity.Customer;
import com.codegym.entity.Provincial;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.provincial.ProvincialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/provincial")
public class ProvincialController {
    @Autowired
    private ProvincialService provincialService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String home(Model model, RedirectAttributes redirectAttributes) {
        List<Provincial> provincialList = provincialService.findAll();
        model.addAttribute("provincialList", provincialList);

        redirectAttributes.addFlashAttribute("message", "");

        return "/home_pro";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("provincialSave", new Provincial());
        return "/create_pro";
    }

    @PostMapping("/save")
    public String save(Provincial provincial, RedirectAttributes redirect) {
        provincialService.save(provincial);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/provincial";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("provincialEdit", provincialService.findById(id));
        return "/edit_pro";
    }

    @PostMapping("/update")
    public String update(Provincial provincial, RedirectAttributes redirectAttributes) {
        provincialService.update(provincial);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/provincial";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("provincialDelete", provincialService.findById(id));
        return "/delete_pro";
    }

    @PostMapping("/remove")
    public String remove(Provincial provincial, RedirectAttributes redirectAttributes) {
        provincialService.remove(provincial.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/provincial";
    }

//    @GetMapping("/{id}/view")
//    public String view(@PathVariable int id, Model model) {
//        model.addAttribute("provincialView", provincialService.findById(id));
//        model.addAttribute("customerView",customerService.findAllByProvince(provincialView));
//        return "/detail_view_pro";
//    }
    @GetMapping("/{id}/view")
    public ModelAndView viewProvince(@PageableDefault(size = 3) Pageable pageable ,@PathVariable("id") int id){
        Optional<Provincial> provincial = provincialService.findById(id);
        if(provincial == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Customer> customers = customerService.findAllByProvincial(pageable,provincial);

        ModelAndView modelAndView = new ModelAndView("/detail_view_pro");
        modelAndView.addObject("provincial", provincial);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}

