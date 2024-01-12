package org.telerik.web.beertag.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final BeerService beerService;

    @Autowired
    public UserServiceImpl(UserRepository repository, BeerService beerService) {
        this.repository = repository;
        this.beerService = beerService;
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getById(int id) {
        return repository.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return repository.getByUsername(username);
    }

    @Override
    public void addToWishList(int userId, int beerId) {
        Beer beer= beerService.getById(beerId);
        repository.addToWishList(userId, beer);
    }

    @Override
    public void deleteFromWishList(int userId, int beerId) {
        Beer beer= beerService.getById(beerId);
        repository.deleteFromWishList(userId, beer);
    }

}
