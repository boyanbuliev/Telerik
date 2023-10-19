package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.RemoveFromCategoryCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.Category;
import com.company.oop.cosmetics.models.Product;
import com.company.oop.cosmetics.tests.models.ProductTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.cosmetics.tests.models.CategoryTests.*;
import static com.company.oop.cosmetics.tests.models.ProductTests.VALID_PRODUCT_NAME;
import static com.company.oop.cosmetics.tests.utils.TestUtilities.getList;

public class RemoveFromCategoryCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private Command removeFromCategoryCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        removeFromCategoryCommand = new RemoveFromCategoryCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CategoryNameIsInvalid() {
        // Arrange
        List<String> params = List.of(INVALID_CATEGORY_NAME, VALID_PRODUCT_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CategoryDoesNotExist() {
        // Arrange
        Product product = ProductTests.addInitializedProductToRepo(cosmeticsRepository);
        List<String> params = List.of(VALID_CATEGORY_NAME, product.getName());

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductDoesNotExist() {
        // Arrange
        Category category = addInitializedCategoryToRepo(cosmeticsRepository);
        List<String> params = List.of(category.getName(), VALID_PRODUCT_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeFromCategoryCommand.execute(params));
    }

    @Test
    public void should_RemoveFromCategory_When_ArgumentsAreValid() {
        // Arrange
        Product product = ProductTests.addInitializedProductToRepo(cosmeticsRepository);
        Category category = addInitializedCategoryToRepo(cosmeticsRepository);
        category.addProduct(product);
        List<String> params = List.of(category.getName(), product.getName());
        removeFromCategoryCommand.execute(params);

        // Act, Assert
        Assertions.assertEquals(0, category.getProducts().size());
    }
}
