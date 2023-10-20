package com.company.oop.cosmetics.models;

import java.util.ArrayList;

public class ShoppingCart {

    private final ArrayList<Product> products;
    public static final String PRODUCT_NOT_IN_SHOPPING_CART = "Shopping cart does not contain product with name %s!";

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
            throw new IllegalArgumentException(String.format(PRODUCT_NOT_IN_SHOPPING_CART, product.getName()));
    }

    public boolean containsProduct(Product product) {
        return products.contains(product);
    }

    public double totalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

}
