package org.telerik.web.beertag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> get() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    public User get(@RequestParam String username) {
        try {
            return service.getByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}/wish-list")
    public List<Beer> getWishList(@PathVariable int id) {
        try {
        return new ArrayList<>(service.getById(id).getWishList());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{user}/wish-list/{beer}")
    public void addToWishList(@PathVariable("user") int userId, @PathVariable("beer") int beerId) {
        service.addToWishList(userId, beerId);
    }

    @DeleteMapping("/{user}/wish-list/{beer}")
    public void deleteFromWishList(@PathVariable("user") int userId, @PathVariable("beer") int beerId) {
        try {
            service.deleteFromWishList(userId, beerId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
