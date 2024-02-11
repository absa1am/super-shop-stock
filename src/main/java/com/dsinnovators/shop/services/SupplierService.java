package com.dsinnovators.shop.services;

import com.dsinnovators.shop.entities.Supplier;
import com.dsinnovators.shop.repositories.SupplierRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAllByIsDeletedIsFalse();
    }

    public Optional<Supplier> getSupplier(Long id) {
        return supplierRepository.findById(id);
    }

    public Errors saveSupplier(Supplier supplier, Errors errors) {
        try {
            supplierRepository.save(supplier);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("name", "error.name", "Name already exists");
        }

        return errors;
    }

    public Errors updateSupplier(Supplier supplier, Long id, Errors errors) {

        Supplier oldSupplier = supplierRepository.findById(id).get();

        oldSupplier.setId(id);
        oldSupplier.setName(supplier.getName());
        oldSupplier.setEmail(supplier.getEmail());
        oldSupplier.setPhone(supplier.getPhone());
        oldSupplier.setAddress(supplier.getAddress());

        try {
            supplierRepository.save(oldSupplier);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("name", "error.name", "Name already exists");
        }

        return errors;
    }

    public void deleteSupplier(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        if (supplier.isPresent()) {
            supplier.get().setIsDeleted(true);

            supplierRepository.save(supplier.get());
        }
    }

}
