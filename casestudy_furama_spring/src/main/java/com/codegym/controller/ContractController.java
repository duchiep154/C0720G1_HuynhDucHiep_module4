package com.codegym.controller;

import com.codegym.entity.contract.Contract;
import com.codegym.entity.customer.Customer;
import com.codegym.entity.employee.Employee;
import com.codegym.entity.service.Service;
import com.codegym.service.contract.ContractService;
import org.hibernate.id.BulkInsertionCapableIdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping({"/contract"})
public class ContractController {
    @Autowired
    private ContractService contractService;

    @GetMapping()
    public String home(Model model, RedirectAttributes redirectAttributes, @PageableDefault(size = 3) Pageable pageable) {
//
        redirectAttributes.addFlashAttribute("message", "");

        model.addAttribute("contractList", contractService.findAll(pageable));

        return "contract/home_contract";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Service> serviceList = this.contractService.findAllService();
        model.addAttribute("serviceList", serviceList);

        List<Customer> customerList = this.contractService.findAllCustomer();
        model.addAttribute("customerList", customerList);

        List<Employee> employeeList = this.contractService.findAllEmployee();
        model.addAttribute("employeeList", employeeList);

        model.addAttribute("save", new Contract());
        return "contract/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(value = "save") Contract contract, BindingResult bindingResult,
                       RedirectAttributes redirect, Model model) {

        // ------------------- Validate -----------------------
        new Contract().validate(contract, bindingResult);

        if(bindingResult.hasFieldErrors()){
            model.addAttribute("contractList", this.contractService.findAll());

            List<Service> serviceList = this.contractService.findAllService();
            model.addAttribute("serviceList", serviceList);

            List<Customer> customerList = this.contractService.findAllCustomer();
            model.addAttribute("customerList", customerList);

            List<Employee> employeeList = this.contractService.findAllEmployee();
            model.addAttribute("employeeList", employeeList);

            return "contract/create";
        }

        contractService.save(contract);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/contract";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        List<Service> serviceList = this.contractService.findAllService();
        model.addAttribute("serviceList", serviceList);

        List<Customer> customerList = this.contractService.findAllCustomer();
        model.addAttribute("customerList", customerList);

        List<Employee> employeeList = this.contractService.findAllEmployee();
        model.addAttribute("employeeList", employeeList);

        model.addAttribute("edit", contractService.findById(id));
        return "contract/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(value = "edit") Contract contract, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {

        // ------------------- Validate -----------------------
        new Contract().validate(contract, bindingResult);

        if(bindingResult.hasFieldErrors()){
            model.addAttribute("contractList", this.contractService.findAll());

            List<Service> serviceList = this.contractService.findAllService();
            model.addAttribute("serviceList", serviceList);

            List<Customer> customerList = this.contractService.findAllCustomer();
            model.addAttribute("customerList", customerList);

            List<Employee> employeeList = this.contractService.findAllEmployee();
            model.addAttribute("employeeList", employeeList);

            return "contract/edit";
        }

        contractService.update(contract);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/contract";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {
        model.addAttribute("delete", contractService.findById(id));
//        return "contract/delete";
        return "contract/home_contract";
    }

//    @PostMapping("/remove")
//    public String remove(Contract contract, RedirectAttributes redirectAttributes) {
//        xService.remove(contract.getId());
//        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
//        return "redirect:/contract";
//    }

    @PostMapping("/remove")
    public String remove(@RequestParam Integer deleteId, RedirectAttributes redirectAttributes) {
        contractService.remove(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/contract";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("view", contractService.findById(id));
        return "contract/detail_view";
    }
}

