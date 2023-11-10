package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.CategoryImpl;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.ProductImpl;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryImplTests {

    private Category category;
    private Product product;

    @BeforeEach
    public void setUp() {
        category = new CategoryImpl("Category");
        product = new ProductImpl("asdf", "asdf", 2.5, GenderType.MEN);
        category.addProduct(product);
    }

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        Assertions.assertEquals("Category", category.getName());
    }

    @Test
    public void constructor_Should_InitializeProducts_When_ArgumentsAreValid() {
        category = new CategoryImpl("Asdf");
        Assertions.assertEquals(0, category.getProducts().size());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> category = new CategoryImpl("A"));
    }

    @Test
    public void addProduct_Should_AddProductToList() {
        Assertions.assertEquals(1, category.getProducts().size());
    }

    @Test
    public void removeProduct_Should_RemoveProductFromList_When_ProductExist() {
        category.removeProduct(product);
        Assertions.assertEquals(0, category.getProducts().size());
    }

    @Test
    public void removeProduct_should_notRemoveProductFromList_when_productNotExist() {
        Product nonExistingProduct = new ProductImpl("fdsa", "fda", 1.2, GenderType.WOMEN);
        category.removeProduct(nonExistingProduct);
        Assertions.assertEquals(1, category.getProducts().size());
    }

}
