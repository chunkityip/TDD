package com.example.demo;

import java.math.BigDecimal;

public class Supplier {
    // Part 1 US2:
    // As a baker, I should be able to get information about an ingredient
    private String name;
    private BigDecimal price;
    private double quantity;
    private String unit;

    public Supplier(String name, BigDecimal price, double quantity, String unit) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    // Part 1 US3
    // As a baker, I should be able to modify the details of an ingredient
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
