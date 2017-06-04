package by.bsuir.tsiarokhin.booking.exceptions;

public class MeetingInitializationException extends InitializationException {

    public static final String OVERLAP = "Meetings are overlapping";
    public static final String OVERTIME = "Meeting is out of working hours period";
    public static final String INVALID = "Meeting time is not valid";

    public MeetingInitializationException(String message) {
        super(message);
    }
}
