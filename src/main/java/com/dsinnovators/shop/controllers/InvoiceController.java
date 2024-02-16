package com.dsinnovators.shop.controllers;

import com.dsinnovators.shop.services.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoice/create")
    public String create(Model model) {
        return "invoice/create";
    }

}
