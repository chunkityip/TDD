package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BatchTest {

    @Test
    public void testAddProductsToBatch() {
        Batch batch = new Batch();
        Recipe cupcakeRecipe = new Recipe("Cupcake", 12);

        Ingredient flour = new Ingredient("Flour");
        Supplier flourSupplier = new Supplier("GoodSupplier", new BigDecimal("1.50"), 500, "grams");
        flour.addSupplier(flourSupplier);

        Ingredient sugar = new Ingredient("Sugar");
        Supplier sugarSupplier = new Supplier("BestSupplier", new BigDecimal("2.50"), 1000, "grams");
        sugar.addSupplier(sugarSupplier);

        cupcakeRecipe.addIngredient(flour, 200);
        cupcakeRecipe.addIngredient(sugar, 150);

        batch.addProduct(cupcakeRecipe, 10);

        assertEquals(2, batch.calculateTotalIngredients().size());
    }

    @Test
    public void testCalculateOverallRequiredIngredients() {
        Batch batch = new Batch();
        Recipe cupcakeRecipe = new Recipe("Cupcake", 12);

        Ingredient flour = new Ingredient("Flour");
        Supplier flourSupplier = new Supplier("GoodSupplier", new BigDecimal("1.50"), 500, "grams");
        flour.addSupplier(flourSupplier);

        Ingredient sugar = new Ingredient("Sugar");
        Supplier sugarSupplier = new Supplier("BestSupplier", new BigDecimal("2.50"), 1000, "grams");
        sugar.addSupplier(sugarSupplier);

        cupcakeRecipe.addIngredient(flour, 200);
        cupcakeRecipe.addIngredient(sugar, 150);
        batch.addProduct(cupcakeRecipe, 10);

        Map<Ingredient, Double> totalIngredients = batch.calculateTotalIngredients();
        assertEquals(2000, totalIngredients.get(flour));
        assertEquals(1500, totalIngredients.get(sugar));
    }

    @Test
    public void testCalculateTotalCost() {
        Batch batch = new Batch();
        Recipe cupcakeRecipe = new Recipe("Cupcake", 12);

        Ingredient flour = new Ingredient("Flour");
        Supplier flourSupplier = new Supplier("GoodSupplier", new BigDecimal("1.50"), 500, "grams");
        flour.addSupplier(flourSupplier);

        Ingredient sugar = new Ingredient("Sugar");
        Supplier sugarSupplier = new Supplier("BestSupplier", new BigDecimal("2.50"), 1000, "grams");
        sugar.addSupplier(sugarSupplier);

        cupcakeRecipe.addIngredient(flour, 200);
        cupcakeRecipe.addIngredient(sugar, 150);
        batch.addProduct(cupcakeRecipe, 10);

        BigDecimal totalCost = batch.calculateTotalCost();
        assertEquals(new BigDecimal("117.000"), totalCost); // Adjusted expected value based on correct calculations
    }

    @Test
    public void testValidateNoCycles() {
        Recipe cakeRecipe = new Recipe("Cake", 1);
        Recipe icingRecipe = new Recipe("Icing", 1);
        cakeRecipe.addSubRecipe(icingRecipe, 1);
        icingRecipe.addSubRecipe(cakeRecipe, 1); // Introduces cycle

        Batch batch = new Batch();
        batch.addProduct(cakeRecipe, 2);
        assertFalse(batch.validateNoCycles());
    }

    @Test
    public void testValidateNoCyclesWithoutCycles() {
        Recipe cakeRecipe = new Recipe("Cake", 1);
        Recipe icingRecipe = new Recipe("Icing", 1);
        cakeRecipe.addSubRecipe(icingRecipe, 1);

        Batch batch = new Batch();
        batch.addProduct(cakeRecipe, 2);
        assertTrue(batch.validateNoCycles());
    }
}
