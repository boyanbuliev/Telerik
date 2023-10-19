package com.company.oop.cosmetics.utils;

import java.util.List;

public class ValidationHelpers {

    public static void validateIntRange(int minLength, int maxLength, int actualLength, String errorMessage) {
        if (actualLength < minLength || actualLength > maxLength) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String errorMessage) {
        validateIntRange(minLength, maxLength, stringToValidate.length(), errorMessage);
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters || list.size() > expectedNumberOfParameters) {
            throw new IllegalArgumentException(
                    String.format("Invalid number of arguments. Expected: %d; received: %d.",
                            expectedNumberOfParameters, list.size())
            );
        }
    }

}
