package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddProductToCategoryCommand;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddProductToCategoryCommandTests {

    private ProductRepository productRepository;
    private AddProductToCategoryCommand command;
    private List<String> list;

    @BeforeEach

    public void setUp() {
        productRepository = new ProductRepositoryImpl();
        command = new AddProductToCategoryCommand(productRepository);
        productRepository.createCategory("Category");
        productRepository.createProduct("Product", "sadf", 2.5, GenderType.MEN);
        list = new ArrayList<>();
        list.add("Category");
        list.add("Product");

    }

    @Test
    public void execute_Should_AddProductToCategory_When_ValidParameters() {
        Assertions.assertEquals("Product Product added to category Category!", command.execute(list));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        list = new ArrayList<>();
        Assertions.assertThrows(InvalidUserInputException.class, () -> command.execute(list));

    }

}
