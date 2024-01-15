package org.telerik.web.beertag.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telerik.web.beertag.exceptions.AuthorizationException;
import org.telerik.web.beertag.exceptions.DuplicateEntityException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.exceptions.UnauthorizedOperationException;
import org.telerik.web.beertag.helpers.AuthenticationHelper;
import org.telerik.web.beertag.helpers.BeerMapper;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.FilterOptions;
import org.telerik.web.beertag.models.User;
import org.telerik.web.beertag.models.dtos.BeerDto;
import org.telerik.web.beertag.services.BeerService;

import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {
    private final BeerService service;
    private final BeerMapper mapper;
    private final AuthenticationHelper helper;

    @Autowired
    public BeerRestController(BeerService service, BeerMapper mapper, AuthenticationHelper helper) {
        this.service = service;
        this.mapper = mapper;
        this.helper = helper;
    }

    @GetMapping
    public List<Beer> get(@RequestParam(required = false) String name, @RequestParam(required = false) Double maxAbv,
                          @RequestParam(required = false) Double minAbv, @RequestParam(required = false) Integer styleId,
                          @RequestParam(required = false) String sortBy, @RequestParam(required = false) String sortOrder) {
        FilterOptions filterOptions = new FilterOptions(name, minAbv, maxAbv, styleId, sortBy, sortOrder);
        return service.get(filterOptions);
    }

    @GetMapping("/{id}")
    public Beer get(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    public Beer get(@RequestParam String name) {
        try {
            return service.getByName(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer create(@RequestHeader HttpHeaders headers, @Valid @RequestBody BeerDto beerDto) {
        try {
            User user = helper.tryGetUser(headers);
            Beer beer = mapper.fromDto(beerDto);
            service.create(beer, user);
            return beer;
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer update(@RequestHeader HttpHeaders headers, @Valid @RequestBody BeerDto beerDto, @PathVariable int id) {
        try {
            User user = helper.tryGetUser(headers);
            Beer beer = mapper.fromDto(beerDto, id);
            return service.update(beer, id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (UnauthorizedOperationException | AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = helper.tryGetUser(headers);
            service.delete(id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorizedOperationException | AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
