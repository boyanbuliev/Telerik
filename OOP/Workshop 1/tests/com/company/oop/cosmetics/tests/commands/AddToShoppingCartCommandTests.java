package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddToShoppingCartCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.cosmetics.tests.models.ProductTests.VALID_PRODUCT_NAME;
import static com.company.oop.cosmetics.tests.models.ProductTests.addInitializedProductToRepo;
import static com.company.oop.cosmetics.tests.utils.TestUtilities.getList;


public class AddToShoppingCartCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private Command addToSHoppingCartCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        addToSHoppingCartCommand = new AddToShoppingCartCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addToSHoppingCartCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductNameIsInvalid() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addToSHoppingCartCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductDoesNotExist() {
        // Arrange
        List<String> params = List.of(VALID_PRODUCT_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addToSHoppingCartCommand.execute(params));
    }

    @Test
    public void should_AddToShoppingCart_When_ArgumentsAreValid() {
        // Arrange
        Product product = addInitializedProductToRepo(cosmeticsRepository);
        List<String> params = List.of(product.getName());
        addToSHoppingCartCommand.execute(params);

        // Act, Assert
        Assertions.assertEquals(1, cosmeticsRepository.getShoppingCart().getProducts().size());
    }
}
