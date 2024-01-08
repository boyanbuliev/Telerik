package org.telerik.web.beertag.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.dtos.BeerDto;
import org.telerik.web.beertag.services.StyleService;

@Component
public class BeerMapper {
    private final StyleService service;

    @Autowired
    public BeerMapper(StyleService service) {
        this.service = service;

    }

    public Beer fromDto(BeerDto beerDto, int beerId) {
        Beer beer = fromDto(beerDto);
        beer.setId(beerId);
        return beer;
    }

    public Beer fromDto(BeerDto beerDto) {
        Beer beer = new Beer();
        beer.setName(beerDto.getName());
        beer.setAbv(beerDto.getAbv());
        beer.setStyle(service.getStyleById(beerDto.getStyleId()));
        return beer;
    }
}
