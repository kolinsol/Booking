package by.bsuir.tsiarokhin.booking.models;

import by.bsuir.tsiarokhin.booking.exceptions.MeetingInitializationException;
import by.bsuir.tsiarokhin.booking.exceptions.WorkingHoursInitializationException;

import java.time.LocalDateTime;
import java.util.*;

public class Schedule {

    private static Schedule instance = new Schedule();

    private WorkingHours workingHours;
    private Map<String, Set<Meeting>> meetings;
    private Set<LocalDateTime> submissionTimes;

    private Schedule() {
        meetings = new TreeMap<>();
        submissionTimes = new HashSet<>();
    }

    public static Schedule getInstance() {
        return instance;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }

    public WorkingHours setWorkingHours(WorkingHours workingHours) {
        if (this.workingHours == null) {
            if (workingHours.isValid()) {
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
            if (!submissionTimes.contains(meeting.getSubmissionTime())) {
                submissionTimes.add(meeting.getSubmissionTime());
                String meetingDate = meeting.getMeetingDate().toString();
                if (meeting.isValid() && !meeting.isOvertime(workingHours)) {
                    if (meetings.containsKey(meetingDate)) {
                        meetings.get(meetingDate).add(meeting);
                    } else {
                        meetings.put(meetingDate, new TreeSet<>());
                        meetings.get(meetingDate).add(meeting);
                    }
                }
            }
        }
    }
}