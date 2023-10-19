package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.Objects;

public class Product {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_MIN_LENGTH = 2;
    public static final int BRAND_MAX_LENGTH = 10;

    private String name;
    private String brand;
    private double price;
    private GenderType gender;


    public Product(String name, String brand, double price, GenderType gender) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        setGender(gender);
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price should be non negative.");
        }
        this.price = price;
    }

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, "Name should be between 3 and 10 symbols.");
        this.name = name;
    }

    public void setBrand(String brand) {
        ValidationHelpers.validateStringLength(brand, BRAND_MIN_LENGTH, BRAND_MAX_LENGTH, "Brand should be between 2 and 10 symbols.");
        this.brand = brand;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public GenderType getGender() {
        return gender;
    }

    public String print() {
        return String.format("#%s %s%n #Price: $%.2f%n #Gender: %s%n", name, brand, price, gender);
        // Format:
        //" #[Name] [Brand]
        // #Price: [Price]
        // #Gender: [Gender]
        // ==="
    }

    @Override
    public boolean equals(Object p) {
        if (this == p) return true;
        if (p == null || getClass() != p.getClass()) return false;
        Product product = (Product) p;
        return Double.compare(this.getPrice(), product.getPrice()) == 0 &&
                Objects.equals(this.getName(), product.getName()) &&
                Objects.equals(this.getBrand(), product.getBrand()) &&
                this.getGender() == product.getGender();
    }

}
