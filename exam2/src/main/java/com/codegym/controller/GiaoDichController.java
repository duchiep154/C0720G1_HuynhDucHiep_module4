package com.codegym.controller;

import com.codegym.entity.complex.Customer;
import com.codegym.entity.complex.GiaoDich;
import com.codegym.service.complex.CustomerService;
import com.codegym.service.complex.GiaoDichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/giaodich")
public class GiaoDichController {

    @Autowired
    private GiaoDichService giaoDichService;

    @GetMapping
    public String viewHomePage(Model model,@Param("keyword") String keyword) {
        String keywordOld="";
        if (keyword==null){
            keyword="";
        }
        model.addAttribute("keyword",keyword);

        return viewPage(model, 1,keyword);


    }


    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum, @RequestParam(value = "") String keyword
                          ) {

        Page<GiaoDich> page = giaoDichService.listAll(pageNum, Optional.ofNullable(keyword));


        List<GiaoDich> listGiaoDich = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("giaoDichList", listGiaoDich);
        model.addAttribute("keyword", keyword);
        return "list";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam String deleteId, RedirectAttributes redirectAttributes) {
        this.giaoDichService.deleteGiaoDich(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Product Complete !");
        return "redirect:/giaodich";
    }

    @GetMapping("/showCreateNewForPage")
    public String showCreateNewForPage(Model model) {
        model.addAttribute("giaoDichNew", new GiaoDich());
        model.addAttribute("customerList", this.giaoDichService.allCustomer());
        model.addAttribute("loaiDVList",this.giaoDichService.allLoaiDV());
        return "GiaoDich/new";
    }


    @PostMapping("/create")
    public String createNewCustomer(RedirectAttributes redirectAttributes,
                                    @Validated @ModelAttribute("giaoDichNew") GiaoDich giaoDich, BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("giaoDichNew",giaoDich);

            model.addAttribute("customerList", this.giaoDichService.allCustomer());
            model.addAttribute("loaiDVList",this.giaoDichService.allLoaiDV());
            return "GiaoDich/new";
        }else{
            this.giaoDichService.save(giaoDich);
            redirectAttributes.addFlashAttribute("message", "Create Complete !");
            return "redirect:/giaodich";
        }
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable String id, Model model) {
        model.addAttribute("view", giaoDichService.findById(id));
        return "GiaoDich/showById";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("edit", giaoDichService.findById(id));
        model.addAttribute("customerList", this.giaoDichService.allCustomer());
        model.addAttribute("loaiDVList",this.giaoDichService.allLoaiDV());
        return "edit";
    }
    @PostMapping("/update")
    public String edit(RedirectAttributes redirectAttributes,
                         @Validated @ModelAttribute("edit") GiaoDich giaoDich, BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("edit",giaoDich);

            model.addAttribute("customerList", this.giaoDichService.allCustomer());
            model.addAttribute("loaiDVList",this.giaoDichService.allLoaiDV());
            return "edit";
        }else{
            this.giaoDichService.save(giaoDich);
            redirectAttributes.addFlashAttribute("message", "update Complete !");
            return "redirect:/giaodich";
        }
    }

}
