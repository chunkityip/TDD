package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {
    private Ingredient suger;
    private Supplier supplier1;
    private Supplier supplier2;

    @BeforeEach
    void setUp() {
        // Given - init
        suger = new Ingredient("suger");
        supplier1 = new Supplier("SupplierA", new BigDecimal("1.50"), 500, "grams");
        supplier2 = new Supplier("SupplierB", new BigDecimal("1.40"), 500, "grams");

        // When - operation
        suger.addSupplier(supplier1);
        suger.addSupplier(supplier2);
    }

    @Test
    void addSupplierTest() {
        // Then - result
        assertEquals(2 , suger.getSupplier().size());
    }

    @Test
    void compareSupplierPrice() {
        // When - operation
        Supplier cheapest = suger.getCheapestSupplier();

        // Then - result
        assertEquals("SupplierB" , cheapest.getName());
        assertNotEquals(new BigDecimal("1.50") , cheapest.getPrice());
    }

}