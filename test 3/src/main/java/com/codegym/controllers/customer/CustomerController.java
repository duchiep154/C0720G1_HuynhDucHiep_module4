package com.codegym.controllers.customer;

import com.codegym.entity.customer.Customer;
import com.codegym.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"", "/customer"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String home(Model model, @PageableDefault(size = 5) Pageable pageable,
                       @RequestParam Optional<String> keyword,
                       @RequestParam Optional<String> keywordTwo,
                       @RequestParam Optional<String> keywordAll,
                       @RequestParam Optional<String> sortBy) {
        loadList(model, pageable, keyword, keywordTwo, keywordAll, sortBy);
        model.addAttribute("customer", new Customer());
        return "customer-home";
    }

    @PostMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam String deleteId, RedirectAttributes redirectAttributes) {
        this.customerService.deleteCustomer(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Customer Complete !");
        return "redirect:/customer";
    }

    @PostMapping("/createNew")
    public String createNewCustomer(RedirectAttributes redirectAttributes,
                                    @Validated @ModelAttribute Customer customer, BindingResult bindingResult,
                                    @PageableDefault(size = 5) Pageable pageable, Model model,
                                    @RequestParam Optional<String> keyword, @RequestParam Optional<String> keywordTwo,
                                    @RequestParam Optional<String> keywordAll,
                                    @RequestParam Optional<String> sortBy) {
        boolean check = false;
        List<Customer> customerList = this.customerService.findAll();
        for (Customer element : customerList) {
            if (element.getId().equals(customer.getId())) {
                check = true;
                break;
            }
        }

        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasFieldErrors() || check) {
            loadList(model, pageable, keyword, keywordTwo, keywordAll, sortBy);
            model.addAttribute("wrongCreate", "errorCreate");
            if (!bindingResult.hasFieldErrors()) {
                model.addAttribute("idExist", "idExist");
                model.addAttribute("messageIdExist", "ID is exist ! Please input ID other !");
            }
            return "customer-home";
        }

        this.customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Create Complete !");
        return "redirect:/customer";
    }

    @PostMapping("/update")
    public String updateCustomer(RedirectAttributes redirectAttributes,
                                 @Validated @ModelAttribute Customer customer, BindingResult bindingResult,
                                 @PageableDefault(size = 5) Pageable pageable, Model model,
                                 @RequestParam Optional<String> keyword, @RequestParam Optional<String> keywordTwo,
                                 @RequestParam Optional<String> keywordAll,
                                 @RequestParam Optional<String> sortBy) {
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasErrors()) {
            loadList(model, pageable, keyword, keywordTwo, keywordAll, sortBy);
            model.addAttribute("wrongEdit", "errorEdit");
            return "customer-home";
        }
        this.customerService.update(customer);
        redirectAttributes.addFlashAttribute("message", "Update Complete !");
        return "redirect:/customer";
    }

    private void loadList(Model model, @PageableDefault(size = 5) Pageable pageable,
                          @RequestParam Optional<String> keyword, @RequestParam Optional<String> keywordTwo,
                          @RequestParam Optional<String> keywordAll,
                          @RequestParam Optional<String> sortBy) {
        testInformation(model, pageable, keyword, keywordTwo, keywordAll, sortBy);
        model.addAttribute("customerTypeList", this.customerService.allCustomerType());
        List<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        genderList.add("Unknow");
        model.addAttribute("genderList", genderList);
    }

    private void testInformation(Model model, @PageableDefault(size = 5) Pageable pageable,
                                 @RequestParam Optional<String> keyword, @RequestParam Optional<String> keywordTwo,
                                 @RequestParam Optional<String> keywordAll,
                                 @RequestParam Optional<String> sortBy) {
        String keywordOld = "";
        String keywordTwoOld = "";
        String keywordAllOld = "";
        String informationSort = "";
        if (keyword.isPresent() && !keyword.get().equals("")
                && keywordTwo.get().equals("") && keywordAll.get().equals("")) {
            keywordOld = keyword.get();
            model.addAttribute("customerList",
                    this.customerService.findByNameContaining(pageable, keywordOld));
        } else if (keywordTwo.isPresent() && !keywordTwo.get().equals("")
                && keyword.get().equals("") && keywordAll.get().equals("")) {
            keywordTwoOld = keywordTwo.get();
            model.addAttribute("customerList",
                    this.customerService.findByAddressContaining(pageable, keywordTwoOld));
        } else if (keyword.isPresent() && !keyword.get().equals("")
                && !keywordTwo.get().equals("") && keywordAll.get().equals("")) {
            keywordOld = keyword.get();
            keywordTwoOld = keywordTwo.get();
            model.addAttribute("customerList",
                    this.customerService.findByNameAndAddressContaining(pageable, keywordOld, keywordTwoOld));
        } else if (keywordAll.isPresent() && !keywordAll.get().equals("")
                && keywordTwo.get().equals("") && keyword.get().equals("")) {
            keywordAllOld = keywordAll.get();
            model.addAttribute("customerList",
                    this.customerService.findAllOfMe(pageable, keywordAllOld));
        } else if (sortBy.isPresent() && !sortBy.get().equals("")) {
            informationSort = sortBy.get();
            if (informationSort.equals("id")) {
                model.addAttribute("customerList", this.customerService.findAllAndSortByID(pageable));
            }
            if (informationSort.equals("name")) {
                model.addAttribute("customerList",
                        this.customerService.findAllAndSortByName(pageable));
            }
            if (informationSort.equals("birthDay")) {
                model.addAttribute("customerList",
                        this.customerService.findAllAndSortByBirthDay(pageable));
            }
        } else {
            model.addAttribute("customerList", this.customerService.findAll(pageable));
        }
        model.addAttribute("keywordOld", keywordOld);
        model.addAttribute("keywordTwoOld", keywordTwoOld);
        model.addAttribute("keywordAllOld", keywordAllOld);
        model.addAttribute("informationSort", informationSort);
    }

    // Not Modal

    @GetMapping("/showCreateNewForPage")
    public String showCreateNewForPage(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customerTypeList", this.customerService.allCustomerType());
        List<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        genderList.add("Unknow");
        model.addAttribute("genderList", genderList);
        return "create-new-customer";
    }

    @GetMapping("/{id}/showEdit")
    public String showEdit(Model model, @PathVariable String id) {
        model.addAttribute("customer", this.customerService.findById(id));
        model.addAttribute("customerTypeList", this.customerService.allCustomerType());
        List<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        genderList.add("Unknow");
        model.addAttribute("genderList", genderList);
        return "edit-customer";
    }

    @GetMapping("/{id}/showView")
    public String showView(Model model, @PathVariable String id) {
        model.addAttribute("customer", this.customerService.findById(id));
        return "view-customer";
    }

    @PostMapping("/createNewForPage")
    public String createNewCustomer(RedirectAttributes redirectAttributes,
                                    @Validated @ModelAttribute Customer customer, BindingResult bindingResult,
                                    Model model) {
        boolean check = false;
        List<Customer> customerList = this.customerService.findAll();
        for (Customer element : customerList) {
            if (element.getId().equals(customer.getId())) {
                check = true;
                break;
            }
        }

        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasFieldErrors() || check) {
            if (!bindingResult.hasFieldErrors()) {
                model.addAttribute("idExist", "idExist");
                model.addAttribute("messageIdExist", "ID is exist ! Please input ID other !");
            }
            model.addAttribute("customerTypeList", this.customerService.allCustomerType());
            List<String> genderList = new ArrayList<>();
            genderList.add("Male");
            genderList.add("Female");
            genderList.add("Unknow");
            model.addAttribute("genderList", genderList);
            return "create-new-customer";
        }

        this.customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Create Complete !");
        return "redirect:/customer";
    }

    @PostMapping("/updateForPage")
    public String updateForPage(RedirectAttributes redirectAttributes,
                                    @Validated @ModelAttribute Customer customer, BindingResult bindingResult,
                                    Model model) {
        new Customer().validate(customer, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("customerTypeList", this.customerService.allCustomerType());
            List<String> genderList = new ArrayList<>();
            genderList.add("Male");
            genderList.add("Female");
            genderList.add("Unknow");
            model.addAttribute("genderList", genderList);
            return "edit-customer";
        }

        this.customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Update Complete !");
        return "redirect:/customer";
    }
}
