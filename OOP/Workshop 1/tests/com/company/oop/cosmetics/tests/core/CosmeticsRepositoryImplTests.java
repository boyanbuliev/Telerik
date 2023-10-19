package com.company.oop.cosmetics.tests.core;

import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.Category;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.cosmetics.tests.models.CategoryTests.VALID_CATEGORY_NAME;
import static com.company.oop.cosmetics.tests.models.ProductTests.VALID_BRAND_NAME;
import static com.company.oop.cosmetics.tests.models.ProductTests.VALID_PRODUCT_NAME;

public class CosmeticsRepositoryImplTests {

    private CosmeticsRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new CosmeticsRepositoryImpl();
    }

    @Test
    public void constructor_Should_InitializeAllCollections() {
        // Arrange, Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertNotNull(repository.getProducts()),
                () -> Assertions.assertNotNull(repository.getCategories())
        );
    }

    @Test
    public void constructor_Should_InitializeShoppingCart() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(repository.getShoppingCart());
    }

    @Test
    public void getCategories_Should_ReturnCopyOfCollection() {
        // Arrange
        List<Category> categoriesReference = repository.getCategories();
        List<Category> sameReference = repository.getCategories();

        // Act, Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void getProducts_Should_ReturnCopyOfCollection() {
        // Arrange
        repository.createProduct(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                2, GenderType.WOMEN);
        repository.getProducts().clear();

        // Act, Assert
        Assertions.assertEquals(1, repository.getProducts().size());
    }

    @Test
    public void categoryExists_Should_ReturnTrue_WhenCategoryExists() {
        // Arrange
        String categoryName = VALID_CATEGORY_NAME;
        repository.createCategory(categoryName);

        // Act, Assert
        Assertions.assertTrue(repository.categoryExist(categoryName));
    }

    @Test
    public void productExists_Should_ReturnTrue_WhenProductExists() {
        // Arrange
        repository.createProduct(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                2, GenderType.WOMEN);

        // Act, Assert
        Assertions.assertTrue(repository.productExist(VALID_PRODUCT_NAME));
    }

    @Test
    public void createProduct_Should_CreateSuccessfully_WhenArgumentsAreValid() {
        // Arrange
        repository.createProduct(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                2, GenderType.WOMEN);

        // Act, Assert
        Assertions.assertEquals(1, repository.getProducts().size());
    }

    @Test
    public void createCategory_Should_CreateSuccessfully_WhenArgumentsAreValid() {
        // Arrange
        repository.createCategory(VALID_CATEGORY_NAME);

        // Act, Assert
        Assertions.assertEquals(1, repository.getCategories().size());
    }

    @Test
    public void findCategoryByName_Should_ReturnCategory_IfExists() {
        // Arrange
        String categoryName = VALID_CATEGORY_NAME;
        repository.createCategory(categoryName);

        // Act
        Category found = repository.findCategoryByName(categoryName);

        // Assert
        Assertions.assertEquals(found.getName(), categoryName);
    }

    @Test
    public void findCategoryByName_Should_ThrowException_IfDoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> repository.findCategoryByName("name"));
    }

    @Test
    public void findProductByName_Should_ReturnCategory_IfExists() {
        // Arrange
        String productName = VALID_PRODUCT_NAME;
        repository.createProduct(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                2, GenderType.WOMEN);

        // Act
        Product found = repository.findProductByName(productName);

        // Assert
        Assertions.assertEquals(found.getName(), productName);
    }

    @Test
    public void findProductByName_Should_ThrowException_IfDoesNotExist() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> repository.findProductByName("name"));
    }

}
