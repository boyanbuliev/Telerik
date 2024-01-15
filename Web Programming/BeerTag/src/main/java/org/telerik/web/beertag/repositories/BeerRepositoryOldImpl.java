package org.telerik.web.beertag.repositories;

import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.FilterOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//@Repository
public class BeerRepositoryOldImpl implements BeerRepository {

    private final List<Beer> beers;

    //    @Autowired
    public BeerRepositoryOldImpl(StyleRepository styleRepository, UserRepository userRepository) {
        beers = new ArrayList<>();
        beers.add(new Beer(1, "Glarus", 4.6, styleRepository.get(1), userRepository.getById(1)));
        beers.add(new Beer(2, "Rhombus Porter", 3.0, styleRepository.get(2), userRepository.getById(2)));
        beers.add(new Beer(3, "Opasen Char", 6.6, styleRepository.get(3), userRepository.getById(3)));
    }

    @Override
    public List<Beer> get(FilterOptions filterOptions) {
        List<Beer> result = new ArrayList<>(beers);
//        result = filterBeers(name, maxAbv, minAbv, styleId, sortParam, sortOrderParam, result);
        return result;
    }

    private List<Beer> filterBeers(String name, Double maxAbv, Double minAbv, Integer styleId, String sortParam, String sortOrderParam, List<Beer> result) {
        result = filterByName(result, name);
        result = filterByAbv(result, maxAbv, minAbv);
        result = filterByStyle(result, styleId);
        result = sortBy(result, sortParam);
        result = sortOrder(result, sortOrderParam);
        return result;
    }

    private List<Beer> sortOrder(List<Beer> beers, String sortOrderParam) {
        if (sortOrderParam != null && !sortOrderParam.isEmpty())
            if (sortOrderParam.equalsIgnoreCase("desc")) Collections.reverse(beers);
        return beers;
    }

    private List<Beer> sortBy(List<Beer> beers, String sortParam) {
        if (sortParam != null && !sortParam.isEmpty()) switch (sortParam) {
            case "name":
                beers.sort(Comparator.comparing(Beer::getName));
                break;
            case "abv":
                beers.sort(Comparator.comparing(Beer::getAbv));
                break;
            case "style":
                beers.sort(Comparator.comparing(b -> b.getStyle().getName()));
                break;
        }
        return beers;
    }

    private List<Beer> filterByStyle(List<Beer> beers, Integer styleId) {
        if (styleId != null)
            beers = beers.stream().filter(b -> b.getStyle().getId() == styleId).collect(Collectors.toList());
        return beers;
    }

    private List<Beer> filterByAbv(List<Beer> beers, Double maxAbv, Double minAbv) {
        if (maxAbv != null) beers = beers.stream().filter(b -> b.getAbv() <= maxAbv).collect(Collectors.toList());
        if (minAbv != null) beers = beers.stream().filter(b -> b.getAbv() >= minAbv).collect(Collectors.toList());
        return beers;
    }

    private List<Beer> filterByName(List<Beer> beers, String name) {
        if (name != null && !name.isEmpty())
            beers = beers.stream().filter(b -> b.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        return beers;
    }

    @Override
    public Beer get(int id) {
        return beers.stream().filter(b -> b.getId() == id).findFirst().orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }

    @Override
    public Beer get(String name) {
        return beers.stream().filter(b -> b.getName().equals(name)).findFirst().orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    @Override
    public void create(Beer beer) {
        beer.setId(beers.size() + 1);
        beers.add(beer);
    }

    @Override
    public Beer update(Beer beer) {
        Beer beerToUpdate = get(beer.getId());
        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
        beerToUpdate.setStyle(beer.getStyle());
        return beerToUpdate;
    }

    @Override
    public void delete(int id) {
        beers.remove(get(id));
    }
}
