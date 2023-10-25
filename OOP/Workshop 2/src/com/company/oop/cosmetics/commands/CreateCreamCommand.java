package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

public class CreateCreamCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private final CosmeticsRepository cosmeticsRepository;

    public CreateCreamCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return createCream(parameters);
    }

    private String createCream(List<String> parameters) {
        String productName = parameters.get(0);
        String brand = parameters.get(1);
        double price = ParsingHelpers.tryParseDouble(parameters.get(2), ParsingHelpers.INVALID_PRICE);
        GenderType genderType = ParsingHelpers.tryParseGender(parameters.get(3));
        ScentType scent = ParsingHelpers.tryParseScent(parameters.get(4));
        if (cosmeticsRepository.productExist(productName)) {
            throw new IllegalArgumentException(String.format(ParsingHelpers.PRODUCT_NAME_ALREADY_EXISTS, "Cream", productName));
        }
        cosmeticsRepository.createCream(productName, brand, price, genderType, scent);
        return String.format(ParsingHelpers.PRODUCT_CREATED, "Cream", productName);
    }

}
