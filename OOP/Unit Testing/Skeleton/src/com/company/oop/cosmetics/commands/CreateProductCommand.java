package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.models.GenderType;

import java.util.List;

public class CreateProductCommand implements Command {

    public static final int EXPECTED_PARAMETERS_COUNT = 4;
    private static final String INVALID_PARAMETERS_COUNT_MESSAGE = String.format(
            "CreateProduct command expects %d parameters.",
            EXPECTED_PARAMETERS_COUNT);

    private static final String PRODUCT_EXIST_MESSAGE = "Product %s already exist.";
    private static final String INVALID_PRICE_MESSAGE = "Third parameter should be price (real number).";
    private static final String INVALID_GENDER_MESSAGE = "Forth parameter should be one of Men, Women or Unisex.";
    private static final String PRODUCT_CREATED = "Product with name %s was created!";

    private final ProductRepository productRepository;

    public CreateProductCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        if (parameters.size() < EXPECTED_PARAMETERS_COUNT) {
            throw new InvalidUserInputException(INVALID_PARAMETERS_COUNT_MESSAGE);
        }

        String name = parameters.get(0);
        String brand = parameters.get(1);
        double price = tryParseDouble(parameters.get(2));
        GenderType gender = tryParseGenderType(parameters.get(3));

        return createProduct(name, brand, price, gender);
    }

    private String createProduct(String name, String brand, double price, GenderType gender) {
        if (productRepository.productExist(name)) {
            throw new DuplicateEntityException(String.format(PRODUCT_EXIST_MESSAGE, name));
        }

        productRepository.createProduct(name, brand, price, gender);

        return String.format(PRODUCT_CREATED, name);
    }

    private double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidUserInputException(INVALID_PRICE_MESSAGE);
        }
    }

    private GenderType tryParseGenderType(String value) {
        try {
            return GenderType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidUserInputException(INVALID_GENDER_MESSAGE);
        }
    }

}
