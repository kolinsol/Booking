package by.bsuir.tsiarokhin.booking.configuration;

import by.bsuir.tsiarokhin.booking.services.LinkService;
import by.bsuir.tsiarokhin.booking.services.MeetingService;
import by.bsuir.tsiarokhin.booking.services.ScheduleService;
import by.bsuir.tsiarokhin.booking.services.WorkingHoursService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by Yauheni Tsiarokhin on 6/3/17.
 */
public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bindAsContract(LinkService.class);
        bindAsContract(ScheduleService.class);
        bindAsContract(MeetingService.class);
        bindAsContract(WorkingHoursService.class);
    }
}
