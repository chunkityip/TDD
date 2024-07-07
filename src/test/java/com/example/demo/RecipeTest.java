package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private Recipe cupcakeRecipe;
    private Ingredient flour;
    private Supplier flourSupplier;

    private Ingredient sugar;
    private Supplier sugarSupplier;


    @BeforeEach
    void setUp() {
        // Given -- init
        cupcakeRecipe = new Recipe("Cupcake", 12);

        flour = new Ingredient("Flour");
        flourSupplier = new Supplier("GoodSupplier", new BigDecimal("1.50"), 500, "grams");

        sugar = new Ingredient("Sugar");
        sugarSupplier = new Supplier("BestSupplier", new BigDecimal("2.50"), 1000, "grams");

    }


    @Test
    void addIngredientTest() {
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

    }

    @Test
    void calculateIngredientAmounts() {
    }

    @Test
    void calculateCostPerUnit() {
    }
}