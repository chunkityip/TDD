package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Ingredient flour;
    private Supplier flourSupplier;

    private Ingredient sugar;
    private Supplier sugarSupplier;


    @BeforeEach
    void setUp() {
        // Given -- init
        flour = new Ingredient("Flour");
        flour.addSupplier(new Supplier("BestSupplier", new BigDecimal("1.40"), 500, "grams"));

        sugar = new Ingredient("Sugar");
        sugar.addSupplier(new Supplier("BestSupplier", new BigDecimal("2.30"), 1000, "grams"));

    }


    @Test
    void addIngredientTest() {
        // Given - init
        Recipe cupcakeRecipe = new Recipe("Cupcake", 12);

        // When - operation
        flour.addSupplier(flourSupplier);
        sugar.addSupplier(sugarSupplier);

        cupcakeRecipe.addIngredient(flour, 200);
        cupcakeRecipe.addIngredient(sugar, 150);

        // Then - result
        assertEquals(2, cupcakeRecipe.getIngredients().size());

        // It should return Cupcake and 12 by line 23: cupcakeRecipe = new Recipe("Cupcake", 12);
        assertNotEquals("Cheesecake" , cupcakeRecipe.getName());
        assertEquals(12 , cupcakeRecipe.getOutput());
    }

    @Test
    void addSubRecipeTest() {
        // Given - init
        Ingredient milk = new Ingredient("Milk");
        Supplier milkSupplier = new Supplier("GoodSupplier", new BigDecimal("2.00"), 25, "ml");

        Ingredient vanillaExtract = new Ingredient("Vanilla extract");
        Supplier vanillaExtractSupplier = new Supplier("GoodSupplier", new BigDecimal("38.68"), 5, "ml");

        Recipe cakeRecipe = new Recipe("Cake " , 1);
        Recipe icingRecipe = new Recipe("Icing" , 1);

        // When - operation
        sugar.addSupplier(sugarSupplier);
        //flour.addSupplier(flourSupplier);
        milk.addSupplier(milkSupplier);
        vanillaExtract.addSupplier(vanillaExtractSupplier);

        icingRecipe.addIngredient(sugar , 150);
        icingRecipe.addIngredient(milk , 50);
        icingRecipe.addIngredient(vanillaExtract , 2);
        //icingRecipe.addIngredient(flour , 100);

        cakeRecipe.addSubRecipe(icingRecipe , 1);

        // Then - result
        assertEquals(1 , cakeRecipe.getSubRecipes().size());
        // Check if the sub-recipe exists in the subRecipes map
        assertTrue(cakeRecipe.getSubRecipes().containsKey(icingRecipe));
    }

    @Test
    void calculateIngredientAmountsTest() {
        // Given - init
        Recipe cupcakeRecipe = new Recipe("Cupcake", 12);

        // When - operation
        flour.addSupplier(flourSupplier);
        sugar.addSupplier(sugarSupplier);

        cupcakeRecipe.addIngredient(flour, 200);
        cupcakeRecipe.addIngredient(sugar, 150);

        Map<Ingredient, Double> ingredientAmounts = cupcakeRecipe.calculateIngredientAmounts(2);

        // Then - result
        // 200 * 2
        assertEquals(400, ingredientAmounts.get(flour));
        // 150 * 2
        assertEquals(300, ingredientAmounts.get(sugar));
    }

    @Test
    void calculateCostPerUnit() {
        // Given - init
        Recipe cupcakeRecipe = new Recipe("Cupcake", 12);

        // Since we already have BestSupplier for both Flour and Sugar,
        // We just need to init above two to compare the price
        flour.addSupplier(new Supplier("AnotherSupplier", new BigDecimal("1.50"), 500, "grams"));
        sugar.addSupplier(new Supplier("AnotherSupplier", new BigDecimal("2.50"), 1000, "grams"));


        cupcakeRecipe.addIngredient(flour, 200);
        cupcakeRecipe.addIngredient(sugar, 150);

        BigDecimal costPerUnit = cupcakeRecipe.calculateCostPerUnit();

        assertEquals(new BigDecimal("0.0754"), costPerUnit);

        // After ingredient.getCheapestSupplier() , we got:
        /*
            Flour:
            Cheapest Supplier: "BestSupplier"
            Price: $1.40
            Quantity: 500 grams
         */

        /*
             Sugar:
             Cheapest Supplier: "BestSupplier"
             Price: $2.30
             Quantity: 1000 grams
         */

        /*
        Since we need 12 cupcakes, so we need:
            200 grams Flour
            500 grams Sugar

        Flour per unit: 1.40 / 500 = 0.0028
        Sugar per unit: 2.30 / 1000 = 0.0023

        Then we need to multiply the price per unit:
        Cost of Flour: 200 grams * 0.0028 = 0.5600
        Cost of Sugar: 150 grams * 0.0023 = 0.3450

        Total cost for 12 cupcakes: 0.56 + 0.345 = 0.9050
        Cost per unit: 0.9050 / 12 = 0.0754
         */

    }
}