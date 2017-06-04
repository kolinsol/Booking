package by.bsuir.tsiarokhin.booking.exceptionmappers;

import by.bsuir.tsiarokhin.booking.models.ErrorMessage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getClass().getSimpleName(), e.getMessage());
        return Response.status(e.getResponse().getStatus())
                .entity(errorMessage)
                .build();
    }
}