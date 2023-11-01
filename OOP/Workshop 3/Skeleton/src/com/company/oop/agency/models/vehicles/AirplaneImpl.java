package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Airplane;

public class AirplaneImpl extends VehicleImpl implements Airplane {

    private boolean hasFreeFood;

    public AirplaneImpl(int id, int passengerCapacity, double pricePerKilometer, boolean hasFreeFood) {
        super(id, passengerCapacity, pricePerKilometer, VehicleType.AIR);
        this.hasFreeFood = hasFreeFood;
    }

    @Override
    public boolean hasFreeFood() {
        return hasFreeFood;
    }

    private void setHasFreeFood(boolean hasFreeFood) {
        this.hasFreeFood = hasFreeFood;
    }

    @Override
    public String getAsString() {
        return String.format("%s%sHas free food: %s%n", "Airplane", super.getAsString(), hasFreeFood);
    }
}