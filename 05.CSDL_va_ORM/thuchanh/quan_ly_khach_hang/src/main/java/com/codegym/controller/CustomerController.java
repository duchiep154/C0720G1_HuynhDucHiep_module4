package com.codegym.controller;

import com.codegym.entity.Customer;
import com.codegym.service.CustomerService;

import com.codegym.service.HibernateCustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

   private CustomerService customerService= new HibernateCustomerServiceImpl();
    @GetMapping("/")
    public String home(Model model){
        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customerList",customerList);
        return "/home";
    }

    @GetMapping("/customer/create")
    public String create(Model model){
        model.addAttribute("customerSave",new Customer());
        return "/create";
    }

    @PostMapping("/customer/save")
    public String save(Customer customer, RedirectAttributes redirect){
        customerService.save(customer);
        redirect.addFlashAttribute("message","Create Successfully");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("customerEdit",customerService.findById(id));
        return "/edit";
    }

    @PostMapping("/customer/update")
    public String update(Customer customer, RedirectAttributes redirectAttributes){
        customerService.update(customer);
        redirectAttributes.addFlashAttribute("message","Update Successfully");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("customerDelete",customerService.findById(id));
        return "/delete";
    }

    @PostMapping("/customer/remove")
    public String remove(Customer customer, RedirectAttributes redirectAttributes){
        customerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("message","Delete Successfully");
        return "redirect:/";
    }

    @GetMapping("/customer/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("customerView", customerService.findById(id));
        return "/detail_view";
    }
}
