package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import com.codegym.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {

    private IProductService productService = new ProductService();


    @GetMapping("/")
    ModelAndView showProducts() {
        ModelAndView modelAndView = new ModelAndView("/index");
        List productList = productService.findAll();
        modelAndView.addObject("products", productList);
        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Product product, RedirectAttributes redirect) {
        List<Product> productList = productService.findAll();
        int size = productList.size();
        size++;
        product.setId(size);
        productService.save(product);
        redirect.addFlashAttribute("success", "Luu thanh cong");
        return "redirect:/";
    }

    @GetMapping("/products/{id}/edit")
    ModelAndView showEditForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit")
    public String update(Product product, RedirectAttributes redirect) {
        productService.update(product.getId(), product);
        redirect.addFlashAttribute("success", "Sua thanh cong");
        return "redirect:/";
    }

    @GetMapping("/products/{id}/delete")
    ModelAndView showDeleteForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Xoa thanh cong");
        return "redirect:/";
    }
    @GetMapping("/products/{id}/view")
    ModelAndView showView(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("product",productService.findById(id));
        return modelAndView;
    }


}
