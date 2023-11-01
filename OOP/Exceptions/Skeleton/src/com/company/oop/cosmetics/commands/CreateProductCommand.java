package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.utils.ValidationHelpers;
import com.company.oop.cosmetics.utils.exceptions.InvalidGenderException;
import com.company.oop.cosmetics.utils.exceptions.InvalidNumberException;
import com.company.oop.cosmetics.utils.exceptions.ItemAlreadyExistsException;

import java.util.List;

public class CreateProductCommand implements Command {

    private static final String PRODUCT_CREATED = "Product with name %s was created!";
    private static final String PRODUCT_ALREADY_EXISTS = "Product %s already exist.";
    private static final String PRICE_NOT_REAL_NUMBER = "Third parameter should be price (real number).";
    private static final String GENDER_NOT_REAL = "Forth parameter should be one of Men, Women or Unisex.";

    private final ProductRepository productRepository;

    public CreateProductCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, 4, "CreateProduct");

        String name = parameters.get(0);
        String brand = parameters.get(1);
        double price;
        try {
            price = Double.parseDouble(parameters.get(2));
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(PRICE_NOT_REAL_NUMBER);
        }
        GenderType gender;
        try {
            gender = GenderType.valueOf(parameters.get(3).toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidGenderException(GENDER_NOT_REAL);
        }
        return createProduct(name, brand, price, gender);
    }

    private String createProduct(String name, String brand, double price, GenderType gender) {
        if (productRepository.productExist(name)) {
            throw new ItemAlreadyExistsException(String.format(PRODUCT_ALREADY_EXISTS, name));
        }

        productRepository.createProduct(name, brand, price, gender);

        return String.format(PRODUCT_CREATED, name);
    }

}
