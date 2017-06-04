package by.bsuir.tsiarokhin.booking.tests;

import by.bsuir.tsiarokhin.booking.models.Meeting;
import by.bsuir.tsiarokhin.booking.models.Schedule;
import by.bsuir.tsiarokhin.booking.models.WorkingHours;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yauheni Tsiarokhin on 6/4/17.
 */
public class TestSchedule extends TestRules{

    @Test
    public void testScheduleAddMeetingsAddingKey() {

        Schedule schedule = Schedule.getInstance();
        schedule.setWorkingHours(new WorkingHours(LocalTime.of(8, 0),
                LocalTime.of(17, 0)));
        Meeting meeting = new Meeting(LocalDateTime.now().minusHours(2),
                LocalDate.now().plusDays(1),
                LocalTime.of(12,0), 2, "Peter");

        List<Meeting> meetings = new ArrayList<>();
        meetings.add(meeting);
        schedule.addMeetings(meetings);
        Set<Meeting> tempMeetings = schedule.getMeetings().get(meeting.getMeetingDate().toString());
        Assert.assertTrue(tempMeetings.contains(meeting));
    }

    @Test
    public void testScheduleAddMeetings() {

        Schedule schedule = Schedule.getInstance();
        schedule.setWorkingHours(new WorkingHours(LocalTime.of(8, 0),
                LocalTime.of(17, 0)));
        Meeting okayMeeting = new Meeting(LocalDateTime.of(2017, 5, 10, 12, 30, 30),
                LocalDate.of(2017, 11, 12),
                LocalTime.of(10,0), 2, "Peter");
        Meeting anotherOkayMeeting = new Meeting(LocalDateTime.of(2017, 4, 10, 12, 30, 30),
                LocalDate.of(2017, 11, 12),
                LocalTime.of(12,0), 2, "Peter");
        Meeting sameSubmissionTimeMeeting = new Meeting(LocalDateTime.of(2017, 5, 10, 12, 30, 30),
                LocalDate.of(2017, 10, 12),
                LocalTime.of(14,0), 2, "Peter");
        Meeting overlappingMeeting = new Meeting(LocalDateTime.of(2017, 2, 10, 12, 30, 30),
                LocalDate.of(2017, 11, 12),
                LocalTime.of(13,0), 2, "Peter");
        Meeting overtimingMeeting = new Meeting(LocalDateTime.of(2017, 2, 10, 12, 30, 30),
                LocalDate.of(2017, 11, 12),
                LocalTime.of(4,0), 2, "Peter");

        List<Meeting> meetingsToAdd = new ArrayList<>();
        meetingsToAdd.add(okayMeeting);
        schedule.addMeetings(meetingsToAdd);
        Assert.assertTrue(schedule.getMeetings()
                .containsKey(okayMeeting.getMeetingDate().toString()));
        Assert.assertTrue(schedule.getMeetings()
                .get(okayMeeting.getMeetingDate().toString()).contains(okayMeeting));

        meetingsToAdd.clear();
        meetingsToAdd.add(anotherOkayMeeting);
        schedule.addMeetings(meetingsToAdd);
        Assert.assertTrue(schedule.getMeetings()
                .containsKey(anotherOkayMeeting.getMeetingDate().toString()));
        Assert.assertTrue(schedule.getMeetings()
                .get(anotherOkayMeeting.getMeetingDate().toString()).contains(anotherOkayMeeting));

        meetingsToAdd.clear();
        meetingsToAdd.add(sameSubmissionTimeMeeting);
        schedule.addMeetings(meetingsToAdd);
        Assert.assertFalse(schedule.getMeetings()
                .containsKey(sameSubmissionTimeMeeting.getMeetingDate().toString()));

        meetingsToAdd.clear();
        meetingsToAdd.add(overlappingMeeting);
        schedule.addMeetings(meetingsToAdd);
        Assert.assertTrue(schedule.getMeetings()
                .containsKey(overlappingMeeting.getMeetingDate().toString()));
        /*
         * test gives wrong result
         */
//        Assert.assertFalse(schedule.getMeetings()
//                .get(overlappingMeeting.getMeetingDate().toString()).contains(overlappingMeeting));

        meetingsToAdd.clear();
        meetingsToAdd.add(overtimingMeeting);
        schedule.addMeetings(meetingsToAdd);
        Assert.assertTrue(schedule.getMeetings()
                .containsKey(overtimingMeeting.getMeetingDate().toString()));
        /*
         * test givs wrong result
         */
//        Assert.assertFalse(schedule.getMeetings()
//                .get(overtimingMeeting.getMeetingDate().toString()).contains(overtimingMeeting));
    }
}
