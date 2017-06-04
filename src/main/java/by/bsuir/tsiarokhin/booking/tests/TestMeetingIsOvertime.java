package by.bsuir.tsiarokhin.booking.tests;

import by.bsuir.tsiarokhin.booking.models.Meeting;
import by.bsuir.tsiarokhin.booking.models.WorkingHours;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestMeetingIsOvertime extends TestRules{

    private WorkingHours workingHours = new WorkingHours(LocalTime.of(8, 0),
            LocalTime.of(17, 0));
    private Meeting earlyMeeting = new Meeting(LocalDateTime.of(2017, 10, 10, 12, 30, 30),
            LocalDate.of(2017, 11, 12),
            LocalTime.of(6,0), 2, "Peter");
    private Meeting okayMeeting = new Meeting(LocalDateTime.of(2017, 10, 10, 12, 30, 30),
            LocalDate.of(2017, 11, 12),
            LocalTime.of(10,0), 2, "Peter");
    private Meeting lateMeeting = new Meeting(LocalDateTime.of(2017, 10, 10, 12, 30, 30),
            LocalDate.of(2017, 11, 12),
            LocalTime.of(16,0), 2, "Peter");
    private Meeting nightMeeting = new Meeting(LocalDateTime.of(2017, 10, 10, 12, 30, 30),
            LocalDate.of(2017, 11, 12),
            LocalTime.of(20,0), 2, "Peter");

    @Test
    public void testMeetingIsOvertime() {
        Assert.assertTrue(earlyMeeting.isOvertime(workingHours));
        Assert.assertFalse(okayMeeting.isOvertime(workingHours));
        Assert.assertTrue(lateMeeting.isOvertime(workingHours));
        Assert.assertTrue(nightMeeting.isOvertime(workingHours));
    }
}
