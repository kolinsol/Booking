package by.bsuir.tsiarokhin.booking.exceptions;

import javax.ws.rs.ext.Provider;

/**
 * Created by Yauheni Tsiarokhin on 5/31/17.
 */
public class InitializationException extends RuntimeException {

    public InitializationException(String message) {
        super(message);
    }
}