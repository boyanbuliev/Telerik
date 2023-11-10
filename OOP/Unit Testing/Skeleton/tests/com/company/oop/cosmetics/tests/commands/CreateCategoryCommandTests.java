package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateCategoryCommandTests {
    // @BeforeEach may help here
    private List<String> list;
    private ProductRepository productRepository;
    private CreateCategoryCommand createCategoryCommand;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add("Category");
        productRepository = new ProductRepositoryImpl();
        createCategoryCommand = new CreateCategoryCommand(productRepository);
    }

    @Test
    public void execute_Should_AddNewCategoryToRepository_When_ValidParameters() {
        Assertions.assertEquals("Category with name Category was created!", createCategoryCommand.execute(list));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        list = new ArrayList<>();
        Assertions.assertThrows(InvalidUserInputException.class, () -> createCategoryCommand.execute(list));
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateCategoryName() {
        createCategoryCommand.execute(list);
        Assertions.assertThrows(DuplicateEntityException.class, () -> createCategoryCommand.execute(list));
    }

}
