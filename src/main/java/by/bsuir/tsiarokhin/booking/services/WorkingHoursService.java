package by.bsuir.tsiarokhin.booking.services;

import by.bsuir.tsiarokhin.booking.models.Schedule;
import by.bsuir.tsiarokhin.booking.models.WorkingHours;

/**
 * Created by Yauheni Tsiarokhin on 5/31/17.
 */
public class WorkingHoursService {

    private LinkService linkService = new LinkService();
    private Schedule schedule = Schedule.getInstance();

    public WorkingHours getWorkingHours() {
        return schedule.getWorkingHours();
    }

    public WorkingHours postWorkingHours(WorkingHours workingHours) {
        return schedule.setWorkingHours(workingHours);
    }
}