package org.telerik.web.beertag.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telerik.web.beertag.models.Style;
import org.telerik.web.beertag.repositories.StyleRepository;

import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository repository;

    @Autowired
    public StyleServiceImpl(StyleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Style> getAllStyles() {
        return repository.get();
    }

    @Override
    public Style getStyleById(int id) {
        return repository.get(id);
    }
}
