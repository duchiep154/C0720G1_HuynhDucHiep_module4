package com.codegym.controller;

import com.codegym.entity.Product;
import com.codegym.service.ProductService;
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
@RequestMapping({"", "/product"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String home(Model model, @PageableDefault(size = 5) Pageable pageable,
                       @RequestParam Optional<String> keyword,
                       @RequestParam Optional<String> keywordTwo,
                       @RequestParam Optional<String> keywordAll,
                       @RequestParam Optional<String> keywordTree,
                       @RequestParam Optional<String> keywordFour,
                       @RequestParam Optional<String> sortBy) {
        loadList(model, pageable, keyword, keywordTwo, keywordAll,keywordTree,keywordFour, sortBy);
        model.addAttribute("product", new Product());
        return "product-home";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam Integer deleteId, RedirectAttributes redirectAttributes) {
        this.productService.deleteProduct(deleteId);
        redirectAttributes.addFlashAttribute("message", "Delete Product Complete !");
        return "redirect:/product";
    }

    @PostMapping("/createNew")
    public String createNewProduct(RedirectAttributes redirectAttributes,
                                   @Validated @ModelAttribute Product product, BindingResult bindingResult,
                                   @PageableDefault(size = 5) Pageable pageable, Model model,
                                   @RequestParam Optional<String> keyword, @RequestParam Optional<String> keywordTwo,
                                   @RequestParam Optional<String> keywordAll,
                                   @RequestParam Optional<String> keywordTree,
                                   @RequestParam Optional<String> keywordFour,
                                   @RequestParam Optional<String> sortBy) {
//        boolean check = false;
//        List<Product> productList = this.productService.findAll();
//        for (Product element : productList) {
//            if (element.getId().equals(product.getId())) {
//                check = true;
//                break;
//            }
//        }

        new Product().validate(product, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            loadList(model, pageable, keyword, keywordTwo, keywordAll,keywordTree,keywordFour, sortBy);
            model.addAttribute("wrongCreate", "errorCreate");
            if (!bindingResult.hasFieldErrors()) {
                model.addAttribute("idExist", "idExist");
                model.addAttribute("messageIdExist", "ID is exist ! Please input ID other !");
            }
            return "product-home";
        }

        this.productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Create Complete !");
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(RedirectAttributes redirectAttributes,
                                @Validated @ModelAttribute Product product, BindingResult bindingResult,
                                @PageableDefault(size = 5) Pageable pageable, Model model,
                                @RequestParam Optional<String> keyword, @RequestParam Optional<String> keywordTwo,
                                @RequestParam Optional<String> keywordAll,
                                @RequestParam Optional<String> keywordTree,
                                @RequestParam Optional<String> keywordFour,
                                @RequestParam Optional<String> sortBy) {
        new Product().validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            loadList(model, pageable, keyword, keywordTwo, keywordAll,keywordTree,keywordFour, sortBy);
            model.addAttribute("wrongEdit", "errorEdit");
            return "product-home";
        }
        this.productService.update(product);
        redirectAttributes.addFlashAttribute("message", "Update Complete !");
        return "redirect:/product";
    }

    private void loadList(Model model, @PageableDefault(size = 5) Pageable pageable,
                          @RequestParam Optional<String> keyword, @RequestParam Optional<String> keywordTwo,
                          @RequestParam Optional<String> keywordAll,
                          @RequestParam Optional<String> keywordTree,
                          @RequestParam Optional<String> keywordFour,
                          @RequestParam Optional<String> sortBy) {
        testInformation(model, pageable, keyword, keywordTwo, keywordAll,keywordTree,keywordFour, sortBy);
        model.addAttribute("productTypeList", this.productService.allProductType());
    }

    private void testInformation(Model model, @PageableDefault(size = 5) Pageable pageable,
                                 @RequestParam Optional<String> keyword, @RequestParam Optional<String> keywordTwo,
                                 @RequestParam Optional<String> keywordAll,
                                 @RequestParam Optional<String> keywordTree,
                                 @RequestParam Optional<String> keywordFour,
                                 @RequestParam Optional<String> sortBy) {
        String keywordOld = "";
        String keywordTwoOld = "";
        String keywordAllOld = "";
        String keywordTreeOld = "";
        String keywordFourOld = "";
        String informationSort = "";
        if (keyword.isPresent() && !keyword.get().equals("")
                && keywordTwo.get().equals("") && keywordAll.get().equals("")&& keywordTree.get().equals("")&& keywordFour.get().equals("")) {
            keywordOld = keyword.get();
            model.addAttribute("productList",
                    this.productService.findByNameContaining(pageable, keywordOld));
        } else if (keywordTwo.isPresent() && !keywordTwo.get().equals("")
                && keyword.get().equals("") && keywordAll.get().equals("")) {
            keywordTwoOld = keywordTwo.get();
            model.addAttribute("productList",
                    this.productService.findByCostContaining(pageable, keywordTwoOld));
        } else if (keyword.isPresent() && !keyword.get().equals("")
                && !keywordTwo.get().equals("") && keywordAll.get().equals("")) {
            keywordOld = keyword.get();
            keywordTwoOld = keywordTwo.get();
            model.addAttribute("productList",
                    this.productService.findByNameAndCostContaining(pageable, keywordOld, keywordTwoOld));
        } else if (keywordAll.isPresent() && !keywordAll.get().equals("")
                && keywordTwo.get().equals("") && keyword.get().equals("")) {
            keywordAllOld = keywordAll.get();
            model.addAttribute("productList",
                    this.productService.findAllOfMe(pageable, keywordAllOld));
        }
        else if (keywordTree.isPresent()&&keywordFour.isPresent() &&!keywordTree.get().equals("") &&!keywordFour.get().equals("")){
            keywordTreeOld=keywordTree.get();
            keywordFourOld=keywordFour.get();
            model.addAttribute("productList",
                    this.productService.findByDateImportBetween(pageable, keywordTreeOld,keywordFourOld));
        }
        else if (keywordTree.isPresent() && keywordFour.get().equals("")
        && !keywordTree.get().equals("")){
            keywordFour=keywordTree;
            keywordTreeOld=keywordTree.get();
            keywordFourOld=keywordFour.get();
            model.addAttribute("productList",
                    this.productService.findByDateImportBetween(pageable, keywordTreeOld,keywordFourOld));


        }
        else if (sortBy.isPresent() && !sortBy.get().equals("")) {
            informationSort = sortBy.get();
            if (informationSort.equals("id")) {
                model.addAttribute("productList", this.productService.findAllAndSortByID(pageable));
            }
            if (informationSort.equals("name")) {
                model.addAttribute("productList",
                        this.productService.findAllAndSortByName(pageable));
            }
//            if (informationSort.equals("birthDay")) {
//                model.addAttribute("productList",
//                        this.productService.findAllAndSortByBirthDay(pageable));
//            }
        } else {
            model.addAttribute("productList", this.productService.findAll(pageable));
        }
        model.addAttribute("keywordOld", keywordOld);
        model.addAttribute("keywordTwoOld", keywordTwoOld);
        model.addAttribute("keywordAllOld", keywordAllOld);
        model.addAttribute("keywordTreeOld", keywordTreeOld);
        model.addAttribute("keywordFourOld", keywordFourOld);
        model.addAttribute("informationSort", informationSort);
    }


    @GetMapping("/showCreateNewForPage")
    public String showCreateNewForPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productTypeList", this.productService.allProductType());
        return "create-new-product";
    }

    @PostMapping("/createNewForPage")
    public String createNewCustomer(RedirectAttributes redirectAttributes,
                                    @Validated @ModelAttribute Product product, BindingResult bindingResult,
                                    Model model) {
        boolean check = false;
        List<Product> productList = this.productService.findAll();
        for (Product element : productList) {
            if (element.getId().equals(product.getId())) {
                check = true;
                break;
            }
        }

        new Product().validate(product, bindingResult);
        if (bindingResult.hasFieldErrors() || check) {
            if (!bindingResult.hasFieldErrors()) {
                model.addAttribute("idExist", "idExist");
                model.addAttribute("messageIdExist", "ID is exist ! Please input ID other !");
            }
            model.addAttribute("productTypeList", this.productService.allProductType());
            return "create-new-product";
        }

        this.productService.save(product);
        redirectAttributes.addFlashAttribute("message", "Create Complete !");
        return "redirect:/product";
    }
}