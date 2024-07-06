package com.example.demo;

import java.util.List;

public class Ingredient {
    private String name;
    private List<Supplier> supplier;

    public Ingredient(String name, List<Supplier> supplier) {
        this.name = name;
        this.supplier = supplier;
    }

    public Supplier compareSupplierPrice() {
        return null;
    }

    public String getInfo() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(List<Supplier> supplier) {
        this.supplier = supplier;
    }
}
