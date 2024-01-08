package org.telerik.web.beertag.services;

import org.telerik.web.beertag.models.Style;

import java.util.List;

public interface StyleService {
    List<Style> getAllStyles();

    Style getStyleById(int id);


}
