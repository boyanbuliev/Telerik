package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.ArrayList;

public class Category {
    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 15;

    private String name;
    private final ArrayList<Product> products;

    public Category(String name) {
        setName(name);
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,
                String.format(Product.INVALID_LENGTH_ERROR, "Name", NAME_MIN_LENGTH, NAME_MAX_LENGTH));
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (!products.remove(product))
            throw new IllegalArgumentException("Product not found in category.");
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("#Category: %s%n", name));
        if (!products.isEmpty()) {
            for (Product product : products) {
                sb.append(product.print()).append("===");
            }
        } else {
            sb.append(" #No product in this category");
        }

        return sb.toString();
    }

}
