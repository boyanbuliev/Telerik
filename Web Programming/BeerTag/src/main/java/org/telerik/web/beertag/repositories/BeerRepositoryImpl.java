package org.telerik.web.beertag.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BeerRepositoryImpl implements BeerRepository {
    private final StyleRepository styleRepository;
    private final UserRepository userRepository;
    private final SessionFactory sessionFactory;

    @Autowired
    public BeerRepositoryImpl(StyleRepository styleRepository, UserRepository userRepository, SessionFactory sessionFactory) {
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> get() {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer", Beer.class);
            return query.list();
        }
    }

    @Override
    public List<Beer> get(String name, Double maxAbv, Double minAbv, Integer styleId, String sortBy, String sortOrder) {
        return filterBeers(name, maxAbv, minAbv, styleId, sortBy, sortOrder, get());
    }

    @Override
    public Beer get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Beer beer = session.get(Beer.class, id);
            if (beer == null) {
                throw new EntityNotFoundException("Beer", id);
            }
            return beer;
        }
    }

    @Override
    public Beer get(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where name = :name", Beer.class);
            query.setParameter("name", name);
            List<Beer> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Beer", "name", name);
            }
            return result.get(0);
        }
    }

    @Override
    public void create(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.save(beer);
        }
    }

    @Override
    public Beer update(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(beer);
            session.getTransaction().commit();
            return get(beer.getId());
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(session.load(User.class, id));
            session.getTransaction().commit();
        }
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
}