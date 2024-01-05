package org.telerik.web.beertag.repositories;

import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeerMapRepositoryImpl implements BeerRepository {
    private Map<Integer, Beer> beers;

    public BeerMapRepositoryImpl() {
        this.beers = new HashMap<>();
    }

    @Override
    public List<Beer> get() {
        return new ArrayList<>(beers.values());
    }

    @Override
    public Beer getById(int id) {
        Beer beer = beers.get(id);
        if (beer == null) {
            throw new EntityNotFoundException("Beer", id);
        }
        return beer;
    }

    @Override
    public Beer getByName(String name) {
        return beers.values().stream().filter(b -> b.getName().equals(name)).findFirst().orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    @Override
    public void create(Beer beer) {
        beers.put(beer.getId(), beer);
    }

    @Override
    public void update(Beer beer) {
        beers.replace(beer.getId(), beer);
    }

    @Override
    public void delete(int id) {
        beers.remove(id);
    }
}
