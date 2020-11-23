package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home (Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "home";
    }

    @GetMapping("/product/create")
    public String create(Model model){
        model.addAttribute("productSave",new Product());
        return "create";
    }

    @PostMapping("/product/save")
    public String save(Product product, RedirectAttributes redirect){
        productService.save(product);
        redirect.addFlashAttribute("message","Create Successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/edit")
    public String edit(@PathVariable int id, Model model){
        model.addAttribute("productEdit",productService.findById(id));
        return "edit";
    }

    @PostMapping("/product/update")
    public String update(Product product, RedirectAttributes redirectAttributes){
        productService.update(product.getId(), product);
        redirectAttributes.addFlashAttribute("message","Update Successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("productDelete",productService.findById(id));
        return "delete";
    }

    @PostMapping("/product/remove")
    public String remove(Product product, RedirectAttributes redirectAttributes){
        productService.remove(product.getId());
        redirectAttributes.addFlashAttribute("message","Delete Successfully");
        return "redirect:/";
    }

    @GetMapping("/product/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("productView", productService.findById(id));
        return "detail_view";
    }

}
