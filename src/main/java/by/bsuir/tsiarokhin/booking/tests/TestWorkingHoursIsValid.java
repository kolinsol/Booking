package by.bsuir.tsiarokhin.booking.tests;

import by.bsuir.tsiarokhin.booking.models.WorkingHours;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class TestWorkingHoursIsValid extends TestRules {

    private WorkingHours okayWorkingHours = new WorkingHours(LocalTime.of(8, 0),
            LocalTime.of(17, 0));
    private WorkingHours mismatchingTimeWorkingHours = new WorkingHours(LocalTime.of(20, 0),
            LocalTime.of(10, 0));
    private WorkingHours sameTimeHours = new WorkingHours(LocalTime.of(10, 0),
            LocalTime.of(10, 0));

    @Test
    public void testWorkingHoursIsValid() {
        Assert.assertTrue(okayWorkingHours.isValid());
        Assert.assertFalse(mismatchingTimeWorkingHours.isValid());
        Assert.assertFalse(sameTimeHours.isValid());
    }
}
