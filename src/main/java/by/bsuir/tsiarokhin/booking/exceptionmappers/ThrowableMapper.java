package by.bsuir.tsiarokhin.booking.exceptionmappers;

import by.bsuir.tsiarokhin.booking.models.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by Yauheni Tsiarokhin on 6/1/17.
 */
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable t) {
        ErrorMessage errorMessage = new ErrorMessage(t.getClass().getSimpleName(), t.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
    }
}