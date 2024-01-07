package org.telerik.web.beertag.services;

import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;

import java.util.List;

public interface BeerService {
    List<Beer> get();

    Beer getById(int id);

    void create(Beer beer);

    void update(Beer beer, User user);

    void delete(int id, User user);
}
