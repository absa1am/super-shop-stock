package com.dsinnovators.shop.controllers;

import com.dsinnovators.shop.entities.Supplier;
import com.dsinnovators.shop.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public String index(Model model) {
        List<Supplier> suppliers = supplierService.getAll();

        model.addAttribute("suppliers", suppliers);

        return "supplier/index";
    }

    @GetMapping("/supplier/create")
    public String create(Model model) {
        model.addAttribute("supplier", new Supplier());

        return "supplier/create";
    }

    @PostMapping("/supplier/create")
    public String create(@Valid @ModelAttribute("supplier") Supplier supplier, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "supplier/create";
        }

        supplierService.save(supplier);

        return "redirect:/index";
    }

}
