package com.example.demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private String name;
    private Map<Ingredient , Double> ingredients;
    private Map<Recipe , Double> subRecipes;
    private int output;

    public Recipe(String name, int output) {
        this.name = name;
        this.output = output;
        this.ingredients = new HashMap<>(); // Initialize the ingredients map
        this.subRecipes = new HashMap<>(); // Initialize the subRecipes map
    }

    // Put ingredient and quantity into HashMap call ingredients
    public void addIngredient(Ingredient ingredient , double quantity) {
        ingredients.put(ingredient , quantity);
    }

    public void addSubRecipe(Recipe recipe , double quantity) {}

    public String getRecipeInfo() {
        return null;
    }

    public Map<Ingredient , BigDecimal> calculateIngredientAmounts(int multiplier) {
        return null;
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
