package org.telerik.web.beertag.repositories;

import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.FilterOptions;

import java.util.List;

public interface BeerRepository {
    List<Beer> get(FilterOptions filterOptions);

    Beer get(int id);

    Beer get(String name);

    void create(Beer beer);

    Beer update(Beer beer);

    void delete(int id);
}
