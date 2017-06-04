package by.bsuir.tsiarokhin.booking.tests;

import by.bsuir.tsiarokhin.booking.models.WorkingHours;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class TestWorkingHours extends TestRules {

    @Test
    public void testWorkingHoursIsValid() {

        WorkingHours okayWorkingHours = new WorkingHours(LocalTime.of(8, 0),
                LocalTime.of(17, 0));
        WorkingHours mismatchingTimeWorkingHours = new WorkingHours(LocalTime.of(20, 0),
                LocalTime.of(10, 0));
        WorkingHours sameTimeHours = new WorkingHours(LocalTime.of(10, 0),
                LocalTime.of(10, 0));

        Assert.assertTrue(okayWorkingHours.isValid());
        Assert.assertFalse(mismatchingTimeWorkingHours.isValid());
        Assert.assertFalse(sameTimeHours.isValid());
    }
}
