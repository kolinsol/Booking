package by.bsuir.tsiarokhin.booking.models;

import by.bsuir.tsiarokhin.booking.exceptions.MeetingInitializationException;
import by.bsuir.tsiarokhin.booking.exceptions.WorkingHoursInitializationException;

import java.time.LocalDateTime;
import java.util.*;

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

    public void addMeetings(List<Meeting> meetingsToAdd) {
        for (Meeting meeting: meetingsToAdd) {
            String meetingDate = meeting.getMeetingDate().toString();
            if (isValid(meeting) && !isOvertime(meeting)) {
                if (meetings.containsKey(meetingDate)) {
                    meetings.get(meetingDate).add(meeting);
                } else {
                    meetings.put(meetingDate, new TreeSet<>());
                    meetings.get(meetingDate).add(meeting);
                }
            }
        }
    }

    private Boolean isOvertime(Meeting meeting) {
        return meeting.getStartTime().isBefore(workingHours.getOpeningTime())
                || meeting.getEndTime().isAfter(workingHours.getClosingTime());
    }

    private Boolean isValid(Meeting meeting) {
        return LocalDateTime.of(meeting.getMeetingDate(), meeting.getStartTime()).isAfter(LocalDateTime.now())
                && meeting.getEndTime().isAfter(meeting.getStartTime())
                && meeting.getSubmissionTime().isBefore(LocalDateTime.now())
                && meeting.getSubmissionTime()
                .isBefore(LocalDateTime.of(meeting.getMeetingDate(), meeting.getStartTime()));
    }
}