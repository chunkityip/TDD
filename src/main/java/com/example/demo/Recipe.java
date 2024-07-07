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

    /*
    * Consider a Cake recipe that uses 200g Flour and 100g Sugar.It also includes a Frosting sub-recipe that uses 50g Sugar.
        //If the Cake recipe produces 1 unit, and you want to calculate the ingredients for 2 units:

        //Main Ingredients Calculation:

        //Flour: 200g * 2 = 400g
        //Sugar from the main recipe: 100g * 2 = 200g

        //Sub-Recipe Ingredients Calculation:
        //Sugar from the sub-recipe: 50g * 2 = 100g
        //Total Ingredients:

        //Flour: 400g
        //Sugar: 200g (main recipe) + 100g (sub-recipe) = 300g
     */
    public Map<Ingredient , Double> calculateIngredientAmounts(int multiplier) {
        Map<Ingredient , Double> amounts = new HashMap<>();

        // entry for ingredients
        addMainRecipeIngredients(amounts, multiplier);
        // entry for subRecipes
        addSubRecipeIngredients(amounts, multiplier);


        return amounts;
    }

    // This method is getting ingredients key and value multiple multiplier
    // And then put into new HashMap call amounts by
    private void addMainRecipeIngredients(Map<Ingredient, Double> amounts, int multiplier) {
        for(Map.Entry<Ingredient , Double> entry : ingredients.entrySet()) {
            amounts.put(entry.getKey() , entry.getValue() * multiplier);
        }
    }



    private void addSubRecipeIngredients(Map<Ingredient, Double> amounts, int multiplier) {
        for (Map.Entry<Recipe , Double> entry : subRecipes.entrySet()) {
            Recipe subRecipeKey = entry.getKey();
            int subRecipeValue = (int) (entry.getValue() * multiplier);
            Map<Ingredient , Double> subAmounts = subRecipeKey.calculateIngredientAmounts(subRecipeValue);
            combineIngredientAmounts(amounts , subAmounts);
        }
    }

    private void combineIngredientAmounts(Map<Ingredient, Double> mainAmounts, Map<Ingredient, Double> subAmounts) {
        for (Map.Entry<Ingredient, Double> subEntry : subAmounts.entrySet()) {
            // mainAmounts.getOrDefault(subEntry.getKey(), 0.0) mean:
            // if mainAmounts does not contain subEntry key, return 0.0
            // else, get the current value associated with subEntry
            mainAmounts.put(subEntry.getKey(), mainAmounts.getOrDefault(subEntry.getKey(), 0.0) + subEntry.getValue());
        }
    }


    public BigDecimal calculateCostPerUnit() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double amount = entry.getValue();
            totalCost = totalCost.add(ingredient.getPrice().multiply(BigDecimal.valueOf(amount / ingredient.getQuantity())));
        }
        for (Map.Entry<Recipe, Double> entry : subRecipes.entrySet()) {
            Recipe subRecipe = entry.getKey();
            double subRecipeQuantity = entry.getValue();
            totalCost = totalCost.add(subRecipe.calculateCostPerUnit().multiply(BigDecimal.valueOf(subRecipeQuantity)));
        }
        return totalCost.divide(BigDecimal.valueOf(output), BigDecimal.ROUND_HALF_UP);
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
