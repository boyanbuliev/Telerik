package org.telerik.web.beertag.repositories;

import org.telerik.web.beertag.models.Style;

import java.util.List;

public interface StyleRepository {
    List<Style> get();

    Style get(int id);
}
