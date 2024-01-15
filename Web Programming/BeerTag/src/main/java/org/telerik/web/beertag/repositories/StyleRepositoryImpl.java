package org.telerik.web.beertag.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.telerik.web.beertag.exceptions.EntityNotFoundException;
import org.telerik.web.beertag.models.Style;

import java.util.List;
//import java.util.List;

@Repository
public class StyleRepositoryImpl implements StyleRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public StyleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Style> get() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Style ", Style.class).list();
        }
    }

    @Override
    public Style get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Style style = session.get(Style.class, id);
            if (style == null) {
                throw new EntityNotFoundException("Style", id);
            }
            return style;
        }
    }
}
