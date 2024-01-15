package org.telerik.web.beertag.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BeerService beerService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BeerService beerService) {
        this.userRepository = userRepository;
        this.beerService = beerService;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public List<Beer> addToWishList(int userId, int beerId) {
        User user = userRepository.getById(userId);
        Beer beer = beerService.getById(beerId);
        if (user.getWishList().add(beer)) {
            userRepository.update(user);
        }
        return new ArrayList<>(user.getWishList());
    }

    @Override
    public List<Beer> deleteFromWishList(int userId, int beerId) {
        User user = userRepository.getById(userId);
        Beer beer = beerService.getById(beerId);
        if (user.getWishList().remove(beer)) {
            userRepository.update(user);
        }
        return new ArrayList<>(user.getWishList());
    }

}
