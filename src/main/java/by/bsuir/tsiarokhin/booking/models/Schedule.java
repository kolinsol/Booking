package by.bsuir.tsiarokhin.booking.models;

import by.bsuir.tsiarokhin.booking.exceptions.MeetingInitializationException;
import by.bsuir.tsiarokhin.booking.exceptions.WorkingHoursInitializationException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by Yauheni Tsiarokhin on 5/30/17.
 */
public class Schedule {

    private static Schedule instance = new Schedule();

    private WorkingHours workingHours;
    private Map<String, Set<Meeting>> meetings;

    private Schedule() {
        meetings = new TreeMap<>();
    }

    public static Schedule getInstance() {
        return instance;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public WorkingHours setWorkingHours(WorkingHours workingHours) {
        if (this.workingHours == null) {
            if (workingHours.getClosingTime().isAfter(workingHours.getOpeningTime())
                    && !workingHours.getOpeningTime().equals(workingHours.getClosingTime())) {
                this.workingHours = workingHours;
                return workingHours;
            } else {
                throw new WorkingHoursInitializationException(WorkingHoursInitializationException.NOT_MATCHING_TIMES);
            }
        } else {
            throw new WorkingHoursInitializationException(WorkingHoursInitializationException.ALREADY_INITIALIZED);
        }
    }

    public Map<String, Set<Meeting>> getMeetings() {
        return meetings;
    }

    public Meeting addMeeting(Meeting meeting) {
        String meetingDate = meeting.getMeetingDate().toString();
        if (isValid(meeting) && !isOvertime(meeting)) {
            if (meetings.containsKey(meetingDate)) {
                if (meetings.get(meetingDate).add(meeting)) {
                    return meeting;
                } else {
                    throw new MeetingInitializationException(MeetingInitializationException.OVERLAP);
                }
            } else {
                meetings.put(meetingDate, new TreeSet<>());
                if (meetings.get(meetingDate).add(meeting)) {
                    return meeting;
                };
            }
        }
        return null;
    }

    private Boolean isOvertime(Meeting meeting) {
        Boolean result =  meeting.getStartTime().isBefore(workingHours.getOpeningTime())
                || meeting.getEndTime().isAfter(workingHours.getClosingTime());
        if (result) {
            throw new MeetingInitializationException(MeetingInitializationException.OVERTIME);
        } else {
            return result;
        }
    }

    private Boolean isValid(Meeting meeting) {
        Boolean result = LocalDateTime.of(meeting.getMeetingDate(), meeting.getStartTime()).isAfter(LocalDateTime.now())
                && meeting.getEndTime().isAfter(meeting.getStartTime());
        if (!result) {
            throw new MeetingInitializationException(MeetingInitializationException.INVALID);
        } else {
            return result;
        }
    }
}