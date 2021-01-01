package com.codegym.controller.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.entity.customer.TypeCustomer;
import com.codegym.entity.login.AppUser;
import com.codegym.repository.customer.TypeCustomerRepository;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.login.UserService;
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
@RequestMapping({"/customer"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserService userService;





    @GetMapping()
    public String home(Model model, RedirectAttributes redirectAttributes, @PageableDefault(size = 10) Pageable pageable,
                       @RequestParam Optional<String> keyword) {

        redirectAttributes.addFlashAttribute("message", "");

        String keywordOld = "";
        if (keyword.isPresent()) {
            keywordOld = keyword.get();
            model.addAttribute("customerList", customerService.findByNameContaining(pageable, keywordOld));
        } else {
            model.addAttribute("customerList", customerService.findAll(pageable));
        }
        model.addAttribute("keywordOld", keywordOld);
        return "customer/home_customer";
    }

    @GetMapping("/create")
    public String create(Model model){
        List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
        List<AppUser> appUserList=this.customerService.findAllAppUser();
        model.addAttribute("typeCustomerList", typeCustomerList);
        model.addAttribute("appUserList",appUserList);

        model.addAttribute("newCus", new Customer());
        return "customer/create";

    }
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(value = "newCus") Customer customer,BindingResult bindingResult,Model model,
                       RedirectAttributes redirect) {
        boolean check = false;
        List<Customer> customerList = this.customerService.findAll();
        for (Customer element : customerList) {
            if (element.getIdCard().equals(customer.getIdCard())) {
                check = true;
                break;
            }
        }

        if (bindingResult.hasFieldErrors() || check) {
            model.addAttribute("customerList", this.customerService.findAll());
            List<AppUser> appUserList=this.customerService.findAllAppUser();
            model.addAttribute("appUserList",appUserList);

            List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
            model.addAttribute("typeCustomerList", typeCustomerList);



            return "customer/create";
        }


        customerService.save(customer);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
        model.addAttribute("typeCustomerList", typeCustomerList);

        List<AppUser> appUserList=this.customerService.findAllAppUser();
        model.addAttribute("appUserList",appUserList);

        model.addAttribute("customerEdit", customerService.findById(id));

        return "customer/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(value = "customerEdit")Customer customer, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("customerList", this.customerService.findAll());
            List<AppUser> appUserList=this.customerService.findAllAppUser();
            model.addAttribute("appUserList",appUserList);

            List<TypeCustomer> typeCustomerList = this.customerService.findAllTypeCustomer();
            model.addAttribute("typeCustomerList", typeCustomerList);

            return "customer/edit";
        }
     

       customerService.update(customer);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/customer";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id, Model model) {
        model.addAttribute("delete", customerService.findById(id));
//        Chuyển trang
//        return "customer/delete";

//        Delete Modal
        return "customer/home_customer";
    }


//    Chuyển trang
//    @PostMapping("/remove")
//    public String remove(Customer customer, RedirectAttributes redirectAttributes) {
//        xService.remove(customer.getId());
//        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
//        return "redirect:/customer";
//    }

    @PostMapping("/remove")
    public String remove(@RequestParam String deleteId, RedirectAttributes redirectAttributes) {
        customerService.remove(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/customer";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable String id, Model model) {
        model.addAttribute("view", customerService.findById(id));
        return "customer/detail_view";
    }



}
