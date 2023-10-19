package com.company.oop.cosmetics.tests.utils;

import java.util.Arrays;
import java.util.List;


public class TestUtilities {
    /**
     * Returns a new List with size equal to wantedSize.
     * Useful when you do not care what the contents of the List are,
     * for example when testing if a list of a command throws exception
     * when it's parameters list's size is less/more than expected.
     *
     * @param wantedSize the size of the List to be returned.
     * @return a new List with size equal to wantedSize
     */
    public static String getString(int wantedSize) {
        return "x".repeat(wantedSize);
    }

    /**
     * Returns a new List with the passed size param
     * Useful when you do not care for the contents of the List,
     * for example when testing if a List of given size
     * would cause an exception being thrown.
     *
     * @param size the size of List to be returned.
     * @return a new List with size equal to size
     */
    public static List<String> getList(int size) {
        return Arrays.asList(new String[size]);
    }

}
