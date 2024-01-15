package org.telerik.web.beertag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telerik.web.beertag.exceptions.AuthorizationException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.helpers.AuthenticationHelper;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.models.dtos.UserResponse;
import org.telerik.web.beertag.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private static final String UNAUTHORIZED_USER_ERROR = "You are not authorized to browse user information";
    private final UserService service;
    private final AuthenticationHelper helper;

    @Autowired
    public UserRestController(UserService service, AuthenticationHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @GetMapping
    public List<User> get(@RequestHeader HttpHeaders headers) {
        try {
            User user = helper.tryGetUser(headers);
            if (!user.isAdmin()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);

            }
            return service.getAll();
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        try {
            tryAuthorize(id, headers);
            User user = service.getById(id);
            return new UserResponse(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.isAdmin(), user.getWishList());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);
        }
    }

    @GetMapping("/search")
    public User get(@RequestParam String username, @RequestHeader HttpHeaders headers) {
        try {
            User user = helper.tryGetUser(headers);
            if (!user.isAdmin()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);
            }
            return service.getByUsername(username);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);
        }
    }

    @GetMapping("/{id}/wish-list")
    public List<Beer> getWishList(@PathVariable int id, @RequestHeader HttpHeaders headers) {
        try {
            User user = tryAuthorize(id, headers);
            return new ArrayList<>(user.getWishList());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);
        }
    }

    @PutMapping("/{userId}/wish-list/{beerId}")
    public List<Beer> addToWishList(@PathVariable int userId, @PathVariable int beerId,
                                    @RequestHeader HttpHeaders headers) {
        try {
            tryAuthorize(userId, headers);
            return service.addToWishList(userId, beerId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);
        }
    }

    @DeleteMapping("/{user}/wish-list/{beer}")
    public void deleteFromWishList(@PathVariable("user") int userId, @PathVariable("beer") int beerId,
                                   @RequestHeader HttpHeaders headers) {
        try {
            tryAuthorize(userId, headers);
            service.deleteFromWishList(userId, beerId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);
        }
    }

    private User tryAuthorize(int id, HttpHeaders headers) {
        User user = helper.tryGetUser(headers);
        if (!user.isAdmin() && user.getId() != id) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, UNAUTHORIZED_USER_ERROR);
        }
        return user;
    }
}
