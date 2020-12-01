package com.codegym.controller;

import com.codegym.entity.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping({"", "/customer"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProvinceService provinceService;

    @GetMapping
    public String home(Model model, @PageableDefault(size = 3) Pageable pageable,
                       @RequestParam Optional<String> keyword) {
//        List<Customer> customerList = customerService.findAll();
//        model.addAttribute("customerList", customerList);

        //redirectAttributes.addFlashAttribute("message", "");

        String keywordOld = "";
        if (keyword.isPresent()) {
            keywordOld = keyword.get();
            model.addAttribute("customerList", customerService.findByNameContaining(pageable, keywordOld));
        } else {
            model.addAttribute("customerList", customerService.findAll(pageable));
        }

        model.addAttribute("keywordOld", keywordOld);

        return "home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customerSave", new Customer());
        model.addAttribute("provinceList", this.provinceService.findAll());

        return "create";
    }

    @PostMapping("/save")
    public String save(Customer customer, RedirectAttributes redirect) {
        customerService.save(customer);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customerEdit", customerService.findById(id));
        model.addAttribute("provinceList", this.provinceService.findAll());
        return "edit";
    }

    @PostMapping("/update")
    public String update(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.update(customer);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/customer";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customerDelete", customerService.findById(id));
        return "delete";
    }

    @PostMapping("/remove")
    public String remove(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/customer";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customerView", customerService.findById(id));
        model.addAttribute("provinceList",provinceService.findAll());
        return "detail_view";
    }

//    @GetMapping("/customer/{name}/remove")
//    public String remove(@PathVariable String name, RedirectAttributes redirectAttributes) {
//        customerService.remove(name);
//        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
//        return "redirect:/customer";
//    }
}
