package by.bsuir.tsiarokhin.booking.exceptionmappers;

import by.bsuir.tsiarokhin.booking.exceptions.InitializationException;
import by.bsuir.tsiarokhin.booking.models.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Yauheni Tsiarokhin on 6/1/17.
 */
@Provider
public class InitializationExceptionMapper implements ExceptionMapper<InitializationException> {

    @Override
    public Response toResponse(InitializationException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getClass().getSimpleName(), e.getMessage());
        return Response.status(Response.Status.CONFLICT)
                .entity(errorMessage)
                .build();
    }
}