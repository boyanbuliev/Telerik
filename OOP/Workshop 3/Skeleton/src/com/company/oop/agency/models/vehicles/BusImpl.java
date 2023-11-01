package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Bus;

public class BusImpl extends VehicleImpl implements Bus {

    private static final String INCORRECT_PASSENGERS_COUNT = "A bus cannot have less than 10 passengers or more than 50 passengers.";
    private static final int PASSENGER_MIN_VALUE = 10;
    private static final int PASSENGER_MAX_VALUE = 50;


    public BusImpl(int id, int passengerCapacity, double pricePerKilometer) {
        super(id, passengerCapacity, pricePerKilometer, VehicleType.LAND);
    }

    @Override
    protected void validatePassengerCapacity(double passengerCapacity) {
        super.checkValueInRange(passengerCapacity,PASSENGER_MIN_VALUE,PASSENGER_MAX_VALUE, INCORRECT_PASSENGERS_COUNT);
    }

    @Override
    public String getAsString() {
        return String.format("%s%s", "Bus", super.getAsString());
    }
}