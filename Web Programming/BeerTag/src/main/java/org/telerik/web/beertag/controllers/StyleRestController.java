package org.telerik.web.beertag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Style;
import org.telerik.web.beertag.services.StyleService;

import java.util.List;

@RestController
@RequestMapping("/api/styles")
public class StyleRestController {
    private final StyleService service;

    @Autowired
    public StyleRestController(StyleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Style> get() {
        return service.getAllStyles();
    }

    @GetMapping("/{id}")
    public Style get(@PathVariable int id) {
        try {
            return service.getStyleById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
