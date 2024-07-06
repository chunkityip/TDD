package com.example.demo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Batch {

    public Batch(Map<Recipe, Integer> products) {
        this.products = products;
    }

    private Map<Recipe , Integer> products;

    public void addProduct(Recipe recipe , int quantity){}

    public Map<Ingredient , Double> calculateTotalIngredients() {
        return null;
    }

    public BigDecimal calculateTotalCost() {
        return null;
    }

    public boolean validateNoCycles() {
        return false;
    }

    private boolean validateNoCyclesHelper(Recipe recipe, Set<Recipe> visited, Set<Recipe> stack) {
        return false;
    }

    public Map<Recipe, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Recipe, Integer> products) {
        this.products = products;
    }
}
