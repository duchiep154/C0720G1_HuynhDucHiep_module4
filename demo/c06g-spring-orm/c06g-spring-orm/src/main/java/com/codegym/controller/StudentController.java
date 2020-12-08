package com.codegym.controller;

import com.codegym.entity.Student;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/student", ""})
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
//    @GetMapping(value = "/")
    public String goListStudent(Model model) {

        model.addAttribute("listStudent", studentService.findAll());

        return "list_student";
    }

    @GetMapping("/detail")
    public String goDetailStudent(@RequestParam Integer id, Model model) {
        model.addAttribute("studentDetail", studentService.findById(id));
        return "detail_student";
    }

    @GetMapping("/detail/{idStudent}")
    public String goDetailStudentPathVariable(@PathVariable(value = "idStudent") Integer id, Model model) {
        model.addAttribute("studentDetail", studentService.findById(id));
        return "detail_student";
    }

    @GetMapping("/create")
    public String goCreateStudent(Model model) {
        model.addAttribute("student", new Student());

        return "create_student";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {

        this.studentService.save(student);
        redirectAttributes.addFlashAttribute("message", "Create student OK!");

        return "redirect:/student";
    }
}
