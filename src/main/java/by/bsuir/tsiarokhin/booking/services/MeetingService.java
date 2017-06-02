package by.bsuir.tsiarokhin.booking.services;

import by.bsuir.tsiarokhin.booking.exceptions.InitializationException;
import by.bsuir.tsiarokhin.booking.exceptions.WorkingHoursInitializationException;
import by.bsuir.tsiarokhin.booking.models.Meeting;
import by.bsuir.tsiarokhin.booking.models.Schedule;

import java.util.Map;
import java.util.Set;

/**
 * Created by Yauheni Tsiarokhin on 5/31/17.
 */
public class MeetingService {

    private Schedule schedule = Schedule.getInstance();

    public Meeting postMeeting(Meeting meeting) {
        if (schedule.getWorkingHours() == null) {
            throw new WorkingHoursInitializationException(WorkingHoursInitializationException.NOT_INITIALIZED);
        }
        return schedule.addMeeting(meeting);
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