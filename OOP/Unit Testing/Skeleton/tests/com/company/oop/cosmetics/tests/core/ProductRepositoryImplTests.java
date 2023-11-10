package com.company.oop.cosmetics.tests.core;

import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductRepositoryImplTests {

    ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepositoryImpl();
    }

    @Test
    public void constructor_Should_InitializeProducts() {
        Assertions.assertEquals(0, productRepository.getProducts().size());
    }

    @Test
    public void constructor_Should_InitializeCategories() {
        Assertions.assertEquals(0, productRepository.getCategories().size());

    }

    @Test
    public void getCategories_Should_ReturnCopyOfCollection() {
        List<Category> listReference = productRepository.getCategories();
        List<Category> listNotSameReference = productRepository.getCategories();
        Assertions.assertNotSame(listReference, listNotSameReference);
    }

    @Test
    public void getProducts_Should_ReturnCopyOfCollection() {
        List<Product> listReference = productRepository.getProducts();
        List<Product> listNotSameReference = productRepository.getProducts();
        Assertions.assertNotSame(listReference, listNotSameReference);
    }

    @Test
    public void categoryExists_Should_ReturnTrue_When_CategoryExists() {
        productRepository.createCategory("Asdf");
        Assertions.assertTrue(productRepository.categoryExist("Asdf"));
    }

    @Test
    public void categoryExists_Should_ReturnFalse_When_CategoryDoesNotExist() {
        productRepository.createCategory("Asdf");
        Assertions.assertFalse(productRepository.categoryExist("Sadf"));
    }

    @Test
    public void productExists_Should_ReturnTrue_When_ProductExists() {
        productRepository.createProduct("Asdf", "Sadf", 2.5, GenderType.MEN);
        Assertions.assertTrue(productRepository.productExist("Asdf"));
    }

    @Test
    public void productExists_Should_ReturnFalse_When_ProductDoesNotExist() {
        productRepository.createProduct("Asdf", "Sadf", 2.5, GenderType.MEN);
        Assertions.assertFalse(productRepository.productExist("Sadf"));

    }

    @Test
    public void createProduct_Should_AddToProducts_When_ArgumentsAreValid() {
        productRepository.createProduct("Asdf", "Sadf", 2.5, GenderType.MEN);
        Assertions.assertEquals(1, productRepository.getProducts().size());
    }

    @Test
    public void createCategory_Should_AddToCategories_When_ArgumentsAreValid() {
        productRepository.createCategory("Asdf");
        Assertions.assertEquals(1, productRepository.getCategories().size());
    }

    @Test
    public void findCategoryByName_Should_ReturnCategory_When_Exists() {
        productRepository.createCategory("Asdf");
        Assertions.assertNotNull(productRepository.findCategoryByName("Asdf"));

    }

    @Test
    public void findCategoryByName_Should_ThrowException_When_DoesNotExist() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> productRepository.findCategoryByName("asdf"));
    }

    @Test
    public void findProductByName_Should_ReturnProduct_When_Exists() {
        productRepository.createProduct("Asdf", "Sadf", 2.5, GenderType.MEN);
        Assertions.assertNotNull(productRepository.findProductByName("Asdf"));
    }

    @Test
    public void findProductByName_Should_ThrowException_When_DoesNotExist() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> productRepository.findProductByName("Asdf"));
    }

}
