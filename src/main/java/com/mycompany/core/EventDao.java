package com.mycompany.core;

import com.mycompany.api.Event;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class EventDao extends AbstractDAO<Event> {

    public EventDao(SessionFactory factory) {
        super(factory);
    }

    public Event findById(Long id) {
        return get(id);
    }

    public Event create(Event event) {
        return persist(event);
    }

    public List<Event> findAll() {
        return list(namedQuery("com.mycompany.api.Event.findAll"));
    }
}
