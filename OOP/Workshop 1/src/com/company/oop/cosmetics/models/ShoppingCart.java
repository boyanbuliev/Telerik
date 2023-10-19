package com.company.oop.cosmetics.models;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ShoppingCart {

    private ArrayList<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (!products.remove(product))
            throw new IllegalArgumentException(String.format("Shopping cart does not contain product with name %s!", product.getName()));
    }

    public boolean containsProduct(Product product) {
        return products.contains(product);
    }

    public double totalPrice() {
        return products.stream().map(Product::getPrice).mapToDouble(Double::doubleValue).sum();
    }

}
