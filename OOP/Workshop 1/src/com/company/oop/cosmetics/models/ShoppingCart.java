package com.company.oop.cosmetics.models;

import java.util.ArrayList;

public class ShoppingCart {

    private final ArrayList<Product> products;

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
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

}
