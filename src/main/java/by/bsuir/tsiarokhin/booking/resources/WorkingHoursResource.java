package by.bsuir.tsiarokhin.booking.resources;

import by.bsuir.tsiarokhin.booking.models.WorkingHours;
import by.bsuir.tsiarokhin.booking.services.LinkService;
import by.bsuir.tsiarokhin.booking.services.WorkingHoursService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by Yauheni Tsiarokhin on 5/31/17.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkingHoursResource {

    private WorkingHoursService workingHoursService = new WorkingHoursService();
    private LinkService linkService = new LinkService();

    @GET
    public Response getWorkingHours(@Context UriInfo uriInfo) {
        WorkingHours workingHours = workingHoursService.getWorkingHours();
        Response.Status status = (workingHours == null)
                ? Response.Status.NO_CONTENT :  Response.Status.OK;
        return Response.status(status)
                .header(LinkService.SELF, linkService.getWorkingHoursUri(uriInfo))
                .header(LinkService.SCHEDULE, linkService.getScheduleUri(uriInfo))
                .entity(workingHours)
                .build();
    }

    @POST
    public Response postWorkingHours(WorkingHours workingHours) {
        return Response.status(Response.Status.CREATED)
                .entity(workingHoursService.postWorkingHours(workingHours))
                .build();
    }
}