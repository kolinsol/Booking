package by.bsuir.tsiarokhin.booking.services;

import by.bsuir.tsiarokhin.booking.models.Schedule;

import javax.ws.rs.core.UriInfo;

/**
 * Created by Yauheni Tsiarokhin on 5/30/17.
 */
public class ScheduleService {

    private Schedule schedule = Schedule.getInstance();

    public Schedule getSchedule() {
        if (schedule.getWorkingHours() == null) {
            return null;
        } else {
            return schedule;
        }
    }
}
