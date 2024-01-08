package org.telerik.web.beertag.repositories;

import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;

import java.util.List;

public interface BeerRepository {
    List<Beer> get(String name, Double maxAbv, Double minAbv, Integer styleId, String sortBy, String sortOrder);

    Beer get(int id);

    Beer get(String name);

    void create(Beer beer, User user);

    Beer update(Beer beer);

    void delete(int id);
}
