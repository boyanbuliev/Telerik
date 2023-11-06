package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class ShowUsersCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private static final String USERS_HEADER = "--USERS--";
    private static final String LOGGED_USER_NOT_ADMIN = "You are not an admin!";

    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected boolean requiresLogin() {
        return false;
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return showUsers();
    }

    private String showUsers() {
        if (!getVehicleDealershipRepository().getLoggedInUser().isAdmin())
            throw new IllegalArgumentException(LOGGED_USER_NOT_ADMIN);

        StringBuilder sb = new StringBuilder();
        sb.append(USERS_HEADER).append(System.lineSeparator());
        List<User> users = getVehicleDealershipRepository().getUsers();
        for (int i = 0; i < users.size(); i++) {
            sb.append(String.format("%d. %s%n", i + 1, users.get(i)));
        }
        return sb.toString().trim();
    }
}

