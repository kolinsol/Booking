package by.bsuir.tsiarokhin.booking.exceptions;

public class WorkingHoursInitializationException extends InitializationException {

    public static final String NOT_INITIALIZED = "Working hours are not initialized";
    public static final String ALREADY_INITIALIZED = "Working hours are already initialized";
    public static final String NOT_MATCHING_TIMES = "Opening and closing times don't match";

    public WorkingHoursInitializationException(String message) {
        super(message);
    }
}
