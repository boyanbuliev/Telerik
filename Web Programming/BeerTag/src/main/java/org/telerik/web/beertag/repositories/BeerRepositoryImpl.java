package org.telerik.web.beertag.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Beer;
import org.telerik.web.beertag.models.FilterOptions;
import org.telerik.web.beertag.models.User;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BeerRepositoryImpl implements BeerRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BeerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> get(FilterOptions filterOptions) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder queryStr = new StringBuilder("from Beer");
            ArrayList<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            filterOptions.getName().ifPresent(value -> {
                filters.add(" name like: beerName ");
                params.put("beerName", String.format("%%%s%%", value));
            });
            filterOptions.getMinAbv().ifPresent(value -> {
                filters.add(" abv >= :minAbv ");
                params.put("minAbv", value);
            });
            filterOptions.getMaxAbv().ifPresent(value -> {
                filters.add(" abv <= :maxAbv ");
                params.put("maxAbv", value);
            });
            filterOptions.getStyleId().ifPresent(value -> {
                filters.add(" style.id = :styleId ");
                params.put("styleId", value);
            });

            if (!filters.isEmpty())
                queryStr.append(" where ").append(String.join(" and", filters));
            queryStr.append(generateOrderBy(filterOptions));
            Query<Beer> query = session.createQuery(queryStr.toString(), Beer.class);
            query.setProperties(params);
            return query.list();
        }
    }

    private String generateOrderBy(FilterOptions filterOptions) {
        if (filterOptions.getSortBy().isEmpty())
            return "";
        String orderBy = "";
        switch (filterOptions.getSortBy().get()) {
            case "name":
                orderBy = "name";
                break;
            case "abv":
                orderBy = "abv";
                break;
            case "style":
                orderBy = "style.id";
                break;
        }
        orderBy = String.format(" order by %s", orderBy);
        if (filterOptions.getSortOrder().isPresent() && filterOptions.getSortOrder().get().equals("desc"))
            orderBy = String.format("%s desc", orderBy);
        return orderBy;
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
            session.beginTransaction();
            session.persist(beer);
            session.getTransaction().commit();
        }
    }

    @Override
    public Beer update(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(beer);
            session.getTransaction().commit();
            return beer;
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    private List<Beer> filterBeers(String name, Double maxAbv, Double minAbv, Integer styleId, String
            sortParam, String sortOrderParam, List<Beer> result) {
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