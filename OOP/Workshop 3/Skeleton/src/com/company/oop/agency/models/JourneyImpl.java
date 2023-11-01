package com.company.oop.agency.models;

import com.company.oop.agency.models.contracts.Journey;
import com.company.oop.agency.models.vehicles.contracts.Vehicle;
import com.company.oop.agency.utils.ValidationHelper;

public class JourneyImpl implements Journey {

    private static final String INVALID_STARTING_LOCATION = "The StartingLocation's length cannot be less than 5 or more than 25 symbols long.";
    private static final String INVALID_DESTINATION = "The Destination's length cannot be less than 5 or more than 25 symbols long.";
    private static final String INVALID_DISTANCE = "The Distance cannot be less than 5 or more than 5000 kilometers.";
    private static final int START_LOCATION_MIN_LENGTH = 5;
    private static final int START_LOCATION_MAX_LENGTH = 25;
    private static final int DESTINATION_MIN_LENGTH = 5;
    private static final int DESTINATION_MAX_LENGTH = 25;
    private static final int DISTANCE_MIN_VALUE = 5;
    private static final int DISTANCE_MAX_VALUE = 5000;

    private int id;
    private String startLocation;
    private String destination;
    private int distance;
    private Vehicle vehicle;

    public JourneyImpl(int id, String startLocation, String destination, int distance, Vehicle vehicle) {
        this.id = id;
        setStartLocation(startLocation);
        setDestination(destination);
        setDistance(distance);
        this.vehicle = vehicle;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String getStartLocation() {
        return startLocation;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public double calculateTravelCosts() {
        return vehicle.getPricePerKilometer() * distance;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setStartLocation(String startLocation) {
        ValidationHelper.validateStringLength(startLocation, START_LOCATION_MIN_LENGTH, START_LOCATION_MAX_LENGTH, INVALID_STARTING_LOCATION);
        this.startLocation = startLocation;
    }

    private void setDestination(String destination) {
        ValidationHelper.validateStringLength(destination, DESTINATION_MIN_LENGTH, DESTINATION_MAX_LENGTH, INVALID_DESTINATION);
        this.destination = destination;
    }

    private void setDistance(int distance) {
        ValidationHelper.validateValueInRange(distance, DISTANCE_MIN_VALUE, DISTANCE_MAX_VALUE, INVALID_DISTANCE);
        this.distance = distance;
    }

    private void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String getAsString() {
        return String.format("Journey ----%nStart location: %s%nDestination: %s%nDistance: %d%nVehicle type: %s%nTravel costs: %.2f%n",
                startLocation, destination, distance, vehicle.getType(), calculateTravelCosts());
    }
}