package com.dsinnovators.shop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Description")
    private String description;
    @NotNull(message = "Purchase price can not be empty")
    @PositiveOrZero(message = "Purchase price can not be negative")
    private double purchasePrice;
    @NotNull(message = "Retail price can not be empty")
    @PositiveOrZero(message = "Retail price can not be negative")
    private double retailPrice;
    @PositiveOrZero(message = "Quantity can not be negative")
    private int quantity;
    @ManyToOne
    @NotNull(message = "Supplier can not be empty")
    private Supplier supplier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
