package com.company.oop.agency.commands.creation;

import com.company.oop.agency.commands.CommandsConstants;
import com.company.oop.agency.commands.contracts.Command;
import com.company.oop.agency.core.contracts.AgencyRepository;
import com.company.oop.agency.models.contracts.Journey;
import com.company.oop.agency.models.contracts.Ticket;
import com.company.oop.agency.utils.ParsingHelpers;
import com.company.oop.agency.utils.ValidationHelper;

import java.util.List;

public class CreateTicketCommand implements Command {
    // TODO: 1.11.2023 г.  
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final AgencyRepository repository;

    private int journeyId;
    private double administrativeCosts;

    public CreateTicketCommand(AgencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelper.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Journey journey = repository.findJourneyById(journeyId);
        Ticket ticket = repository.createTicket(journey, administrativeCosts);

        return String.format(CommandsConstants.TICKET_CREATED_MESSAGE, ticket.getId());
    }

    private void parseParameters(List<String> parameters) {
        journeyId = ParsingHelpers.tryParseInteger(parameters.get(0), "journey id");
        administrativeCosts = ParsingHelpers.tryParseDouble(parameters.get(1), "cost");
    }
}