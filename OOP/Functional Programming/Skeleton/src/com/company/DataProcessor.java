package com.company;

import com.company.models.Customer;
import com.company.models.Genre;
import com.company.models.Movie;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ForLoopReplaceableByForEach")
public class DataProcessor {

    public static long countCustomersAboveTargetAge(List<Customer> customers, int targetAge) {
        int result = 0;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAge() > targetAge) {
                result++;
            }
        }

        return result;
    }

    /**
     * Hint: Is there a method on streams that asks the question "Do all elements match a given condition?"
     */
    public static boolean findIfAllCustomersAreAboveTargetAge(List<Customer> customers, int targetAge) {
        boolean result = true;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAge() < targetAge) {
                result = false;
                break;
            }
        }

        return result;
    }

    /**
     * Hint: Is there a method on streams that asks the question "Does any element match a given condition?"
     */
    public static boolean findIfAnyCustomersHasTargetName(List<Customer> customers, String targetName) {
        boolean result = false;

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getName().equals(targetName)) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Hint: Is there a method on streams that asks the question "Do all element match a given condition?"
     */
    public static boolean findIfAllCustomersDislikeMovie(List<Customer> customers, Movie targetMovie) {
        boolean result = true;

        for (int i = 0; i < customers.size(); i++) {
            if (!customers.get(i).getDislikedMovies().contains(targetMovie)) {
                result = false;
                break;
            }
        }

        return result;
    }

    /**
     * Hint: What method on streams eliminates elements, based on some condition?
     */
    public static long findHowManyPeopleLikeMove(List<Customer> customers, Movie targetMovie) {
        long count = 0;

        for (int i = 0; i < customers.size(); i++) {
            for (int j = 0; j < customers.get(i).getLikedMovies().size(); j++) {
                if (customers.get(i).getLikedMovies().get(j).equals(targetMovie)) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Hint: Is there a method on streams that can eliminate elements from a collection, based on some condition? Also,
     * is there a method that transforms one thing into another thing?
     */
    public static double findTheAverageAgeOfPeopleWhoDislikeMovies(List<Customer> customers, Movie targetMovie) {
        List<Customer> customersWhoDislikeMovie = new ArrayList<>();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getDislikedMovies().contains(targetMovie)) {
                customersWhoDislikeMovie.add(customers.get(i));
            }
        }

        double sum = 0;
        for (int i = 0; i < customersWhoDislikeMovie.size(); i++) {
            sum += customersWhoDislikeMovie.get(i).getAge();
        }

        return sum / customersWhoDislikeMovie.size();
    }

    /**
     * Hint: There is a method average() but we can use it on numeric types only. What method transforms
     * streams from one type to another?
     */
    public static double findAverageAgeOfAllCustomers(List<Customer> customers) {
        double sum = 0;

        for (int i = 0; i < customers.size(); i++) {
            sum += customers.get(i).getAge();
        }

        return sum / customers.size();
    }

    /**
     * Hint: First, we need to eliminate all customers whose age is below the targetAge. Then, we need to eliminate
     * all customers who do not have any movies with the targetGenre in their list of likedMovies.
     */
    public static List<Customer> findAllCustomersAboveTargetAgeThatLikeGenre(List<Customer> customers, int targetAge, Genre targetGenre) {
        List<Customer> result = new ArrayList<>();

        for (int i = 0; i < customers.size(); i++) {
            var customer = customers.get(i);
            if (customer.getAge() > targetAge) {
                for (int j = 0; j < customer.getLikedMovies().size(); j++) {
                    if (customer.getLikedMovies().get(j).getGenre().equals(targetGenre)) {
                        result.add(customer);
                        break;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Hint: Eliminate all customers with age below the targetAge.
     */
    public static List<Customer> findAllCustomersUnderTargetAge(List<Customer> customers, int targetAge) {
        List<Customer> customersUnderTargetAge = new ArrayList<>();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAge() < targetAge) {
                customersUnderTargetAge.add(customers.get(i));
            }
        }

        return customersUnderTargetAge;
    }

    /**
     * Hint: https://www.baeldung.com/java-stream-reduce
     */
    public static Customer findTheCustomerWithTheLongestName(List<Customer> customers) {
        Customer withLongestName = customers.get(0);

        for (int i = 1; i < customers.size(); i++) {
            if (customers.get(i).getName().length() > withLongestName.getName().length()) {
                withLongestName = customers.get(i);
            }
        }

        return withLongestName;
    }

    /**
     * Hint: From the list of customers, eliminate the ones whose list of movies
     * has a movie with a genre, different than the targetGenre.
     */
    public static List<Customer> findAllCustomersWhoLikeOnlyMoviesWithGenre(List<Customer> customers, Genre targetGenre) {
        List<Customer> result = new ArrayList<>();

        for (Customer customer : customers) {
            var customerMovies = customer.getLikedMovies();
            var areSameGenre = false;

            for (int j = 0; j < customerMovies.size() - 1; j++) {
                if (!customerMovies.get(j).getGenre().equals(targetGenre)) break;

                if (customerMovies.get(j).getGenre().equals(customerMovies.get(j + 1).getGenre())) {
                    areSameGenre = true;
                    break;
                }
            }

            if (areSameGenre) {
                result.add(customer);
            }
        }

        return result;
    }
}
