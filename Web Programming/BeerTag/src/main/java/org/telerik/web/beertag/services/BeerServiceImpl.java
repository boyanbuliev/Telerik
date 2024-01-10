package org.telerik.web.beertag.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telerik.web.beertag.exceptions.DuplicateEntityException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.exceptions.UnauthorizedOperationException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.repositories.BeerRepository;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;

    @Autowired
    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Beer> get() {
        return repository.get();
    }

    @Override
    public List<Beer> get(String name, Double maxAbv, Double minAbv, Integer styleId, String sortBy, String sortOrder) {
        return repository.get(name, maxAbv, minAbv, styleId, sortBy, sortOrder);
    }

    @Override
    public Beer getById(int id) {
        return repository.get(id);
    }

    @Override
    public Beer getByName(String name) {
        return repository.get(name);
    }

    @Override
    public void create(Beer beer, User user) {
        boolean duplicateExists = true;
        try {
            repository.get(beer.getName());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }
        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }
        beer.setCreatedBy(user);
        repository.create(beer);
    }

    @Override
    public Beer update(Beer beer, int id, User user) {
        if (!user.isAdmin() && !repository.get(id).getCreatedBy().equals(user)) {
            throw new UnauthorizedOperationException("Only admins can modify beer.");
        }
        boolean duplicateExists = true;
        try {
            Beer existingBeer = repository.get(beer.getName());
            if (existingBeer.getId() == beer.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }
        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }
        return repository.update(beer);
    }

    @Override
    public void delete(int id, User user) {
        if (!user.isAdmin() && !repository.get(id).getCreatedBy().equals(user)) {
            throw new UnauthorizedOperationException("Only admins can delete beer.");
        }
        repository.delete(id);
    }
}
