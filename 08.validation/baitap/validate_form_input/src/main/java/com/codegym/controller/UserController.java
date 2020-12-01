package com.codegym.controller;

import com.codegym.entity.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping({"","/user"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model, RedirectAttributes redirectAttributes){
        List<User> userList = this.userService.findAll();
        model.addAttribute("userList", userList);
        redirectAttributes.addFlashAttribute("message","");
        return "home";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("userSave",new User());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute(value = "userSave") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("userList",this.userService.findAll());
            return "create";
        }

        userService.save(user);
        redirectAttributes.addFlashAttribute("message","Create Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("userEdit", userService.findById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update (@Valid @ModelAttribute(value = "userEdit") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if(bindingResult.hasFieldErrors()){
            model.addAttribute("userList",this.userService.findAll());
            return "edit";
        }

        userService.update(user);
        redirectAttributes.addFlashAttribute("message","Update Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String remove(@PathVariable int id, Model model){
        model.addAttribute("userDelete", userService.findById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(User user, RedirectAttributes redirectAttributes){
        userService.remove(user.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("userView",userService.findById(id));
        return "detail_view";
    }
}