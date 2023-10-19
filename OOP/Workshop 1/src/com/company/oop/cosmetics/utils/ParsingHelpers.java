package com.company.oop.cosmetics.utils;

import com.company.oop.cosmetics.models.GenderType;

public class ParsingHelpers {

    public static double tryParseDouble(String valueToParse, String errorMessage) {
        try {
            return Double.parseDouble(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static GenderType tryParseGender(String valueToParse, String errorMessage) {
        try {
            return GenderType.valueOf(valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }

}
