package by.bsuir.tsiarokhin.booking.tests;

import by.bsuir.tsiarokhin.booking.models.Meeting;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestMeetingIsValid extends TestRules{

    private Meeting okayMeeting = new Meeting(LocalDateTime.now().minusHours(2),
            LocalDate.now().plusDays(1),
            LocalTime.of(12,0), 2, "Peter");
    private Meeting submissionAfterNowMeeting = new Meeting(LocalDateTime.now().plusHours(2),
            LocalDate.now().plusDays(1),
            LocalTime.of(12,0), 2, "Peter");
    private Meeting startAfterNowMeeting = new Meeting(LocalDateTime.now().minusDays(2),
            LocalDate.now().minusDays(1),
            LocalTime.of(12,0), 2, "Peter");
    private Meeting mismatchingTimesMeeting = new Meeting(LocalDateTime.now().minusDays(2),
            LocalDate.now().plusDays(1),
            LocalTime.of(12,0), -2, "Peter");
    private Meeting sameTimeMeeting = new Meeting(LocalDateTime.now().minusDays(2),
            LocalDate.now().plusDays(1),
            LocalTime.of(12,0), 0, "Peter");

    @Test
    public void testMeetingIsValid() {
        Assert.assertTrue(okayMeeting.isValid());
        Assert.assertFalse(submissionAfterNowMeeting.isValid());
        Assert.assertFalse(startAfterNowMeeting.isValid());
        Assert.assertFalse(mismatchingTimesMeeting.isValid());
        Assert.assertFalse(sameTimeMeeting.isValid());
    }
}
