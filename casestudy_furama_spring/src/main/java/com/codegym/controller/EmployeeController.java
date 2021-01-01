package com.codegym.controller;

import com.codegym.entity.employee.Division;
import com.codegym.entity.employee.EducationDegree;
import com.codegym.entity.employee.Employee;
import com.codegym.entity.employee.Position;
import com.codegym.entity.login.AppUser;
import com.codegym.service.employee.EmployeeService;
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
@RequestMapping({"/employee"})
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String home(Model model, RedirectAttributes redirectAttributes, @PageableDefault(size = 3) Pageable pageable,
                       @RequestParam Optional<String> keyword) {
//
        redirectAttributes.addFlashAttribute("message", "");

        String keywordOld = "";
        if (keyword.isPresent()) {
            keywordOld = keyword.get();
            model.addAttribute("employeeList", employeeService.findByNameContaining(pageable, keywordOld));
        } else {
            model.addAttribute("employeeList", employeeService.findAll(pageable));
        }
        model.addAttribute("keywordOld", keywordOld);
        return "employee/home_employee";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Division> divisionList = this.employeeService.findAllDivision();
        model.addAttribute("divisionList", divisionList);
        model.addAttribute("employeeSave", new Employee());
        List<EducationDegree> educationDegreeList = this.employeeService.findAllEducationDegree();
        model.addAttribute("educationDegreeList", educationDegreeList);

        List<Position> positionList = this.employeeService.findAllPosition();
        model.addAttribute("positionList", positionList);

        List<AppUser> appUserList = this.userService.allAppUser();
        model.addAttribute("appUserList", appUserList);


        return "employee/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employeeSave") Employee employee, BindingResult bindingResult,
                       RedirectAttributes redirect, Model model) {
// ------------------------- Validate ---------------------------------
        new Employee().validate(employee, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("employeeList", this.employeeService.findAll());

            List<Division> divisionList = this.employeeService.findAllDivision();
            model.addAttribute("divisionList", divisionList);

            List<EducationDegree> educationDegreeList = this.employeeService.findAllEducationDegree();
            model.addAttribute("educationDegreeList", educationDegreeList);

            List<AppUser> appUserList = this.userService.allAppUser();
            model.addAttribute("appUserList", appUserList);

            List<Position> positionList = this.employeeService.findAllPosition();
            model.addAttribute("positionList", positionList);

            return "employee/create";
        }

        employeeService.save(employee);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/employee";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        List<Division> divisionList = this.employeeService.findAllDivision();
        model.addAttribute("divisionList", divisionList);

        List<EducationDegree> educationDegreeList = this.employeeService.findAllEducationDegree();
        model.addAttribute("educationDegreeList", educationDegreeList);

        List<Position> positionList = this.employeeService.findAllPosition();
        model.addAttribute("positionList", positionList);
        List<AppUser> appUserList = this.userService.allAppUser();
        model.addAttribute("appUserList", appUserList);

        model.addAttribute("edit", employeeService.findById(id));
        return "employee/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute(value = "edit") Employee employee, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        // ------------------------- Validate ---------------------------------
        new Employee().validate(employee, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("employeeList", this.employeeService.findAll());

            List<Division> divisionList = this.employeeService.findAllDivision();
            model.addAttribute("divisionList", divisionList);

            List<EducationDegree> educationDegreeList = this.employeeService.findAllEducationDegree();
            model.addAttribute("educationDegreeList", educationDegreeList);

            List<Position> positionList = this.employeeService.findAllPosition();
            model.addAttribute("positionList", positionList);

            return "employee/edit";
        }

        employeeService.update(employee);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/employee";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id, Model model) {
        model.addAttribute("delete", employeeService.findById(id));
//        return "employee/delete";

        return "employee/home_employee";
    }

//    @PostMapping("/remove")
//    public String remove(Employee employee, RedirectAttributes redirectAttributes) {
//        xService.remove(employee.getId());
//        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
//        return "redirect:/employee";
//    }

    @PostMapping("/remove")
    public String remove(@RequestParam String deleteId, RedirectAttributes redirectAttributes) {
        employeeService.remove(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/employee";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable String id, Model model) {
        model.addAttribute("view", employeeService.findById(id));
        return "employee/detail_view";
    }
}
