package com.codegym.controlles;

import com.codegym.entity.contract.Contract;
import com.codegym.service.contract.ContractService;
import com.codegym.service.customer.CustomerService;
import com.codegym.service.employee.EmployeeService;
import com.codegym.service.service_furama.ServiceFuramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ServiceFuramaService serviceFuramaService;

    @GetMapping
    public String contractNew(Model model) {
        model.addAttribute("employeeList", this.employeeService.allEmployee());
        model.addAttribute("customerList", this.customerService.findAll());
        model.addAttribute("serviceFuramaList", this.serviceFuramaService.allService());
        model.addAttribute("contract", new Contract());
        return "contract";
    }

    @PostMapping("/createContract")
    public String createContract(RedirectAttributes redirectAttributes, Model model,
                                 @Validated @ModelAttribute Contract contract,
                                 BindingResult bindingResult) {
        new Contract().validate(contract, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("employeeList", this.employeeService.allEmployee());
            model.addAttribute("customerList", this.customerService.findAll());
            model.addAttribute("serviceFuramaList", this.serviceFuramaService.allService());
            return "contract";
        }
        this.contractService.save(contract);
        redirectAttributes.addFlashAttribute("message", "Create New Contract Complete !");
        return "redirect:/home";
    }
}
