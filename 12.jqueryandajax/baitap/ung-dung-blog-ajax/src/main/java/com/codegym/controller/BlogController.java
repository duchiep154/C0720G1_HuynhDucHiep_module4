package com.codegym.controller;


import com.codegym.entity.Blog;
import com.codegym.service.BlogService;
import com.codegym.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping({"/blog"})
public class BlogController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String home(Model model, RedirectAttributes redirectAttributes,
                       @PageableDefault(size = 2) Pageable pageable, @RequestParam Optional<String> keyword) {
        redirectAttributes.addFlashAttribute("message", "");
        String keywordOld = "";
        if (keyword.isPresent()) {
            keywordOld = keyword.get();
            model.addAttribute("blogList", blogService.findByAuthorContaining(pageable, keywordOld));
        } else {
            model.addAttribute("blogList", blogService.findAll(pageable));
        }
        model.addAttribute("keywordOld", keywordOld);
        return "blog/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("blogSave", new Blog());
        model.addAttribute("categoryList", this.categoryService.findAll());
        return "/blog/create";
    }

    @PostMapping("/save")
    public String save(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Create Successfully");
        return "redirect:/blog";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("blogEdit", blogService.findById(id));
        model.addAttribute("categoryList", this.categoryService.findAll());
        return "blog/edit";
    }

    @PostMapping("/update")
    public String update(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.update(blog);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/blog";
    }

    @GetMapping("/{id}/delete")
    public String remove(@PathVariable int id, Model model) {
        model.addAttribute("blogDelete", blogService.findById(id));
        return "blog/delete";
    }

    @PostMapping("/delete")
    public String delete(Blog blog, RedirectAttributes redirectAttributes) {
        blogService.delete(blog.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/blog";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("blogView", blogService.findById(id));
        return "blog/detail_view";
    }

    @GetMapping("/blog-of-category")
    public String blogOfCategory(Model model, @RequestParam Integer id) {
        model.addAttribute("blogList", blogService.findAllBlogOfCategory(id));
        return "blog/blog-of-category";
    }

    @GetMapping("/sort")
    public String sortBlogByDateCreate(Model model) {
        model.addAttribute("blogList", blogService.findAllBlogAndSort());
        return "blog/sort-blog";
    }
}
