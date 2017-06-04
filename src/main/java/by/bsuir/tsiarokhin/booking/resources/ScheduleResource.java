package by.bsuir.tsiarokhin.booking.resources;

import by.bsuir.tsiarokhin.booking.models.Schedule;
import by.bsuir.tsiarokhin.booking.services.LinkService;
import by.bsuir.tsiarokhin.booking.services.ScheduleService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/schedule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ScheduleResource {

    private LinkService linkService = new LinkService();
    private ScheduleService scheduleService = new ScheduleService();

    @GET
    public Response getSchedule(@Context UriInfo uriInfo) {
        Schedule schedule = scheduleService.getSchedule();
        Response.Status status = (schedule == null)
                ? Response.Status.NO_CONTENT :  Response.Status.OK;
        return Response.status(status)
                .header(LinkService.SELF, linkService.getScheduleUri(uriInfo))
                .header(LinkService.MEETINGS, linkService.getMeetingsUri(uriInfo))
                .header(LinkService.WORKING_HOURS, linkService.getWorkingHoursUri(uriInfo))
                .entity(schedule)
                .build();
    }

    @Path("/meetings")
    public MeetingResource getMeetingsResource() {
        return new MeetingResource();
    }

    @Path("/workinghours")
    public WorkingHoursResource getWorkingHoursResource() {
        return new WorkingHoursResource();
    }
}