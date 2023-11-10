package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.ProductImpl;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductImplTests {
    //Which are the test cases?
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new ProductImpl("asdf", "sadf", 2.5, GenderType.MEN);
    }

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        Assertions.assertEquals("asdf", product.getName());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> new ProductImpl("a", "asdf", 2.5, GenderType.MEN));
    }

    @Test
    public void constructor_Should_InitializeBrand_When_ArgumentsAreValid() {
        Assertions.assertEquals("sadf", product.getBrand());
    }

    @Test
    public void constructor_Should_ThrowException_When_BrandIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> new ProductImpl("asdf", "b", 2.5, GenderType.MEN));
    }

    @Test
    public void constructor_Should_InitializePrice_When_ArgumentsAreValid() {
        Assertions.assertEquals(2.5, product.getPrice());
    }

    @Test
    public void constructor_Should_ThrowException_When_PriceIsNegative() {
        Assertions.assertThrows(InvalidUserInputException.class, () -> new ProductImpl("asdf", "basd", -1, GenderType.MEN));
    }
    @Test
    public void constructor_Should_InitializeGender_When_ArgumentsAreValid() {
        Assertions.assertEquals(GenderType.MEN, product.getGender());
    }

}
