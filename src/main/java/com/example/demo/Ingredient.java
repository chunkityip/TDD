package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private String name;
    private List<Supplier> supplier;


    public Ingredient(String name) {
        // Part 1 US1:
        // As a baker, I should be able to add ingredients to the system
        this.name = name;
        this.supplier = new ArrayList<>();
    }

    // Extension US1:
    // As a baker, I should be able to store multiple supplier options for a given ingredient

    // Part 1 US1:
    // As a baker, I should be able to add ingredients to the system

    // This method is to add supplier base on ArrayList Supplier class using add()
    public void addSupplier(Supplier supplier) {
        this.supplier.add(supplier);
    }

    // Extension US1:
    // such that I can better plan my orders
    public Supplier getCheapestSupplier() {
        Supplier cheapest = null;
        //Using for each loop base on a collection
        for (Supplier s : supplier) {
            //-1 is numerically less than, 0 is equal to and 1 is greater than val
            // check if s price less than the cheapest price
            if (cheapest == null || s.getPrice().compareTo(cheapest.getPrice()) == -1) {
                cheapest = s;
            }
        }
        return cheapest;
    }


    // Part 1 US2:
    // As a baker, I should be able to get information about an ingredient
    public String getInfo() {
        // Since String is immutable, StringBuilder is a good chosen to do so
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");

        for (Supplier s : supplier) {
            sb.append("Supplier: ").append(s.getName())
                    .append(", Price: ").append(s.getPrice().toString())
                    .append(", Quantity: ").append(String.format("%.2f", s.getQuantity()))
                    .append(" ").append(s.getUnit())
                    .append("\n");
        }

        return sb.toString();
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
