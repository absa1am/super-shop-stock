package com.dsinnovators.shop.controllers;

import com.dsinnovators.shop.dto.SupplierDTO;
import com.dsinnovators.shop.entities.Supplier;
import com.dsinnovators.shop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/supplier/create")
    public String create(Model model) {
        model.addAttribute("supplier", new SupplierDTO());

        return "createSupplier";
    }

    @PostMapping("/supplier/create")
    public String create(@ModelAttribute Supplier supplier, Model model) {
        supplierService.save(supplier);

        return "index";
    }

}
