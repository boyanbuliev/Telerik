package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.contracts.Product;

public class ProductImpl implements Product {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    private static final String INVALID_NAME_MESSAGE = String.format(
            "Product name should be between %d and %d symbols.",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);

    public static final int BRAND_MIN_LENGTH = 2;
    public static final int BRAND_MAX_LENGTH = 10;
    private static final String INVALID_BRAND_MESSAGE = String.format(
            "Product brand should be between %d and %d symbols.",
            BRAND_MIN_LENGTH,
            BRAND_MAX_LENGTH);

    private static final String INVALID_PRICE_MESSAGE = "Price can't be negative.";

    private String name;
    private String brand;
    private double price;
    private final GenderType gender;

    public ProductImpl(String name, String brand, double price, GenderType gender) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.length() < NAME_MIN_LENGTH || name.length() > NAME_MAX_LENGTH) {
            throw new InvalidUserInputException(INVALID_NAME_MESSAGE);
        }
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    private void setBrand(String brand) {
        if (brand.length() < BRAND_MIN_LENGTH || brand.length() > BRAND_MAX_LENGTH) {
            throw new InvalidUserInputException(INVALID_BRAND_MESSAGE);
        }
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        if (price < 0) {
            throw new InvalidUserInputException(INVALID_PRICE_MESSAGE);
        }
        this.price = price;
    }

    public GenderType getGender() {
        return gender;
    }

    @Override
    public String print() {
        return String.format(
                "#%s %s%n" +
                " #Price: $%.2f%n" +
                " #Gender: %s%n",
                name,
                brand,
                price,
                gender);
    }

}
