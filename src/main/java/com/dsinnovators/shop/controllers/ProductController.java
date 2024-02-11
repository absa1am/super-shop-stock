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
import org.springframework.web.bind.annotation.PostMapping;

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
        if (errors.hasErrors()) {
            model.addAttribute("suppliers", supplierService.getSuppliers());

            return "product/create";
        }

        productService.saveProduct(product);

        return "supplier/index";
    }

}
