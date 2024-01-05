package org.telerik.web.beertag.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telerik.web.beertag.exceptions.DuplicateEntityException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.services.BeerService;

import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {
    private BeerService service;

    @Autowired
    public BeerRestController(BeerService service) {
        this.service = service;
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
    public Beer create(@Valid @RequestBody Beer beer) {
        try {
            service.create(beer);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return beer;
    }

    @PutMapping("/{id}")
    public Beer update(@Valid @RequestBody Beer beer, @PathVariable int id) {
        try {
            service.update(beer);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return beer;
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        try {
            service.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
