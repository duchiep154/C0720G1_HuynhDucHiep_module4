package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import com.codegym.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService=new ProductServiceImpl();

    @GetMapping("/")
    public String index(Model model, RedirectAttributes redirect) {
        model.addAttribute("products", productService.findAll());
        redirect.addFlashAttribute("message","");
        return "/index";
    }
    @GetMapping("/product/create")
    public String create(Model model) {
        model.addAttribute("productSave", new Product());
        return "/create";
    }
    @PostMapping("/product/save")
    public String save(Product productSave, RedirectAttributes redirect) {

        productService.save(productSave);
        redirect.addFlashAttribute("message", "Saved product successfully!");
        return "redirect:/";
    }
    @GetMapping("/product/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("productEdit", productService.findById(id));
        return "/edit";
    }
    @PostMapping("/product/update")
    public String update(Product product, RedirectAttributes redirect) {
        productService.update(product.getId(), product);
        redirect.addFlashAttribute("message", "Modified product successfully!");
        return "redirect:/";
    }
    @GetMapping("/product/{id}/view")
    public String view(@PathVariable int id, Model model){
        model.addAttribute("productView", productService.findById(id));
        return "/detail";
    }

    @GetMapping("/product/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("productView",productService.findById(id));
        return "/detail";
    }

    @PostMapping("/product/remove")
    public String remove(Product product, RedirectAttributes redirectAttributes){
        productService.remove(product.getId());
        redirectAttributes.addFlashAttribute("message","Delete Successfully");
        return "redirect:/";
    }
}
