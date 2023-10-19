package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddToShoppingCartCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.cosmetics.tests.models.ProductTests.*;
import static com.company.oop.cosmetics.tests.utils.TestUtilities.getList;

public class CreateProductCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private Command createProductCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        createProductCommand = new AddToShoppingCartCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createProductCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_PriceInvalid() {
        //Arrange
        List<String> parameters = List.of(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                "Invalid price",
                GenderType.MEN.toString());
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createProductCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_GenderInvalid() {
        //Arrange
        List<String> parameters = List.of(
                VALID_PRODUCT_NAME,
                VALID_BRAND_NAME,
                "1",
                "Invalid gender");
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createProductCommand.execute(parameters));
    }


    @Test
    public void should_ThrowException_When_ProductNameIsInvalid() {
        // Arrange
        List<String> params = List.of(INVALID_PRODUCT_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createProductCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductWithSameNameExists() {
        // Arrange
        Product product = addInitializedProductToRepo(cosmeticsRepository);
        List<String> params = List.of(
                product.getName(),
                product.getBrand(),
                String.valueOf(product.getPrice()),
                product.getGender().toString()
        );

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createProductCommand.execute(params));
    }

    @Test
    public void should_CreateProduct_When_ArgumentsAreValid() {
        // Arrange
        addInitializedProductToRepo(cosmeticsRepository);

        // Act, Assert
        Assertions.assertEquals(1, cosmeticsRepository.getProducts().size());
    }
}
