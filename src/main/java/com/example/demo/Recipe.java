package com.example.demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private String name;
    private Map<Ingredient , Double> ingredients;
    private Map<Recipe , Double> subRecipes;
    private int output;

    // Part 2 US1:
    // As a baker, I should be able to store a recipe in the system
    public Recipe(String name, int output) {
        this.name = name;
        this.output = output;
        this.ingredients = new HashMap<>(); // Initialize the ingredients map
        this.subRecipes = new HashMap<>(); // Initialize the subRecipes map
    }

    // Part 2 US2 and US3:
    // As a baker, I should be able to define one or more ingredients
    // As a baker, I should be able to provide quantities and how many of the created output

    // Put ingredient and quantity into ingredients HashMap
    public void addIngredient(Ingredient ingredient , double quantity) {
        ingredients.put(ingredient , quantity);
    }

    // Part 4 US1:
    // As a baker, I am allowed to use another recipe

    // Put recipe and quantity into subRecipes HashMap
    public void addSubRecipe(Recipe recipe , double quantity) {
        this.subRecipes.put(recipe, quantity);
    }

    public String getRecipeInfo() {
        return null;
    }

    // Part 2 US4:
    // As a baker, I should be able to calculate the amount of each ingredient

    // Using Map.entry to have direct access to both the key and the value to iterate over the entries
    public Map<Ingredient , BigDecimal> calculateIngredientAmounts(int multiplier) {
        Map<Ingredient , Double> amount = new HashMap<>();

        // entry for ingredients
        for(Map.Entry<Ingredient , Double> entry : ingredients.entrySet()) {
            amount.put(entry.getKey() , entry.getValue() * multiplier);
        }

        // entry for subRecipes
        for (Map.Entry<Recipe , Double> entry : subRecipes.entrySet()) {
            Map<Ingredient , Double> subAmount = new HashMap<>();

        }
    }

    public BigDecimal calculateCostPerUnit() {
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Map<Ingredient, Double> getIngredients() {
        return ingredients;
    }

//    public void setIngredient(Map<Ingredient, Double> ingredient) {
//        this.ingredient = ingredient;
//    }

    public Map<Recipe, Double> getSubRecipes() {
        return subRecipes;
    }

    public void setSubRecipes(Map<Recipe, Double> subRecipes) {
        this.subRecipes = subRecipes;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

}
