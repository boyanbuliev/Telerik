package com.company.oop.cosmetics.tests.core;

import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.tests.models.CategoryTests;
import com.company.oop.cosmetics.tests.models.ShampooTests;
import com.company.oop.cosmetics.tests.models.ToothpasteTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CosmeticsRepositoryImplTests {

    CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void beforeEach() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
    }

    @Test
    public void constructor_Should_InitializeAllCollections() {
        // Arrange, Act, Assert
        Assertions.assertAll(
                () -> Assertions.assertNotNull(cosmeticsRepository.getProducts()),
                () -> Assertions.assertNotNull(cosmeticsRepository.getCategories())
        );
    }

    @Test
    public void constructor_Should_InitializeShoppingCart() {
        // Arrange, Act, Assert
        Assertions.assertNotNull(cosmeticsRepository.getShoppingCart());
    }

    @Test
    public void getCategories_Should_ReturnCopyOfCollection() {
        // Arrange
        List<Category> categoriesReference = cosmeticsRepository.getCategories();
        List<Category> sameReference = cosmeticsRepository.getCategories();

        // Act, Assert
        Assertions.assertNotSame(categoriesReference, sameReference);
    }

    @Test
    public void getProducts_Should_ReturnCopyOfCollection() {
        // Arrange
        cosmeticsRepository.createShampoo(
                ToothpasteTests.VALID_TOOTHPASTE_NAME,
                ToothpasteTests.VALID_TOOTHPASTE_BRAND_NAME,
                10.75,
                GenderType.MEN,
                75,
                UsageType.MEDICAL);
        cosmeticsRepository.getProducts().clear();

        // Act, Assert
        Assertions.assertEquals(1, cosmeticsRepository.getProducts().size());
    }

    @Test
    public void findProductByName_Should_ThrowException_When_ProductDoesNotExist() {
        //Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> cosmeticsRepository.findProductByName(ShampooTests.VALID_SHAMPOO_NAME));
    }

    @Test
    public void findProductByName_Should_ReturnProduct_When_ProductExists() {
        //Arrange
        Product product = ShampooTests.addInitializedShampooToRepository(cosmeticsRepository);

        //Act, Assert
        assertNotNull(cosmeticsRepository.findProductByName(product.getName()));
    }

    @Test
    public void findCategoryByName_Should_ThrowException_When_CategoryDoesNotExist() {
        //Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> cosmeticsRepository.findCategoryByName(CategoryTests.VALID_CATEGORY_NAME));
    }

    @Test
    public void findCategoryByName_Should_ReturnProduct_When_CategoryExists() {
        //Arrange
        Category category = CategoryTests.addInitializedCategoryToRepository(cosmeticsRepository);

        //Act, Assert
        assertNotNull(cosmeticsRepository.findCategoryByName(category.getName()));
    }

    @Test
    public void createShampoo_Should_AddShampooToList() {
        //Arrange, Act, Assert
        assertAll(
                () -> assertDoesNotThrow(() -> cosmeticsRepository.createShampoo(
                        ShampooTests.VALID_SHAMPOO_NAME,
                        ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        5,
                        UsageType.MEDICAL)),
                () -> assertEquals(1, cosmeticsRepository.getProducts().size())
        );
    }

    @Test
    public void createToothpaste_Should_AddToothpasteToList() {
        //Arrange, Act, Assert
        assertAll(
                () -> assertDoesNotThrow(() -> cosmeticsRepository.createToothpaste(
                        ToothpasteTests.VALID_TOOTHPASTE_NAME,
                        ToothpasteTests.VALID_TOOTHPASTE_BRAND_NAME,
                        10.75,
                        GenderType.MEN,
                        List.of("test1,test2,test3"))),
                () -> assertEquals(1, cosmeticsRepository.getProducts().size())
        );
    }

//    @Test
//    public void createCream_Should_AddCreamToList() {
//        //Arrange, Act, Assert
//        assertAll(
//                () -> assertDoesNotThrow(() -> cosmeticsRepository.createCream(
//                        VALID_CREAM_NAME,
//                        VALID_CREAM_BRAND_NAME,
//                        10.75,
//                        GenderType.MEN,
//                        ScentType.LAVENDER)),
//                () -> assertEquals(1, cosmeticsRepository.getProducts().size())
//        );
//    }

    @Test
    public void createCategory_Should_AddCategoryToList() {
        //Arrange, Act
        cosmeticsRepository.createCategory(CategoryTests.VALID_CATEGORY_NAME);

        // Assert
        assertEquals(1, cosmeticsRepository.getCategories().size());
    }

    @Test
    public void categoryExists_Should_ReturnFalse_WhenCategoryDoesNotExist() {
        // Arrange, Act, Assert
        assertFalse(cosmeticsRepository.categoryExist(CategoryTests.VALID_CATEGORY_NAME));
    }

    @Test
    public void categoryExists_Should_ReturnTrue_WhenCategoryExists() {
        // Arrange
        String categoryName = CategoryTests.VALID_CATEGORY_NAME;
        cosmeticsRepository.createCategory(categoryName);

        // Act, Assert
        assertTrue(cosmeticsRepository.categoryExist(categoryName));
    }

    @Test
    public void productExists_Should_ReturnFalse_WhenProductDoesNotExist() {
        // Arrange, Act, Assert
        assertFalse(cosmeticsRepository.productExist(ShampooTests.VALID_SHAMPOO_NAME));
    }

    @Test
    public void productExists_Should_ReturnTrue_WhenProductExists() {
        // Arrange
        cosmeticsRepository.createShampoo(
                ShampooTests.VALID_SHAMPOO_NAME,
                ShampooTests.VALID_SHAMPOO_BRAND_NAME,
                10.75,
                GenderType.MEN,
                75,
                UsageType.MEDICAL);

        // Act, Assert
        assertTrue(cosmeticsRepository.productExist(ShampooTests.VALID_SHAMPOO_NAME));
    }
}
