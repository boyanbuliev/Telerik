package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Cream;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;

public class CreamImpl extends ProductImpl implements Cream {

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
    public String print() {
        return String.format("%s #Scent: %s%n", super.print(), scent);
    }
}
