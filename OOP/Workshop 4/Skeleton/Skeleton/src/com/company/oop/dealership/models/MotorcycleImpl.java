package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

import static java.lang.String.format;

public class MotorcycleImpl extends VehicleImpl implements Motorcycle {

    public static final int CATEGORY_LEN_MIN = 3;
    public static final int CATEGORY_LEN_MAX = 10;
    private static final String CATEGORY_LEN_ERR = format(
            "Category must be between %d and %d characters long!",
            CATEGORY_LEN_MIN,
            CATEGORY_LEN_MAX);

    private String category;

    public MotorcycleImpl(String make, String model, double price, String category) {
        super(make, model, price, 2, VehicleType.MOTORCYCLE);
        setCategory(category);
    }

    private void setCategory(String category) {
        ValidationHelpers.validateStringLength(category, CATEGORY_LEN_MIN, CATEGORY_LEN_MAX, CATEGORY_LEN_ERR);
        this.category = category;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%sCategory: %s", super.toString(), category);
    }
}