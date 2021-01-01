package com.codegym.controlles;

import com.codegym.entity.employee.Employee;
import com.codegym.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String home(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam Optional<String> keyword) {
        loadList(pageable, model, keyword);
        model.addAttribute("employee", new Employee());
        return "employee-home";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam Integer deleteId, RedirectAttributes redirectAttributes) {
        this.employeeService.deleteEmployee(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Employee Complete !");
        return "redirect:/employee";
    }

    @PostMapping("/createNew")
    public String createNewEmployee(RedirectAttributes redirectAttributes,
                                    @Validated @ModelAttribute Employee employee, BindingResult bindingResult,
                                    @PageableDefault(size = 2) Pageable pageable, Model model,
                                    @RequestParam Optional<String> keyword) {
        new Employee().validate(employee, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            loadList(pageable, model, keyword);
            model.addAttribute("wrongCreate", "errorCreate");
            return "employee-home";
        }
        this.employeeService.save(employee);
        redirectAttributes.addFlashAttribute("message", "Create Complete !");
        return "redirect:/employee";
    }

    @PostMapping("/update")
    public String updateEmployee(RedirectAttributes redirectAttributes,
                                 @Validated @ModelAttribute Employee employee, BindingResult bindingResult,
                                 @PageableDefault(size = 2) Pageable pageable, Model model,
                                 @RequestParam Optional<String> keyword) {
        new Employee().validate(employee, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            loadList(pageable, model, keyword);
            model.addAttribute("wrongEdit", "errorEdit");
            return "employee-home";
        }
        this.employeeService.update(employee);
        redirectAttributes.addFlashAttribute("message", "Update Complete !");
        return "redirect:/employee";
    }

    private void loadList(@PageableDefault(size = 2) Pageable pageable, Model model,
                          @RequestParam Optional<String> keyword) {
        String keywordOld = "";
        if (keyword.isPresent()) {
            keywordOld = keyword.get();
            model.addAttribute("employeeList",
                    this.employeeService.findByNameContaining(pageable, keywordOld));
        } else {
            model.addAttribute("employeeList", this.employeeService.findAll(pageable));
        }
        model.addAttribute("keywordOld", keywordOld);
        model.addAttribute("positionList", this.employeeService.allPosition());
        model.addAttribute("divisionList", this.employeeService.allDivision());
        model.addAttribute("educationDegreeList", this.employeeService.allEducationDegree());
        model.addAttribute("userList", this.employeeService.allUser());
    }
}
