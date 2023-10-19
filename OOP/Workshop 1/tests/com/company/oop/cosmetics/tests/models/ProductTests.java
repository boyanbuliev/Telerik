package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.Product;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ProductTests {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_MIN_LENGTH = 2;
    public static final int BRAND_MAX_LENGTH = 10;

    public static final String INVALID_PRODUCT_NAME = TestUtilities.getString(NAME_MAX_LENGTH + 1);
    public static final String VALID_PRODUCT_NAME = TestUtilities.getString(NAME_MIN_LENGTH + 1);
    public static final String INVALID_BRAND_NAME = TestUtilities.getString(BRAND_MAX_LENGTH + 1);
    public static final String VALID_BRAND_NAME = TestUtilities.getString(BRAND_MIN_LENGTH + 1);



    @Test
    public void construct_Should_ThrowException_When_ProductNameIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product(
                INVALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                1,
                GenderType.MEN));
    }

    @Test
    public void construct_Should_ThrowException_When_BrandNameIsInvalid() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product(
                VALID_PRODUCT_NAME,
                INVALID_BRAND_NAME,
                1,
                GenderType.MEN));
    }

    @Test
    public void construct_Should_ThrowException_When_PriceIsNegative() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Product(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                -1,
                GenderType.MEN));
    }

    @Test
    public void construct_Should_CreateProduct_WhenValidValuesArePassed() {
        // Arrange, Act, Assert
        Assertions.assertDoesNotThrow(ProductTests::initializeTestProduct);
    }

    public static Product addInitializedProductToRepo(CosmeticsRepository cosmeticsRepository) {
        cosmeticsRepository.createProduct(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                1,
                GenderType.MEN);
        return cosmeticsRepository.findProductByName(VALID_PRODUCT_NAME);
    }

    public static Product initializeTestProduct() {
        return new Product(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                1,
                GenderType.MEN);
    }
}
