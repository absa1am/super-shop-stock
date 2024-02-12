package com.dsinnovators.shop.services;

import com.dsinnovators.shop.entities.Product;
import com.dsinnovators.shop.repositories.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Errors saveProduct(Product product, BindingResult errors) {
        try {
            productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("name", "error.name", "Product name already exists");
        }

        return errors;
    }

    public Errors updateProduct(Product product, Long id, BindingResult errors) {
        Product oldProduct = productRepository.findById(id).get();

        oldProduct.setId(product.getId());
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPurchasePrice(product.getPurchasePrice());
        oldProduct.setRetailPrice(product.getRetailPrice());
        oldProduct.setQuantity(product.getQuantity());
        oldProduct.setSupplier(product.getSupplier());

        try {
            productRepository.save(oldProduct);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("name", "error.name", "Product name already exists");
        }

        return errors;
    }

    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.delete(product.get());
        }
    }

}
