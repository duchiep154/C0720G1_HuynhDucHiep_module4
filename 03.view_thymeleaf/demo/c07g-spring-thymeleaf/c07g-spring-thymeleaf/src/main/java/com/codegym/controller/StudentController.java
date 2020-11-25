package com.codegym.controller;

import com.codegym.entity.Student;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    // private StudentService studentService = new StudentServiceImpl();

    @GetMapping({"", "/list"})
    public String goStudentList(Model model) {
        model.addAttribute("listStudent", this.studentService.findAll());
        return "list_student";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String goDetailStudent(@RequestParam Integer id, Model model) {
        model.addAttribute("studentDetail", studentService.findById(id));
        return "detail_student";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String goDetailStudentPathVariable(@PathVariable Integer id, Model model) {
        model.addAttribute("studentDetail", studentService.findById(id));
        return "detail_student";
    }

//    @GetMapping({"/", "/list"})
//    public ModelAndView goStudentList() {
//        ModelAndView modelAndView =
//                new ModelAndView("list_student",
//                        "listStudent",
//                        this.studentService.findAll());
////        modelAndView.addObject("instructorList", );
//
//        return modelAndView;
//    }

//    @GetMapping({"/", "/list"})
//    public String goStudentList(ModelMap modelMap) {
////        model.addAttribute("listStudent", this.studentService.findAll());
//        modelMap.put("listStudent", this.studentService.findAll());
//
//        return "list_student";
//    }

    @GetMapping("/create")
    public String goCreateStudent(Model model) {
        model.addAttribute("studentObj", new Student());

        return "create_student";
    }

    @PostMapping("/create")
    public String createStudent(Model model, @ModelAttribute Student studentObj,
                                RedirectAttributes redirectAttributes) {
        this.studentService.save(studentObj);
        redirectAttributes.addFlashAttribute("messageSuccess", "Register successfully!");
//        model.addAttribute("studentObj", studentObj);

//        model.addAttribute("listStudent", studentService.findAll());
        return "redirect:/student/list";
    }
}
