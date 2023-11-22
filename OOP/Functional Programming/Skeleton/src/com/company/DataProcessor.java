package com.company;

import com.company.models.Customer;
import com.company.models.Genre;
import com.company.models.Movie;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ForLoopReplaceableByForEach")
public class DataProcessor {

    public static long countCustomersAboveTargetAge(List<Customer> customers, int targetAge) {
        return customers.stream().filter(c -> c.getAge() > targetAge).count();
    }

    /**
     * Hint: Is there a method on streams that asks the question "Do all elements match a given condition?"
     */
    public static boolean findIfAllCustomersAreAboveTargetAge(List<Customer> customers, int targetAge) {
        return customers.stream().allMatch(c -> c.getAge() == targetAge);
    }

    /**
     * Hint: Is there a method on streams that asks the question "Does any element match a given condition?"
     */
    public static boolean findIfAnyCustomersHasTargetName(List<Customer> customers, String targetName) {
        return customers.stream().anyMatch(c -> c.getName().equals(targetName));
    }

    /**
     * Hint: Is there a method on streams that asks the question "Do all element match a given condition?"
     */
    public static boolean findIfAllCustomersDislikeMovie(List<Customer> customers, Movie targetMovie) {
        return customers.stream().allMatch(c -> c.getDislikedMovies().contains(targetMovie));
    }

    /**
     * Hint: What method on streams eliminates elements, based on some condition?
     */
    public static long findHowManyPeopleLikeMove(List<Customer> customers, Movie targetMovie) {
        return customers.stream().filter(c -> c.getLikedMovies().contains(targetMovie)).count();
    }

    /**
     * Hint: Is there a method on streams that can eliminate elements from a collection, based on some condition? Also,
     * is there a method that transforms one thing into another thing?
     */
    public static double findTheAverageAgeOfPeopleWhoDislikeMovies(List<Customer> customers, Movie targetMovie) {
        return customers.stream().filter(c -> c.getDislikedMovies().contains(targetMovie)).mapToDouble(Customer::getAge)
                .average().orElse(0);
    }

    /**
     * Hint: There is a method average() but we can use it on numeric types only. What method transforms
     * streams from one type to another?
     */
    public static double findAverageAgeOfAllCustomers(List<Customer> customers) {
        return customers.stream().mapToDouble(Customer::getAge).average().orElse(0);


    }

    /**
     * Hint: First, we need to eliminate all customers whose age is below the targetAge. Then, we need to eliminate
     * all customers who do not have any movies with the targetGenre in their list of likedMovies.
     */
    public static List<Customer> findAllCustomersAboveTargetAgeThatLikeGenre(List<Customer> customers, int targetAge, Genre targetGenre) {
        return customers.stream()
                .filter(c -> c.getAge() > targetAge)
                .filter(c -> c.getLikedMovies().stream().anyMatch(m -> m.getGenre().equals(targetGenre)))
                .collect(Collectors.toList());
    }

    /**
     * Hint: Eliminate all customers with age below the targetAge.
     */
    public static List<Customer> findAllCustomersUnderTargetAge(List<Customer> customers, int targetAge) {
        return customers.stream().filter(c -> c.getAge() < targetAge).collect(Collectors.toList());
    }

    /**
     * Hint: https://www.baeldung.com/java-stream-reduce
     */
    public static Customer findTheCustomerWithTheLongestName(List<Customer> customers) {
        return customers.stream().reduce((c1, c2) -> c1.getName().length() > c2.getName().length() ? c1 : c2)
                .orElse(customers.get(0));
    }

    /**
     * Hint: From the list of customers, eliminate the ones whose list of movies
     * has a movie with a genre, different than the targetGenre.
     */
    public static List<Customer> findAllCustomersWhoLikeOnlyMoviesWithGenre(List<Customer> customers, Genre targetGenre) {
        return customers.stream().filter(c -> c.getLikedMovies().stream()
                .allMatch(m -> m.getGenre().equals(targetGenre))).collect(Collectors.toList());

    }
}
