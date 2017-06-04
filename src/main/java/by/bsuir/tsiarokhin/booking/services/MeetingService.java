package by.bsuir.tsiarokhin.booking.services;

import by.bsuir.tsiarokhin.booking.exceptions.WorkingHoursInitializationException;
import by.bsuir.tsiarokhin.booking.models.Meeting;
import by.bsuir.tsiarokhin.booking.models.Schedule;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MeetingService {

    private Schedule schedule = Schedule.getInstance();

    public void postMeetings(List<Meeting> meetings) {
        if (schedule.getWorkingHours() == null) {
            throw new WorkingHoursInitializationException(WorkingHoursInitializationException.NOT_INITIALIZED);
        }
        schedule.addMeetings(meetings);
    }

    public Map<String, Set<Meeting>> getMeetings() {
        Map<String, Set<Meeting>> meetings = schedule.getMeetings();
        if (meetings.isEmpty()) {
            return null;
        } else {
            return meetings;
        }

    }
}