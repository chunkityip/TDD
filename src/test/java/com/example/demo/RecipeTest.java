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
        flourSupplier = new Supplier("GoodSupplier", new BigDecimal("1.50"), 500, "grams");

        sugar = new Ingredient("Sugar");
        sugarSupplier = new Supplier("BestSupplier", new BigDecimal("2.50"), 1000, "grams");

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
    void calculateIngredientAmounts() {
    }

    @Test
    void calculateCostPerUnit() {
    }
}