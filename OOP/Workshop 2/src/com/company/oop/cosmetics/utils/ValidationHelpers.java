package com.company.oop.cosmetics.utils;

import java.util.List;

public class ValidationHelpers {

    private static final String STRING_LENGTH_ERROR = "%s should be between %d and %d symbols.";
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";

    public static void validateIntRange(int minLength, int maxLength, int actualLength, String type) {
        if (actualLength < minLength || actualLength > maxLength) {
            throw new IllegalArgumentException(String.format(STRING_LENGTH_ERROR, type, minLength, maxLength));
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String type) {
        validateIntRange(minLength, maxLength, stringToValidate.length(), type);
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters || list.size() > expectedNumberOfParameters) {
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_ARGUMENTS,
                    expectedNumberOfParameters, list.size()));
        }
    }

}
