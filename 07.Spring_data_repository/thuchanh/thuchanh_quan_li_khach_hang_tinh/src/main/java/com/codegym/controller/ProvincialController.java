package com.codegym.controller;

import com.codegym.model.Provincial;
import com.codegym.service.provincial.ProvincialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/provincial")
public class ProvincialController {
    @Autowired
    private ProvincialService provincialService;

    @GetMapping
    public String home(Model model, RedirectAttributes redirectAttributes) {
        List<Provincial> provincialList = provincialService.findAll();
        model.addAttribute("provincialList", provincialList);

        redirectAttributes.addFlashAttribute("message", "");

        return "provincial/home_pro";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("provincialSave", new Provincial());
        return "provincial/create_pro";
    }

    @PostMapping("/save")
    public String save(Provincial provincial, RedirectAttributes redirect) {
        provincialService.save(provincial);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("provincialEdit", provincialService.findById(id));
        return "provincial/edit_pro";
    }

    @PostMapping("/update")
    public String update(Provincial provincial, RedirectAttributes redirectAttributes) {
        provincialService.update(provincial);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("provincialDelete", provincialService.findById(id));
        return "provincial/delete_pro";
    }

    @PostMapping("/remove")
    public String remove(Provincial provincial, RedirectAttributes redirectAttributes) {
        provincialService.remove(provincial.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("provincialView", provincialService.findById(id));
        return "provincial/detail_view_pro";
    }
}
