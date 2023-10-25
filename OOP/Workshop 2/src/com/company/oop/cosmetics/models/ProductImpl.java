package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;

public abstract class ProductImpl implements Product {
    private String name;
    private String brandName;
    private double price;
    private GenderType genderType;

    public ProductImpl(String name, String brandName, double price, GenderType genderType) {
        setName(name);
        setBrandName(brandName);
        setPrice(price);
        setGenderType(genderType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price should be non negative.");
        }
        this.price = price;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }


    public String print() {
        return String.format("#%s %s%n #Price: $%.2f%n #Gender: %s%n", getName(), getBrandName(), getPrice(), getGenderType());
    }
}
