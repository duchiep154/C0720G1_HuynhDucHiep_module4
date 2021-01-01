package com.codegym.controller;

import com.codegym.entity.complex.GiaoDich;
import com.codegym.service.complex.CustomerService;
import com.codegym.service.complex.GiaoDichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
@RequestMapping({"", "/giaodich"})
public class GiaoDichController {

    @Autowired
    private GiaoDichService giaoDichService;




    @GetMapping
    public String home(Model model, @PageableDefault(size = 5) Pageable pageable,
                       @RequestParam Optional<String> keyword,
                       @RequestParam Optional<String> keywordTwo,
                       @RequestParam Optional<String> keywordAll
    ) {
        loadList(model, pageable, keyword, keywordTwo, keywordAll);
        model.addAttribute("giaoDichList", new GiaoDich());
        return "list";
    }

    @PostMapping("/deletegiaodich")
    public String deleteProduct(@RequestParam String deleteId, RedirectAttributes redirectAttributes) {
        this.giaoDichService.deleteGiaoDich(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Product Complete !");
        return "redirect:/giaodich";
    }



    private void loadList(Model model, @PageableDefault(size = 5) Pageable pageable,
                          @RequestParam Optional<String> nameSearch, @RequestParam Optional<String> loaiDVSearch,
                          @RequestParam Optional<String> keywordAll
    ) {
        testInformation(model, pageable, nameSearch, loaiDVSearch, keywordAll);
        model.addAttribute("customerList", this.giaoDichService.allCustomer());
        model.addAttribute("loaiDVList",this.giaoDichService.allLoaiDV());
    }

    private void testInformation(Model model, @PageableDefault(size = 5) Pageable pageable,
                                 @RequestParam Optional<String> nameSearch, @RequestParam Optional<String> loaiDVSearch,
                                 @RequestParam Optional<String> keywordAll
    ) {
        String nameSearchOld = "";
        String loaiDVSearchOld = "";
        String keywordAllOld = "";


        if (nameSearch.isPresent() && !nameSearch.get().equals("")
                && loaiDVSearch.get().equals("") && keywordAll.get().equals("")) {
            nameSearchOld = nameSearch.get();
            model.addAttribute("giaoDichList",
                    this.giaoDichService.findByCustomer_NameContaining(nameSearchOld));
        } else if (loaiDVSearch.isPresent() && !loaiDVSearch.get().equals("")
                && nameSearch.get().equals("") && keywordAll.get().equals("")) {
            loaiDVSearchOld = loaiDVSearch.get();
            model.addAttribute("giaoDichList",
                    this.giaoDichService.findByLoaiDV_NameContaining(loaiDVSearchOld));
        } else if (nameSearch.isPresent() && !nameSearch.get().equals("")
                && !loaiDVSearch.get().equals("") && keywordAll.get().equals("")) {
            nameSearchOld = nameSearch.get();
            loaiDVSearchOld = loaiDVSearch.get();
            model.addAttribute("giaoDich",
                    this.giaoDichService.findByCustomer_NameAndLoaiDV_NameContaining( nameSearchOld, nameSearchOld));
        } else if (keywordAll.isPresent() && !keywordAll.get().equals("")
                && nameSearch.get().equals("") && loaiDVSearch.get().equals("")) {
            keywordAllOld = keywordAll.get();
            model.addAttribute("productList",
                    this.giaoDichService.findByLoaiDV_NameOrCustomer_Name( keywordAllOld));
        }
         else {
            model.addAttribute("giaoDichList", this.giaoDichService.findAll());
        }
        model.addAttribute("nameSearchOld", nameSearchOld);
        model.addAttribute("loaiDVSearchOld", loaiDVSearchOld);
        model.addAttribute("keywordAllOld", keywordAllOld);

    }


    @GetMapping("/showCreateNewForPage")
    public String showCreateNewForPage(Model model) {
        model.addAttribute("giaoDichNew", new GiaoDich());
        model.addAttribute("cusTomerList", this.giaoDichService.allCustomer());
        model.addAttribute("loaiDVList",this.giaoDichService.allLoaiDV());
        return "create-new-product";
    }


    @PostMapping("/createNewForPage")
    public String createNewCustomer(RedirectAttributes redirectAttributes,
                                    @Validated @ModelAttribute GiaoDich giaoDich, BindingResult bindingResult,
                                    Model model) {




        if (bindingResult.hasFieldErrors()) {

            model.addAttribute("CustomerList", this.giaoDichService.allCustomer());
            model.addAttribute("loaiDVList",this.giaoDichService.allLoaiDV());
            return "create-new-product";
        }

        this.giaoDichService.save(giaoDich);
        redirectAttributes.addFlashAttribute("message", "Create Complete !");
        return "redirect:/giaodich";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable String id, Model model) {
        model.addAttribute("view", giaoDichService.findById(id));
        return "view-product";
    }
}
