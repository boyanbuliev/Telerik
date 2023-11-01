package com.company.oop.agency.commands.creation;

import com.company.oop.agency.commands.contracts.Command;
import com.company.oop.agency.core.contracts.AgencyRepository;
import com.company.oop.agency.models.contracts.Journey;
import com.company.oop.agency.models.vehicles.contracts.Vehicle;
import com.company.oop.agency.utils.ParsingHelpers;
import com.company.oop.agency.utils.ValidationHelper;

import java.util.List;

import static com.company.oop.agency.commands.CommandsConstants.JOURNEY_CREATED_MESSAGE;

public class CreateJourneyCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    private final AgencyRepository repository;

    private int distance;
    private int vehicleId;
    private String destination;
    private String startLocation;

    public CreateJourneyCommand(AgencyRepository agencyRepository) {
        this.repository = agencyRepository;
    }

    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Vehicle vehicle = repository.findVehicleById(vehicleId);
        Journey journey = repository.createJourney(startLocation, destination, distance, vehicle);

        return String.format(JOURNEY_CREATED_MESSAGE, journey.getId());
    }

    private void parseParameters(List<String> parameters) {
        startLocation = parameters.get(0);
        destination = parameters.get(1);
        distance = ParsingHelpers.tryParseInteger(parameters.get(2), "distance");
        vehicleId = ParsingHelpers.tryParseInteger(parameters.get(3), "vehicle id");
    }

}