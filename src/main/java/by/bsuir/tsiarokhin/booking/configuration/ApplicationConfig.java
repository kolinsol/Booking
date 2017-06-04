package by.bsuir.tsiarokhin.booking.configuration;

import by.bsuir.tsiarokhin.booking.services.LinkService;
import by.bsuir.tsiarokhin.booking.services.MeetingService;
import by.bsuir.tsiarokhin.booking.services.ScheduleService;
import by.bsuir.tsiarokhin.booking.services.WorkingHoursService;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Yauheni Tsiarokhin on 6/3/17.
 */
//@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        register(LinkService.class);
        register(ScheduleService.class);
        register(MeetingService.class);
        register(WorkingHoursService.class);
        register(new ApplicationBinder());
        packages("by.bsuir.tsiarokhin.booking.services");
    }
}
