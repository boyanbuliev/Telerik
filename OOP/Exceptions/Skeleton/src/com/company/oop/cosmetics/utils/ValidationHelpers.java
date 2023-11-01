package com.company.oop.cosmetics.utils;

import com.company.oop.cosmetics.utils.exceptions.InvalidNumberException;
import com.company.oop.cosmetics.utils.exceptions.InvalidStringLengthException;

public class ValidationHelpers {
    private static final String STRING_LENGTH_ERROR = "%s should be between %d and %d symbols.";
    private static final String NEGATIVE_PRICE_ERROR = "Price can't be negative.";

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

}
