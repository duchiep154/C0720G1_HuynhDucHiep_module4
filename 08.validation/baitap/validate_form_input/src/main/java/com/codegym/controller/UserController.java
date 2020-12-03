package com.codegym.controller;

import com.codegym.entity.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping({"","/user"})
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public String home(Model model, RedirectAttributes redirectAttributes){
//        List<User> userList = this.userService.findAll();
//        model.addAttribute("userList", userList);
//        redirectAttributes.addFlashAttribute("message","");
//        return "home";
//    }
    @GetMapping("/")
    public String home() {
        return "redirect:/user";
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
    @GetMapping("/user")
    public String index(Model model,HttpServletRequest request
            ,RedirectAttributes redirect) {
        request.getSession().setAttribute("userList", null);

        if(model.asMap().get("success") != null)
            redirect.addFlashAttribute("success",model.asMap().get("success").toString());
        return "redirect:/user/page/1";
    }
    @GetMapping("/user/page/{pageNumber}")
    public String showUserPage(HttpServletRequest request,
                                   @PathVariable int pageNumber, Model model) {
        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("userlist");
        int pagesize = 2;
        List<User> list =(List<User>) userService.findAll();
        System.out.println(list.size());
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("userList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/user/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("user", pages);

        return "home";
    }
}