package org.telerik.web.beertag.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telerik.web.beertag.exceptions.DuplicateEntityException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.repositories.BeerRepository;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository repository;

    @Autowired
    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Beer> get() {
        return repository.get();
    }

    @Override
    public Beer getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void create(Beer beer) {
        boolean duplicateExists = true;
        try {
            repository.getByName(beer.getName());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }
        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }
        repository.create(beer);
    }

    @Override
    public void update(Beer beer) {
        boolean duplicateExists = true;
        try {
            Beer existingBeer = repository.getByName(beer.getName());
            if (existingBeer.getId() == beer.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }
        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }
        repository.update(beer);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
