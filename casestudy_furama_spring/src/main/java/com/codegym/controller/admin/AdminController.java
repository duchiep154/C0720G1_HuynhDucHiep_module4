package com.codegym.controller.admin;

import com.codegym.entity.login.AppRole;
import com.codegym.entity.login.AppUser;
import com.codegym.entity.login.UserRole;
import com.codegym.service.login.RoleService;
import com.codegym.service.login.UserRoleService;
import com.codegym.service.login.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/admin"})
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("roleList")
    public Iterable<AppRole> appRole(){
        return roleService.findAll();
    }


    @GetMapping()
    public String home(Model model, RedirectAttributes redirectAttributes, @PageableDefault(size = 3) Pageable pageable,
                       @RequestParam Optional<String> keyword,AppUser appUser) {

        redirectAttributes.addFlashAttribute("message", "");

        String keywordOld = "";
        if (keyword.isPresent()) {
            keywordOld = keyword.get();
//            model.addAttribute("userRoleList", userRoleService.findAllByAppRole(keyword.get(),appUser, pageable));
        } else {
            model.addAttribute("userRoleList", userRoleService.findAll(pageable));
        }
        model.addAttribute("keywordOld", keywordOld);
        return "/admin/home_user";
    }


    @GetMapping("/user/{id}/edit")
    public String editUserRole(@PathVariable long id, Model model) {
        model.addAttribute("userRoleEdit",this. userRoleService.findById(id));
        model.addAttribute("roleList", this.roleService.findAll());
        model.addAttribute("userlist",this.userService.allAppUser());
        return "admin/edit_user";
    }

    @PostMapping("/user/update")
    public String updateUserRole(UserRole userRole, RedirectAttributes redirectAttributes) {
        this.userRoleService.update(userRole);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/admin/user";
    }

    @GetMapping("/user/{id}/delete")
    public String deleteUserRole(@PathVariable int id, Model model) {
        model.addAttribute("customerDelete", userRoleService.findById(id));
        return "admin/delete_user";
    }

    @PostMapping("/user/remove")
    public String remove(UserRole userRole, RedirectAttributes redirectAttributes) {
        userRoleService.remove(userRole.getId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/admin/user";
    }

    @GetMapping("/user/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("userRoleView", userRoleService.findById(id));
        model.addAttribute("roleList",roleService.findAll());
        model.addAttribute("user",userService.allAppUser());
        return "/admin/detail_user";
    }

//
//    **************************role Controler******************

    @GetMapping("/role")
    public String homeRole(Model model, RedirectAttributes redirectAttributes) {
        List<AppRole> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);

        redirectAttributes.addFlashAttribute("message", "");

        return "/admin/home_role";
    }

    @GetMapping("/role/create")
    public String createRole(Model model) {
        model.addAttribute("roleSave", new AppRole());
        return "/admin/create_role";
    }

    @PostMapping("/role/save")
    public String saveRole(AppRole appRole, RedirectAttributes redirect) {
        roleService.save(appRole);
        redirect.addFlashAttribute("message", "Create Successfully");
        return "redirect:/admin/role";
    }

    @GetMapping("/role/{id}/edit")
    public String editRole(@PathVariable int id, Model model) {
        model.addAttribute("roleEdit", roleService.findById(id));
        return "/admin/edit_role";
    }

    @PostMapping("/role/update")
    public String update(AppRole appRole, RedirectAttributes redirectAttributes) {
        roleService.update(appRole);
        redirectAttributes.addFlashAttribute("message", "Update Successfully");
        return "redirect:/admin/role";
    }

    @GetMapping("/role/{id}/delete")
    public String deleteRole(@PathVariable int id, Model model) {
        model.addAttribute("roleDelete", roleService.findById(id));
        return "/admin/delete_role";
    }

    @PostMapping("/role/remove")
    public String remove(AppRole appRole, RedirectAttributes redirectAttributes) {
        roleService.remove(appRole.getRoleId());
        redirectAttributes.addFlashAttribute("message", "Delete Successfully");
        return "redirect:/admin";
    }

    //    @GetMapping("/{id}/view")
//    public String view(@PathVariable int id, Model model) {
//        model.addAttribute("provincialView", provincialService.findById(id));
//        model.addAttribute("customerView",customerService.findAllByProvince(provincialView));
//        return "/detail_view_pro";
//    }
    @GetMapping("/role/{id}/view")
    public ModelAndView viewRole(@PageableDefault(size = 3) Pageable pageable , @PathVariable("id") long id){
      AppRole roleView = roleService.findById(id);
//        if(roleView == null){
//            return new ModelAndView("/error.404");
//        }

        Iterable<UserRole> userRole = userRoleService.findAllByAppRole(pageable,roleView);

        ModelAndView modelAndView = new ModelAndView("/admin/detail_role");
        modelAndView.addObject("roleView", roleView);
        modelAndView.addObject("userRole", userRole);
        return modelAndView;
    }


}
