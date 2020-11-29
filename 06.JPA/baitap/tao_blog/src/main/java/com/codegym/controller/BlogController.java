package com.codegym.controller;


import com.codegym.model.Blog;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String home(Model model, RedirectAttributes redirectAttributes){
        List<Blog> blogList = this.blogService.findAll();
        model.addAttribute("blogList", blogList);
        redirectAttributes.addFlashAttribute("message","");
        return "home";
    }

    @GetMapping("/blog/create")
    public String create(Model model){
        model.addAttribute("blogSave",new Blog());
        return "create";
    }

    @PostMapping("/blog/save")
    public String save(Blog blog, RedirectAttributes redirectAttributes){
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message","Create Successfully");
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("blogEdit", blogService.findById(id));
        return "edit";
    }

    @PostMapping("/blog/update")
    public String update (Blog blog, RedirectAttributes redirectAttributes){
        blogService.update(blog);
        redirectAttributes.addFlashAttribute("message","Update Successfully");
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/delete")
    public String remove(@PathVariable int id, Model model){
        model.addAttribute("blogDelete", blogService.findById(id));
        return "delete";
    }

    @PostMapping("/blog/delete")
    public String delete(Blog blog, RedirectAttributes redirectAttributes){
        blogService.delete(blog.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/";
    }

    @GetMapping("/blog/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("blogView",blogService.findById(id));
        return "detail_view";
    }
}