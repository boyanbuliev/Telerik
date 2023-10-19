package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.RemoveFromShoppingCartCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.Product;
import com.company.oop.cosmetics.tests.models.ProductTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.cosmetics.tests.models.CategoryTests.VALID_CATEGORY_NAME;
import static com.company.oop.cosmetics.tests.models.ProductTests.INVALID_PRODUCT_NAME;
import static com.company.oop.cosmetics.tests.utils.TestUtilities.getList;

public class RemoveFromShoppingCartCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private Command removeFromShoppingCartCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        removeFromShoppingCartCommand = new RemoveFromShoppingCartCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromShoppingCartCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductNameIsInvalid() {
        // Arrange
        List<String> params = List.of(INVALID_PRODUCT_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromShoppingCartCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductDoesNotExist() {
        // Arrange
        Product product = ProductTests.addInitializedProductToRepo(cosmeticsRepository);
        List<String> params = List.of(VALID_CATEGORY_NAME, product.getName());

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromShoppingCartCommand.execute(params));
    }

    @Test
    public void should_RemoveFromShoppingCart_When_ArgumentsAreValid() {
        // Arrange
        Product product = ProductTests.addInitializedProductToRepo(cosmeticsRepository);
        cosmeticsRepository.getShoppingCart().addProduct(product);
        List<String> params = List.of(product.getName());
        removeFromShoppingCartCommand.execute(params);

        // Act, Assert
        Assertions.assertEquals(0, cosmeticsRepository.getShoppingCart().getProducts().size());
    }
}
