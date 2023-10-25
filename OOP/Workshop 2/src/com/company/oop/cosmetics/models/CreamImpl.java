package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Cream;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class CreamImpl extends ProductImpl implements Cream {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_NAME_MIN_LENGTH = 2;
    public static final int BRAND_NAME_MAX_LENGTH = 10;

    private ScentType scent;

    public CreamImpl(String name, String brandName, double price, GenderType genderType, ScentType scent) {
        super(name, brandName, price, genderType);
        setScent(scent);
    }

    @Override
    public ScentType getScent() {
        return scent;
    }

    public void setScent(ScentType scent) {
        this.scent = scent;
    }

    @Override
    public void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, "Name");
        super.setName(name);
    }

    @Override
    public void setBrandName(String brandName) {
        ValidationHelpers.validateStringLength(brandName, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH, "Brand");
        super.setBrandName(brandName);
    }

    @Override
    public String print() {
        return String.format("%s #Scent: %s%n", super.print(), scent);
    }
}
