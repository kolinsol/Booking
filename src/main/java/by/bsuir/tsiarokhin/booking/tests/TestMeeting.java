package by.bsuir.tsiarokhin.booking.tests;

import by.bsuir.tsiarokhin.booking.models.Meeting;
import by.bsuir.tsiarokhin.booking.models.WorkingHours;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestMeeting extends TestRules{

    @Test
    public void testMeetingIsValid() {

        Meeting okayMeeting = new Meeting(LocalDateTime.now().minusHours(2),
                LocalDate.now().plusDays(1),
                LocalTime.of(12,0), 2, "Peter");
        Meeting submissionAfterNowMeeting = new Meeting(LocalDateTime.now().plusHours(2),
                LocalDate.now().plusDays(1),
                LocalTime.of(12,0), 2, "Peter");
        Meeting startAfterNowMeeting = new Meeting(LocalDateTime.now().minusDays(2),
                LocalDate.now().minusDays(1),
                LocalTime.of(12,0), 2, "Peter");
        Meeting mismatchingTimesMeeting = new Meeting(LocalDateTime.now().minusDays(2),
                LocalDate.now().plusDays(1),
                LocalTime.of(12,0), -2, "Peter");
        Meeting sameTimeMeeting = new Meeting(LocalDateTime.now().minusDays(2),
                LocalDate.now().plusDays(1),
                LocalTime.of(12,0), 0, "Peter");

        Assert.assertTrue(okayMeeting.isValid());
        Assert.assertFalse(submissionAfterNowMeeting.isValid());
        Assert.assertFalse(startAfterNowMeeting.isValid());
        Assert.assertFalse(mismatchingTimesMeeting.isValid());
        Assert.assertFalse(sameTimeMeeting.isValid());
    }

    @Test
    public void testMeetingIsOvertime() {

        WorkingHours workingHours = new WorkingHours(LocalTime.of(8, 0),
                LocalTime.of(17, 0));
        Meeting earlyMeeting = new Meeting(LocalDateTime.of(2017, 10, 10, 12, 30, 30),
                LocalDate.of(2017, 11, 12),
                LocalTime.of(6,0), 2, "Peter");
        Meeting okayMeeting = new Meeting(LocalDateTime.of(2017, 10, 10, 12, 30, 30),
                LocalDate.of(2017, 11, 12),
                LocalTime.of(10,0), 2, "Peter");
        Meeting lateMeeting = new Meeting(LocalDateTime.of(2017, 10, 10, 12, 30, 30),
                LocalDate.of(2017, 11, 12),
                LocalTime.of(16,0), 2, "Peter");
        Meeting nightMeeting = new Meeting(LocalDateTime.of(2017, 10, 10, 12, 30, 30),
                LocalDate.of(2017, 11, 12),
                LocalTime.of(20,0), 2, "Peter");

        Assert.assertTrue(earlyMeeting.isOvertime(workingHours));
        Assert.assertFalse(okayMeeting.isOvertime(workingHours));
        Assert.assertTrue(lateMeeting.isOvertime(workingHours));
        Assert.assertTrue(nightMeeting.isOvertime(workingHours));
    }
}
