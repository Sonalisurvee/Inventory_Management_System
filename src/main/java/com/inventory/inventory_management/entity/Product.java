package com.inventory.inventory_management.entity;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private int quantity;

    private double price;

    // NEW: Store relationship
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    // Default Constructor
    public Product() {
    }

    // Parameterized Constructor
    public Product(String name, String category, int quantity, double price) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // NEW Getter
    public Store getStore() {
        return store;
    }

    // NEW Setter
    public void setStore(Store store) {
        this.store = store;
    }
}