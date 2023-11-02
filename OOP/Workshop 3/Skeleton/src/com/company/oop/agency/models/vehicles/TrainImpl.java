package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Train;

public class TrainImpl extends VehicleImpl implements Train {

    private static final String INCORRECT_PASSENGERS_COUNT = "A train cannot have less than 30 passengers or more than 150 passengers.";
    private static final String INCORRECT_CARTS_AMOUNT = "A train cannot have less than 1 cart or more than 15 carts.";
    private static final int PASSENGER_MIN_VALUE = 30;
    private static final int PASSENGER_MAX_VALUE = 150;
    private static final int CARTS_MIN_VALUE = 1;
    private static final int CARTS_MAX_VALUE = 15;

    private int carts;

    public TrainImpl(int id, int passengerCapacity, double pricePerKilometer, int carts) {
        super(id, passengerCapacity, pricePerKilometer, VehicleType.LAND);
        setCarts(carts);
    }

    @Override
    public int getCarts() {
        return carts;
    }

    private void setCarts(int carts) {
        super.checkValueInRange(carts, CARTS_MIN_VALUE, CARTS_MAX_VALUE, INCORRECT_CARTS_AMOUNT);
        this.carts = carts;
    }

    @Override
    public void validatePassengerCapacity(double passengerCapacity) {
        super.checkValueInRange(passengerCapacity, PASSENGER_MIN_VALUE, PASSENGER_MAX_VALUE, INCORRECT_PASSENGERS_COUNT);
    }

    @Override
    public String getAsString() {
        return String.format("Train%sCarts amount: %d%n", super.getAsString(), carts);
    }
}