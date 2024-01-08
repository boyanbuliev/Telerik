package org.telerik.web.beertag.repositories;

import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Style;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StyleRepositoryImpl implements StyleRepository {
    private final List<Style> styles;

    public StyleRepositoryImpl() {
        styles = new ArrayList<>();
        styles.add(new Style(1, "Special Ale"));
        styles.add(new Style(2, "English Porter"));
        styles.add(new Style(3, "Indian Pale Ale"));
    }

    @Override
    public List<Style> get() {
        return new ArrayList<>(styles);
    }

    @Override
    public Style get(int id) {
        return styles.stream().filter(s -> s.getId() == id).findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Style", id));
    }
}
