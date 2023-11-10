package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.ShowCategoryCommand;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowCategoryCommandTests {

    private ProductRepository repository;
    private ShowCategoryCommand command;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        repository = new ProductRepositoryImpl();
        command = new ShowCategoryCommand(repository);
        repository.createCategory("Gosho");
        list = new ArrayList<>();
        list.add("Gosho");
    }

    @Test
    public void execute_Should_ShowCategoryFromRepository_When_ValidParameters() {
        command.execute(list);
        Assertions.assertEquals(1, repository.getCategories().size());
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        list = new ArrayList<>();
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(list));
    }
}
