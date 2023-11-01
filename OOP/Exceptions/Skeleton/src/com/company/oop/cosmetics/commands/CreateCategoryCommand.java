package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.utils.ValidationHelpers;
import com.company.oop.cosmetics.utils.exceptions.ItemAlreadyExistsException;

import java.util.List;

public class CreateCategoryCommand implements Command {

    private static final String CATEGORY_CREATED = "Category with name %s was created!";
    private static final String CATEGORY_ALREADY_EXISTS = "Category %s already exist.";

    private final ProductRepository productRepository;

    public CreateCategoryCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, 1, "CreateCategory");

        String categoryName = parameters.get(0);

        return createCategory(categoryName);
    }

    private String createCategory(String categoryName) {
        if (productRepository.categoryExist(categoryName)) {
            throw new ItemAlreadyExistsException(String.format(CATEGORY_ALREADY_EXISTS, categoryName));
        }
        productRepository.createCategory(categoryName);

        return String.format(CATEGORY_CREATED, categoryName);
    }

}
