package com.company.oop.cosmetics.utils;

import com.company.oop.cosmetics.commands.CommandType;
import com.company.oop.cosmetics.utils.exceptions.InvalidCommandException;
import com.company.oop.cosmetics.utils.exceptions.InvalidNumberException;
import com.company.oop.cosmetics.utils.exceptions.InvalidParametersCountException;
import com.company.oop.cosmetics.utils.exceptions.InvalidStringLengthException;

import java.util.Arrays;
import java.util.List;

public class ValidationHelpers {
    private static final String STRING_LENGTH_ERROR = "%s name should be between %d and %d symbols.";
    private static final String NEGATIVE_PRICE_ERROR = "Price can't be negative.";
    private static final String INCORRECT_PARAMETERS_COUNT_ERROR = "%s command expects %d parameters.";
    private static final String NOT_SUPPORTED_COMMAND = "Command %s is not supported.";

    public static void validateStringLength(String strToCheck, int minLength, int maxLength, String type) {
        if (strToCheck.length() < minLength || strToCheck.length() > maxLength) {
            throw new InvalidStringLengthException(String.format(STRING_LENGTH_ERROR, type, minLength, maxLength));
        }
    }

    public static void validateNumberIsNonNegative(double numberToCheck, int minimumNumber) {
        if (numberToCheck < minimumNumber) {
            throw new InvalidNumberException(NEGATIVE_PRICE_ERROR);
        }
    }

    public static void validateArgumentsCount(List<String> arguments, int count, String type) {
        if (arguments.size() != count) {
            throw new InvalidParametersCountException(String.format(INCORRECT_PARAMETERS_COUNT_ERROR, type, count));
        }

    }

    public static void validateCommandFormat(String command) {
        Arrays.stream(CommandType.values()).filter(e -> e.toString().equals(command.toUpperCase())).findAny()
                .orElseThrow(() -> new InvalidCommandException(String.format(NOT_SUPPORTED_COMMAND, command)));

    }

}
