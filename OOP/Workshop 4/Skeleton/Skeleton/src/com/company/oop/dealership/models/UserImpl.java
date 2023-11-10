package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class UserImpl implements User {


    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole userRole;
    private final List<Vehicle> vehicles;

    public UserImpl(String username, String firstName, String lastName, String password, UserRole userRole) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        this.userRole = userRole;
        this.vehicles = new ArrayList<>();
    }


    private void setUsername(String username) {
        validateStringLengthAndPattern(username, Constants.USERNAME_LEN_MIN, Constants.USERNAME_LEN_MAX, Constants.USERNAME_LEN_ERR, Constants.USERNAME_REGEX_PATTERN, Constants.USERNAME_PATTERN_ERR);
        this.username = username;
    }

    private void setFirstName(String firstName) {
        ValidationHelpers.validateStringLength(firstName, Constants.FIRSTNAME_LEN_MIN, Constants.FIRSTNAME_LEN_MAX, Constants.FIRSTNAME_LEN_ERR);
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        ValidationHelpers.validateStringLength(lastName, Constants.LASTNAME_LEN_MIN, Constants.LASTNAME_LEN_MAX, Constants.LASTNAME_LEN_ERR);
        this.lastName = lastName;
    }

    private void setPassword(String password) {
        validateStringLengthAndPattern(password, Constants.PASSWORD_LEN_MIN, Constants.PASSWORD_LEN_MAX, Constants.PASSWORD_LEN_ERR, Constants.PASSWORD_REGEX_PATTERN, Constants.PASSWORD_PATTERN_ERR);
        this.password = password;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public UserRole getRole() {
        return userRole;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (isAdmin())
            throw new IllegalArgumentException(Constants.ADMIN_CANNOT_ADD_VEHICLES);
        if (userRole == UserRole.NORMAL && vehicles.size() == Constants.NORMAL_ROLE_VEHICLE_LIMIT)
            throw new IllegalArgumentException(String.format(Constants.NOT_AN_VIP_USER_VEHICLES_ADD, Constants.NORMAL_ROLE_VEHICLE_LIMIT));

        vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    @Override
    public void addComment(Comment commentToAdd, Vehicle vehicleToAddComment) {
        vehicleToAddComment.addComment(commentToAdd);
    }

    @Override
    public void removeComment(Comment commentToRemove, Vehicle vehicleToRemoveComment) {
        if (!commentToRemove.getAuthor().equals(username))
            throw new IllegalArgumentException(Constants.YOU_ARE_NOT_THE_AUTHOR);

        vehicleToRemoveComment.removeComment(commentToRemove);
    }

    @Override
    public String printVehicles() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Constants.USER_HEADER, username)).append(System.lineSeparator());
        if (vehicles.isEmpty()) {
            sb.append(Constants.NO_VEHICLES_HEADER).append(System.lineSeparator());
            return sb.toString().trim();
        }

        for (int i = 0; i < vehicles.size(); i++) {
            sb.append(String.format("%d. %s%n", i + 1, vehicles.get(i)));
            if (vehicles.get(i).getComments().isEmpty()) {
                sb.append(Constants.NO_COMMENTS_HEADER).append(System.lineSeparator());
            } else {
                sb.append(Constants.COMMENTS_PRESENT_HEADER).append(System.lineSeparator());
                vehicles.get(i).getComments().forEach(sb::append);
                sb.append(Constants.COMMENTS_PRESENT_HEADER).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    @Override
    public boolean isAdmin() {
        return userRole == UserRole.ADMIN;
    }

    private void validateStringLengthAndPattern(String value, int min, int max, String lengthError, String
            pattern, String patternError) {
        ValidationHelpers.validateStringLength(value, min, max, lengthError);
        ValidationHelpers.validatePattern(value, pattern, patternError);
    }


    @Override
    public String toString() {
        return String.format(Constants.USER_TO_STRING, username, firstName, lastName, userRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl user = (UserImpl) o;
        return username.equals(user.username) && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && userRole == user.userRole;
    }

    private static class Constants {

        private static final int USERNAME_LEN_MIN = 2;
        private static final int USERNAME_LEN_MAX = 20;
        private static final String USERNAME_LEN_ERR = format(
                "Username must be between %d and %d characters long!",
                USERNAME_LEN_MIN,
                USERNAME_LEN_MAX);
        private static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]+$";
        private static final String USERNAME_PATTERN_ERR = "Username contains invalid symbols!";
        private static final int PASSWORD_LEN_MIN = 5;
        private static final int PASSWORD_LEN_MAX = 30;
        private static final String PASSWORD_LEN_ERR = format(
                "Password must be between %s and %s characters long!",
                PASSWORD_LEN_MIN,
                PASSWORD_LEN_MAX);
        private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
        private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
        private static final int LASTNAME_LEN_MIN = 2;
        private static final int LASTNAME_LEN_MAX = 20;
        private static final String LASTNAME_LEN_ERR = format(
                "Lastname must be between %s and %s characters long!",
                LASTNAME_LEN_MIN,
                LASTNAME_LEN_MAX);
        private static final int FIRSTNAME_LEN_MIN = 2;
        private static final int FIRSTNAME_LEN_MAX = 20;
        private static final String FIRSTNAME_LEN_ERR = format(
                "Firstname must be between %s and %s characters long!",
                FIRSTNAME_LEN_MIN,
                FIRSTNAME_LEN_MAX);
        private final static String NOT_AN_VIP_USER_VEHICLES_ADD = "You are not VIP and cannot add more than %d vehicles!";
        private final static String ADMIN_CANNOT_ADD_VEHICLES = "You are an admin and therefore cannot add vehicles!";
        private static final String YOU_ARE_NOT_THE_AUTHOR = "You are not the author of the comment you are trying to remove!";
        private final static String USER_TO_STRING = "Username: %s, FullName: %s %s, Role: %s";
        private final static String NO_VEHICLES_HEADER = "--NO VEHICLES--";
        private final static String NO_COMMENTS_HEADER = "--NO COMMENTS--";
        private final static String COMMENTS_PRESENT_HEADER = "--COMMENTS--";
        private final static String USER_HEADER = "--USER %s--";
        private static final int NORMAL_ROLE_VEHICLE_LIMIT = 5;
    }
}