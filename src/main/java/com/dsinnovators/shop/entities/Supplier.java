package com.dsinnovators.shop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Phone number can not be empty")
    private String phone;
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email can not be empty")
    private String email;
    @NotBlank(message = "Address can not be empty")
    private String address;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
