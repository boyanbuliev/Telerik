package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddToShoppingCartCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.oop.cosmetics.tests.models.CategoryTests.INVALID_CATEGORY_NAME;
import static com.company.oop.cosmetics.tests.models.CategoryTests.addInitializedCategoryToRepo;
import static com.company.oop.cosmetics.tests.utils.TestUtilities.getList;

public class CreateCategoryCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private Command createCategoryCommand;
    private CosmeticsRepository cosmeticsRepository;


    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        createCategoryCommand = new AddToShoppingCartCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CategoryNameIsInvalid() {
        // Arrange
        List<String> params = List.of(INVALID_CATEGORY_NAME);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCategoryCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CategoryWithSameNameExists() {
        // Arrange
        Category category = addInitializedCategoryToRepo(cosmeticsRepository);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createCategoryCommand.execute(List.of(category.getName())));
    }

    @Test
    public void should_CreateCategory_When_ArgumentsAreValid() {
        // Arrange
        addInitializedCategoryToRepo(cosmeticsRepository);

        // Act, Assert
        Assertions.assertEquals(1, cosmeticsRepository.getCategories().size());
    }
}
