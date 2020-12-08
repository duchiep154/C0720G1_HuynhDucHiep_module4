package com.codegym.controller;

import com.codegym.entity.Customer;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping({"","/customer"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String home(Model model){
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customerList",customerList);
        return "home";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("customerSave",new Customer());
        return "create";
    }

    @PostMapping("/save")
    public String save(Customer customer, RedirectAttributes redirect){
        customerService.save(customer);
        redirect.addFlashAttribute("message","Create Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) throws Exception {
        model.addAttribute("customerEdit",customerService.findById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(Customer customer, RedirectAttributes redirectAttributes){
        customerService.update(customer);
        redirectAttributes.addFlashAttribute("message","Update Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) throws Exception {
        model.addAttribute("customerDelete",customerService.findById(id));
        return "delete";
    }

    @PostMapping("/remove")
    public String remove(Customer customer, RedirectAttributes redirectAttributes){
        customerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("message","Delete Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) throws Exception {
        model.addAttribute("customerView", customerService.findById(id));
        return "detail_view";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException() {
        return "error_customer";
    }
}
