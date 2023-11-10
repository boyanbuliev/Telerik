package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateProductCommand;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateProductCommandTests {

    private ProductRepository repository;
    private CreateProductCommand command;
    private List<String> list;

    @BeforeEach
    public void setUp() {
        repository = new ProductRepositoryImpl();
        command = new CreateProductCommand(repository);
        list = new ArrayList<>();
        list.add("Gosho");
        list.add("Peshov");
        list.add("2.5");
        list.add("men");
    }

    @Test
    public void execute_Should_AddNewProductToRepository_When_ValidParameters() {
        Assertions.assertEquals("Product with name Gosho was created!", command.execute(list));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        list = new ArrayList<>();
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(list));
    }


    @Test
    public void execute_Should_ThrowException_When_DuplicateProductName() {
        command.execute(list);
        Assertions.assertThrows(DuplicateEntityException.class, () -> command.execute(list));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidPrice() {
        list.set(2, "Ivan");
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(list));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidGender() {
        list.set(3, "Petkan");
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(list));
    }
}
