package by.bsuir.tsiarokhin.booking.models;

public class ErrorMessage {
    private String exception;
    private String message;

    public ErrorMessage(String exception, String message) {
        this.exception = exception;
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}