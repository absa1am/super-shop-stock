package com.dsinnovators.shop.controllers;

import com.dsinnovators.shop.entities.Product;
import com.dsinnovators.shop.services.ProductService;
import com.dsinnovators.shop.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ProductController {

    private ProductService productService;
    private SupplierService supplierService;

    public ProductController(ProductService productService, SupplierService supplierService) {
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @GetMapping("/product")
    public String index(Model model) {
        model.addAttribute("products", productService.getProducts());

        return "product/index";
    }

    @GetMapping("/product/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("suppliers", supplierService.getSuppliers());

        return "product/create";
    }

    @PostMapping("/product/create")
    public String create(@Valid @ModelAttribute("product") Product product, BindingResult errors, Model model) {
        if (errors.hasErrors() || productService.saveProduct(product, errors).hasErrors()) {
            model.addAttribute("suppliers", supplierService.getSuppliers());

            return "product/create";
        }

        return "redirect:/product";
    }

    @GetMapping("/product/{id}/update")
    public String update(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.getProduct(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("suppliers", supplierService.getSuppliers());

            return "product/update";
        }

        return "error/index";
    }

    @PostMapping("/product/{id}/update")
    public String update(@Valid @ModelAttribute("product") Product product, BindingResult errors, @PathVariable Long id, Model model) {
        if (errors.hasErrors() || productService.updateProduct(product, id, errors).hasErrors()) {
            model.addAttribute("suppliers", supplierService.getSuppliers());

            return "product/update";
        }

        return "redirect:/product";
    }

    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.deleteProduct(id);

        return "redirect:/product";
    }

}
