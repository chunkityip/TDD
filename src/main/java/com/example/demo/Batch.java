package com.example.demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Batch {
    private Map<Recipe, Integer> products;

    public Batch() {
        this.products = new HashMap<>();
    }

    public void addProduct(Recipe recipe, int quantity) {
        this.products.put(recipe, quantity);
    }

    public Map<Ingredient, Double> calculateTotalIngredients() {
        Map<Ingredient, Double> totalIngredients = new HashMap<>();
        for (Map.Entry<Recipe, Integer> entry : products.entrySet()) {
            Recipe recipe = entry.getKey();
            int quantity = entry.getValue();
            Map<Ingredient, Double> ingredientAmounts = recipe.calculateIngredientAmounts(quantity);
            for (Map.Entry<Ingredient, Double> ingredientEntry : ingredientAmounts.entrySet()) {
                Ingredient ingredient = ingredientEntry.getKey();
                double amount = ingredientEntry.getValue();
                totalIngredients.put(ingredient, totalIngredients.getOrDefault(ingredient, 0.0) + amount);
            }
        }
        return totalIngredients;
    }

    public BigDecimal calculateTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Map.Entry<Recipe, Integer> entry : products.entrySet()) {
            Recipe recipe = entry.getKey();
            int quantity = entry.getValue();
            for (Map.Entry<Ingredient, Double> ingredientEntry : recipe.getIngredients().entrySet()) {
                Ingredient ingredient = ingredientEntry.getKey();
                double amount = ingredientEntry.getValue() * quantity * recipe.getOutput(); // Total amount needed
                Supplier cheapestSupplier = ingredient.getCheapestSupplier();
                BigDecimal cost = cheapestSupplier.getPrice()
                        .multiply(BigDecimal.valueOf(amount))
                        .divide(BigDecimal.valueOf(cheapestSupplier.getQuantity()), BigDecimal.ROUND_HALF_UP);
                totalCost = totalCost.add(cost);
            }
        }
        return totalCost;
    }

    public boolean validateNoCycles() {
        Set<Recipe> visited = new HashSet<>();
        for (Recipe recipe : products.keySet()) {
            if (!validateNoCyclesHelper(recipe, visited, new HashSet<>())) {
                return false;
            }
        }
        return true;
    }

    private boolean validateNoCyclesHelper(Recipe recipe, Set<Recipe> visited, Set<Recipe> stack) {
        if (stack.contains(recipe)) {
            return false;
        }
        if (visited.contains(recipe)) {
            return true;
        }
        stack.add(recipe);
        for (Recipe subRecipe : recipe.getSubRecipes().keySet()) {
            if (!validateNoCyclesHelper(subRecipe, visited, stack)) {
                return false;
            }
        }
        stack.remove(recipe);
        visited.add(recipe);
        return true;
    }
}

