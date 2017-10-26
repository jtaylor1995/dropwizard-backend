package com.mycompany.resources;

import com.mycompany.api.Event;
import com.mycompany.core.EventDao;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("events")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {


    private final EventDao eventDao;

    public EventResource(EventDao eventDao) {
        this.eventDao = eventDao;
    }


    @POST
    @UnitOfWork
    public Event create(Event event) {
        return eventDao.create(event);

    }

    @GET
    @UnitOfWork
    public List<Event> allEvent() {
        return eventDao.findAll();
    }


}
