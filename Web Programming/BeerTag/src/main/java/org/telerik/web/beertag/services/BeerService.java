package org.telerik.web.beertag.services;

import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;

import java.util.List;

public interface BeerService {
    List<Beer> get(String name, Double maxAbv, Double minAbv, Integer styleId, String sortBy, String sortOrder);

    Beer getById(int id);

    void create(Beer beer, User user);

    Beer update(Beer beer,int id, User user);

    void delete(int id, User user);
}
