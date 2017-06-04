package by.bsuir.tsiarokhin.booking.services;

import by.bsuir.tsiarokhin.booking.models.Schedule;

public class ScheduleService {

    private Schedule schedule = Schedule.getInstance();

    public Schedule getSchedule() {
        return (schedule.getWorkingHours() == null) ? null : schedule;
    }
}
