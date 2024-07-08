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

    // Part 2 US4 amd US5
    // As a baker, I should be able to calculate the amount of each ingredient
    // As a baker, I should be able to calculate the amount of each ingredient I would need to purchase

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

    // This method is getting recipe key and value multiple multiplier
    // and then put into new HashMap call subAmounts by using combineIngredientAmounts()
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


    // Part 2 US6:
    // As a baker, I should be able to calculate how much each unit of the product
    /*
    // Suppose the cheapest supplier offers 500 grams of flour for $1.50. Here’s how the calculation works:

    //      Total price: $1.50
    //      Quantity: 500 grams
    //      To find the price per gram:

    // Convert the quantity to BigDecimal: BigDecimal.valueOf(500)
    // Divide the total price by the quantity
    // The result will be 0.003 (the price per gram)

     */

    public BigDecimal calculateCostPerUnit() {
        BigDecimal totalCost = BigDecimal.ZERO;
        int scale = 4; // Adjust scale for precision if needed

        // Calculate the cost of each ingredient
        for (Map.Entry<Ingredient, Double> entry : ingredients.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double amount = entry.getValue();

            // Since the baker needs to ensure the profitability of the enterprise , we should compare the price
            // by using ingredient.getCheapestSupplier()
            Supplier cheapestSupplier = ingredient.getCheapestSupplier();

            if (cheapestSupplier != null) {

                // Using BigDecimal.valueOf() to change to double
                // BigDecimal.ROUND_HALF_UP: if the result of the division is halfway between two possible results, it will round up to the nearest whole number. For example, 2.5 would be rounded to 3
                BigDecimal pricePerUnit = cheapestSupplier
                        .getPrice()
                        .divide(BigDecimal
                                .valueOf(cheapestSupplier.getQuantity()), scale, BigDecimal.ROUND_HALF_UP);

                BigDecimal ingredientCost = pricePerUnit
                        .multiply(BigDecimal.valueOf(amount))
                        .setScale(scale, BigDecimal.ROUND_HALF_UP); // Ensure proper rounding here

                // To ensure the result of unit test is correct
                System.out.println("Ingredient: " + ingredient.getName() + ", Amount: " + amount
                        + ", Price per unit: " + pricePerUnit + ", Ingredient cost: " + ingredientCost
                + " Supplier name :" + cheapestSupplier.getName());

                totalCost = totalCost.add(ingredientCost);
            }
        }

        System.out.println("Total Cost before division: " + totalCost);
        BigDecimal costPerUnit = totalCost.divide(BigDecimal.valueOf(output), scale, BigDecimal.ROUND_HALF_UP);
        System.out.println("Total Cost per unit: " + costPerUnit);
        return costPerUnit.setScale(scale, BigDecimal.ROUND_HALF_UP); // Ensure a final result is properly rounded
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
