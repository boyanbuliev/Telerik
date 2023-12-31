package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public abstract class ProductImpl implements Product {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_NAME_MIN_LENGTH = 2;
    public static final int BRAND_NAME_MAX_LENGTH = 10;

    private String name;
    private String brandName;
    private double price;
    private GenderType genderType;

    protected ProductImpl(String name, String brandName, double price, GenderType genderType) {
        setName(name);
        setBrandName(brandName);
        setPrice(price);
        setGenderType(genderType);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, "Name");
        this.name = name;
    }

    @Override
    public String getBrandName() {
        return brandName;
    }

    private void setBrandName(String brandName) {
        ValidationHelpers.validateStringLength(brandName, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH, "Brand");
        this.brandName = brandName;
    }


    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        ValidationHelpers.validateMinimumValue(price,0,"Price");
        this.price = price;
    }

    @Override
    public GenderType getGenderType() {
        return genderType;
    }

    private void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    @Override
    public String print() {
        return String.format("#%s %s%n #Price: $%.2f%n #Gender: %s%n", getName(), getBrandName(), getPrice(), getGenderType());
    }
}
