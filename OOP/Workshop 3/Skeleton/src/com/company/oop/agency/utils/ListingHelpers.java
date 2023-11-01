package com.company.oop.agency.utils;

import com.company.oop.agency.commands.CommandsConstants;
import com.company.oop.agency.models.contracts.Journey;
import com.company.oop.agency.models.contracts.Printable;
import com.company.oop.agency.models.contracts.Ticket;
import com.company.oop.agency.models.vehicles.contracts.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class ListingHelpers {

    public static String ticketsToString(List<Ticket> tickets) {
        return elementsToString(tickets);
    }

    public static String vehiclesToString(List<Vehicle> vehicles) {
        return elementsToString(vehicles);
    }

    public static String journeysToString(List<Journey> journeys) {
        return elementsToString(journeys);
    }

    public static <T extends Printable> String elementsToString(List<T> elements) {
        return elements.stream().map(Printable::getAsString)
                .collect(Collectors.joining(CommandsConstants.JOIN_DELIMITER + System.lineSeparator())).trim();
    }

}
