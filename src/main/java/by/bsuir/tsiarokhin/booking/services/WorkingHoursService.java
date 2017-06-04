package by.bsuir.tsiarokhin.booking.services;

import by.bsuir.tsiarokhin.booking.models.Schedule;
import by.bsuir.tsiarokhin.booking.models.WorkingHours;

public class WorkingHoursService {

    private Schedule schedule = Schedule.getInstance();

    public WorkingHours getWorkingHours() {
        return schedule.getWorkingHours();
    }

    public WorkingHours postWorkingHours(WorkingHours workingHours) {
        return schedule.setWorkingHours(workingHours);
    }
}