package by.bsuir.tsiarokhin.booking.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestMeetingIsOvertime.class,
        TestMeetingIsValid.class,
        TestWorkingHoursIsValid.class
})
public class TestPrimer {
}
