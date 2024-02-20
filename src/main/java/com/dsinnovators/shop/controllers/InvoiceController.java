package com.dsinnovators.shop.controllers;

import com.dsinnovators.shop.entities.Invoice;
import com.dsinnovators.shop.entities.Product;
import com.dsinnovators.shop.services.InvoiceService;
import com.dsinnovators.shop.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class InvoiceController {

    private InvoiceService invoiceService;
    private ProductService productService;

    public InvoiceController(InvoiceService invoiceService, ProductService productService) {
        this.invoiceService = invoiceService;
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping("/products")
    public List<Product> product() {
        return productService.getProducts();
    }

    @GetMapping("/invoice/create")
    public String create(Model model) {
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("products", productService.getProducts());

        return "invoice/create";
    }

}
