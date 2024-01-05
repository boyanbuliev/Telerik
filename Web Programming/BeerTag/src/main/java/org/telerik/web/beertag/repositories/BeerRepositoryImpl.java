package org.telerik.web.beertag.repositories;

import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeerRepositoryImpl implements BeerRepository {

    private List<Beer> beers;

    public BeerRepositoryImpl() {
        beers = new ArrayList<>();
        beers.add(new Beer(1, "Glarus", 4.6));
        beers.add(new Beer(2, "Rhombus Porter", 5.0));
    }

    @Override
    public List<Beer> get() {
        return beers;
    }

    @Override
    public Beer getById(int id) {
        return beers.stream().filter(b -> b.getId() == id).findFirst().orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }

    @Override
    public Beer getByName(String name) {
        return beers.stream().filter(b -> b.getName().equals(name)).findFirst().orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    @Override
    public void create(Beer beer) {
        beers.add(beer);
    }

    @Override
    public void update(Beer beer) {
        Beer beerToUpdate = getById(beer.getId());
        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    @Override
    public void delete(int id) {
        beers.remove(getById(id));
    }
}
