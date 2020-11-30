package com.codegym.controller;

import com.codegym.entity.Category;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"", "/category"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String home(Model model, RedirectAttributes redirectAttributes, @PageableDefault(size = 2) Pageable pageable) {
        Page<Category> categoryList = this.categoryService.findAll(pageable);
        model.addAttribute("categoryList", categoryList);
        redirectAttributes.addFlashAttribute("message", "");
        return "category/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("categorySave", new Category());
        return "/category/create";
    }

    @PostMapping("/save")
    public String save(Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message", "Create Successfully");
        return "redirect:/category";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("categoryEdit", categoryService.findById(id));
        return "category/edit";
    }

    @PostMapping("/update")
    public String update(Category category, RedirectAttributes redirectAttributes) {
        categoryService.update(category);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/category";
    }

    @GetMapping("/{id}/delete")
    public String remove(@PathVariable int id, Model model) {
        model.addAttribute("categoryDelete", categoryService.findById(id));
        return "category/delete";
    }

    @PostMapping("/delete")
    public String delete(Category category, RedirectAttributes redirectAttributes) {
        categoryService.delete(category.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/category";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("categoryView", categoryService.findById(id));
        return "category/detail_view";
    }
}