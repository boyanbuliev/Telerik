package org.telerik.web.beertag.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telerik.web.beertag.exceptions.DuplicateEntityException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.exceptions.UnauthorizedOperationException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.services.BeerService;

import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {
    private BeerService service;
    private AuthenticationHelper helper;

    @Autowired
    public BeerRestController(BeerService service, AuthenticationHelper helper) {
        this.service = service;
        this.helper = helper;
    }

    @GetMapping
    public List<Beer> get() {
        return service.get();
    }

    @GetMapping("/{id}")
    public Beer get(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer create(@RequestHeader HttpHeaders headers, @Valid @RequestBody Beer beer) {
        try {
            User user = helper.tryGetUser(headers);
            service.create(beer);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return beer;
    }

    @PutMapping("/{id}")
    public Beer update(@RequestHeader HttpHeaders headers, @Valid @RequestBody Beer beer, @PathVariable int id) {
        try {
            User user = helper.tryGetUser(headers);
            service.update(beer, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (UnauthorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        return beer;
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = helper.tryGetUser(headers);
            service.delete(id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
