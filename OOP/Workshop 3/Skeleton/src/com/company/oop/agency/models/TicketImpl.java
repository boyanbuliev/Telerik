package com.company.oop.agency.models;

import com.company.oop.agency.models.contracts.Journey;
import com.company.oop.agency.models.contracts.Ticket;
import com.company.oop.agency.utils.ValidationHelper;

public class TicketImpl implements Ticket {

    private int id;
    private Journey journey;
    private double administrativeCosts;

    public TicketImpl(int id, Journey journey, double costs) {
        this.id = id;
        this.journey = journey;
        setAdministrativeCosts(costs);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Journey getJourney() {
        return journey;
    }

    @Override
    public double calculatePrice() {
        return journey.calculateTravelCosts() * administrativeCosts;
    }

    @Override
    public double getAdministrativeCosts() {
        return administrativeCosts;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setJourney(Journey journey) {
        this.journey = journey;
    }

    private void setAdministrativeCosts(double administrativeCosts) {
        ValidationHelper.validateNumberNoLesserThan(administrativeCosts, 0,
                String.format("Value of 'costs' must be a positive number. Actual value: %.2f.", administrativeCosts));
        this.administrativeCosts = administrativeCosts;
    }

    @Override
    public String getAsString() {
        return String.format("Ticket ----%nDestination: %s%nPrice: %.2f%n", journey.getDestination(), calculatePrice());
    }
}
