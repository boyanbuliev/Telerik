package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements Category {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    private static final String INVALID_NAME_MESSAGE = "Category name should be between 3 and 10 symbols.";

    private String name;
    private final List<Product> products;

    public CategoryImpl(String name) {
        setName(name);
        products = new ArrayList<>();
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

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public String print() {
        if (products.size() == 0) {
            return String.format(
                    "#Category: %s%n" +
                    " #No product in this category",
                    name);
        }

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(String.format("#Category: %s%n", name));

        for (Product product : products) {
            strBuilder.append(product.print());
            strBuilder.append(String.format(" ===%n"));
        }

        return strBuilder.toString();
    }

}
