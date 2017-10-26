package com.mycompany;

import com.mycompany.api.Event;
import com.mycompany.core.EventDao;
import com.mycompany.resources.EventResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.text.SimpleDateFormat;

public class EventsApplication extends Application<EventsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new EventsApplication().run(args);
    }

    @Override
    public String getName() {
        return "Events";
    }

    private final HibernateBundle<EventsConfiguration> hibernate = new HibernateBundle<EventsConfiguration>(Event.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(EventsConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<EventsConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final EventsConfiguration configuration,
                    final Environment environment) {
        environment.getObjectMapper().setDateFormat(new SimpleDateFormat(configuration.getDateFormat()));
        
        final EventDao eventDao = new EventDao(hibernate.getSessionFactory());
        environment.jersey().register(new EventResource(eventDao));
    }

}
