package com.dsinnovators.shop.controllers;

import com.dsinnovators.shop.entities.Supplier;
import com.dsinnovators.shop.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/supplier")
    public String index(Model model) {
        List<Supplier> suppliers = supplierService.getSuppliers();

        model.addAttribute("suppliers", suppliers);

        return "/supplier/index";
    }

    @GetMapping("/supplier/create")
    public String create(Model model) {
        model.addAttribute("supplier", new Supplier());

        return "/supplier/create";
    }

    @PostMapping("/supplier/create")
    public String create(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult errors, Model model) {
        if (errors.hasErrors() || supplierService.saveSupplier(supplier, errors).hasErrors()) {
            return "/supplier/create";
        }

        return "redirect:/supplier";
    }

    @GetMapping("/supplier/{id}/update")
    public String update(Model model, @PathVariable Long id) {
        Optional<Supplier> supplier = supplierService.getSupplier(id);

        if (supplier.isPresent()) {
            model.addAttribute("supplier", supplier.get());
        } else {
            return "/error/index";
        }

        return "/supplier/update";
    }

    @PostMapping("/supplier/{id}/update")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("supplier") Supplier supplier, BindingResult errors) {
        if (errors.hasErrors() || supplierService.updateSupplier(supplier, id, errors).hasErrors()) {
            return "/supplier/update";
        }

        return "redirect:/supplier";
    }

    @PostMapping("/supplier/{id}/delete")
    public String delete(@PathVariable Long id) {
        supplierService.deleteSupplier(id);

        return "redirect:/supplier";
    }

}
