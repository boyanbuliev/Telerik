package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.MagicStrings;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class ShampooImpl {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_NAME_MIN_LENGTH = 2;
    public static final int BRAND_NAME_MAX_LENGTH = 10;

    private String name;
    private String brandName;
    private double price;
    private GenderType genderType;
    private int millilitres;
    private UsageType usageType;

    public ShampooImpl(String name, String brand, double price, GenderType genderType, int milliliters, UsageType usageType) {
        setName(name);
        setBrandName(brand);
        setPrice(price);
        setGenderType(genderType);
        setMillilitres(milliliters);
        setUsageType(usageType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH,
                String.format(MagicStrings.INCORRECT_STRING_LENGTH, "Name", NAME_MIN_LENGTH, NAME_MAX_LENGTH));
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        ValidationHelpers.validateStringLength(brandName, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH,
                String.format(MagicStrings.INCORRECT_STRING_LENGTH, "Brand", BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH));
        this.brandName = brandName;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException(MagicStrings.INCORRECT_PRICE);
        }
        this.price = price;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;

    }

    public int getMillilitres() {
        return millilitres;
    }

    public void setMillilitres(int millilitres) {
        this.millilitres = millilitres;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    @Override
    public String toString() {
        return String.format("#%s %s%n #Price: %.2f%n #Gender: %s%n #Milliliters: %s%n #Usage: %s%n===",
                getName(), getBrandName(), getPrice(), getGenderType(), getMillilitres(), getUsageType());
    }

    //This method should be uncommented when you are done with the class.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShampooImpl shampoo = (ShampooImpl) o;
        return getName().equals(shampoo.getName()) &&
                getBrandName().equals(shampoo.getBrandName()) &&
                getPrice() == shampoo.getPrice() &&
                getGenderType().equals(shampoo.getGenderType()) &&
                getMillilitres() == shampoo.getMillilitres() &&
                getUsageType().equals(shampoo.getUsageType());
    }
}
