package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.Category;
import com.company.oop.cosmetics.models.Product;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTests {

    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String INVALID_CATEGORY_NAME = TestUtilities.getString(NAME_MAX_LENGTH + 1);
    public static final String VALID_CATEGORY_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);

    @Test
    public void add_Should_AddProduct_When_ProductIsValid() {
        // Arrange
        Category category = initializeTestCategory();
        Product productToAdd = ProductTests.initializeTestProduct();

        // Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> category.addProduct(productToAdd)),
                () -> Assertions.assertEquals(1, category.getProducts().size())
        );
    }

    @Test
    public void construct_Should_ThrowException_When_CategoryNameIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Category(INVALID_CATEGORY_NAME));
    }

    @Test
    public void construct_Should_CreateCategory_When_NameIsValid() {
        // Arrange, Act, Assert
        Assertions.assertDoesNotThrow(() -> new Category(VALID_CATEGORY_NAME));
    }

    @Test
    public void construct_Should_InitializeNewListOfProducts_When_CategoryIsCreated() {
        // Arrange, Act
        Category category = initializeTestCategory();

        // Assert
        Assertions.assertNotNull(category.getProducts());
    }

    @Test
    public void remove_Should_RemoveProduct_When_ProductIsValid() {
        // Arrange, Act
        Category category = initializeTestCategory();
        Product productToAdd = ProductTests.initializeTestProduct();
        category.addProduct(productToAdd);

        // Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertDoesNotThrow(() -> category.removeProduct(ProductTests.initializeTestProduct())),
                () -> Assertions.assertEquals(0, category.getProducts().size())
        );
    }

    @Test
    public void remove_Should_ThrowException_When_ProductNotFound() {
        // Arrange, Act
        Category category = initializeTestCategory();
        Product productToRemove = ProductTests.initializeTestProduct();

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> category.removeProduct(productToRemove));
    }

    public static Category addInitializedCategoryToRepo(CosmeticsRepository cosmeticsRepository) {
        cosmeticsRepository.createCategory(VALID_CATEGORY_NAME);
        return cosmeticsRepository.findCategoryByName(VALID_CATEGORY_NAME);
    }

    public static Category initializeTestCategory() {
        return new Category(VALID_CATEGORY_NAME);
    }
}
