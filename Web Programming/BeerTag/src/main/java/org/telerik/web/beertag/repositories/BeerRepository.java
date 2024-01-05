package org.telerik.web.beertag.repositories;

import org.telerik.web.beertag.models.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> get();

    Beer getById(int id);

    Beer getByName(String name);

    void create(Beer beer);

    void update(Beer beer);

    void delete(int id);
}
