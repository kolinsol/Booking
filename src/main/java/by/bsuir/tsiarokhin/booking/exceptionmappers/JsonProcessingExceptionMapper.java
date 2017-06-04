package by.bsuir.tsiarokhin.booking.exceptionmappers;

import by.bsuir.tsiarokhin.booking.models.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {

    @Override
    public Response toResponse(JsonProcessingException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getClass().getSimpleName(), e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
    }
}