package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Vehicle;
import com.company.oop.agency.utils.ValidationHelper;

public abstract class VehicleImpl implements Vehicle {

    private static final String INCORRECT_PASSENGERS_COUNT = "A vehicle with less than 1 passenger or more than 800 passengers cannot exist!";
    private static final String INCORRECT_PRICE_PER_KM = "A vehicle with a price per kilometer lower than $0.10 or higher than $2.50 cannot exist!";

    private static final int MIN_PASSENGERS = 1;
    private static final int MAX_PASSENGERS = 800;
    private static final double MIN_PRICE = 0.1;
    private static final double MAX_PRICE = 2.5;


    private int id;
    private int passengerCapacity;
    private double pricePerKilometer;
    private VehicleType type;

    public VehicleImpl(int id, int passengerCapacity, double pricePerKilometer, VehicleType type) {
        this.id = id;
        setPassengerCapacity(passengerCapacity);
        setPricePerKilometer(pricePerKilometer);
        this.type = type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public VehicleType getType() {
        return type;
    }

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setPassengerCapacity(int passengerCapacity) {
        validatePassengerCapacity(passengerCapacity);
        this.passengerCapacity = passengerCapacity;
    }

    private void setPricePerKilometer(double pricePerKilometer) {
        checkValueInRange(pricePerKilometer, MIN_PRICE, MAX_PRICE, INCORRECT_PRICE_PER_KM);
        this.pricePerKilometer = pricePerKilometer;
    }

    private void setType(VehicleType type) {
        this.type = type;
    }

    protected void validatePassengerCapacity(double passengerCapacity) {
        checkValueInRange(passengerCapacity, MIN_PASSENGERS, MAX_PASSENGERS, INCORRECT_PASSENGERS_COUNT);
    }

    protected void checkValueInRange(double value, double min, double max, String errorMessage) {
        ValidationHelper.validateValueInRange(value, min, max, errorMessage);
    }

    @Override
    public String getAsString() {
        return String.format(" ----%nPassenger capacity: %d%nPrice per kilometer: %.2f%nVehicle type: %s%n", passengerCapacity, pricePerKilometer, type);
    }
}
