package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public abstract class VehicleImpl implements Vehicle {
    public static final int MAKE_NAME_LEN_MIN = 2;
    public static final int MAKE_NAME_LEN_MAX = 15;
    private static final String MAKE_NAME_LEN_ERR = format(
            "Make must be between %s and %s characters long!",
            MAKE_NAME_LEN_MIN,
            MAKE_NAME_LEN_MAX);
    public static final int MODEL_NAME_LEN_MIN = 1;
    public static final int MODEL_NAME_LEN_MAX = 15;
    private static final String MODEL_NAME_LEN_ERR = format(
            "Model must be between %s and %s characters long!",
            MODEL_NAME_LEN_MIN,
            MODEL_NAME_LEN_MAX);
    private static final double MIN_PRICE = 0.0;
    private static final double MAX_PRICE = 1000000.0;
    public static final double PRICE_VAL_MIN = 0;
    public static final double PRICE_VAL_MAX = 1000000;
    private static final String PRICE_VAL_ERR = format(
            "Price must be between %.1f and %.1f!",
            PRICE_VAL_MIN,
            PRICE_VAL_MAX);
    private static final String BASE_VEHICLE_PRINT = "%s:%nMake: %s%nModel: %s%nWheels: %d%nPrice: $%s%n";

    private String make;
    private String model;
    private double price;
    private final int wheels;
    private final VehicleType vehicleType;
    private final List<Comment> comments;


    public VehicleImpl(String make, String model, double price, int wheels, VehicleType vehicleType) {
        setMake(make);
        setModel(model);
        setPrice(price);
        this.wheels = wheels;
        this.vehicleType = vehicleType;
        this.comments = new ArrayList<>();
    }

    private void setMake(String make) {
        ValidationHelpers.validateStringLength(make, MAKE_NAME_LEN_MIN, MAKE_NAME_LEN_MAX, MAKE_NAME_LEN_ERR);
        this.make = make;
    }

    private void setModel(String model) {
        ValidationHelpers.validateStringLength(model, MODEL_NAME_LEN_MIN, MODEL_NAME_LEN_MAX, MODEL_NAME_LEN_ERR);
        this.model = model;
    }

    private void setPrice(double price) {
        ValidationHelpers.validateDecimalRange(price, MIN_PRICE, MAX_PRICE, PRICE_VAL_ERR);
        this.price = price;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getWheels() {
        return wheels;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public String toString() {
        return String.format(BASE_VEHICLE_PRINT,
                vehicleType, make, model, wheels, FormattingHelpers.removeTrailingZerosFromDouble(price));
    }
}
