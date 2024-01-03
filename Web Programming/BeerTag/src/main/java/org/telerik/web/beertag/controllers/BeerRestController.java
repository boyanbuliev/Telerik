package org.telerik.web.beertag.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.telerik.web.beertag.models.Beer;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {

    private final List<Beer> beers;

    public BeerRestController() {
        beers = new ArrayList<>();
        beers.add(new Beer(1, "Glarus", 4.6));
        beers.add(new Beer(2, "Rhombus Porter", 5.0));
    }

    @GetMapping
    public List<Beer> get() {
        return beers;
    }

    @GetMapping("/{id}")
    public Beer get(@PathVariable int id) {
        return beers.stream().filter(b -> b.getId() == id).findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Beer with id %d not found.", id)));
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer) {
        beers.add(beer);
        return beer;
    }

    @PutMapping("/{id}")
    public Beer update(@Valid @RequestBody Beer beer, @PathVariable int id) {
        Beer curr = beers.get(id);
        curr.setName(beer.getName());
        curr.setAbv(beer.getAbv());
        return beer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        beers.remove(beers.get(id));
    }
}
