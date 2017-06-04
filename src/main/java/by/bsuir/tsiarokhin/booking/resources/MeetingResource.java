package by.bsuir.tsiarokhin.booking.resources;

import by.bsuir.tsiarokhin.booking.models.Meeting;
import by.bsuir.tsiarokhin.booking.services.LinkService;
import by.bsuir.tsiarokhin.booking.services.MeetingService;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MeetingResource {

    private MeetingService meetingService = new MeetingService();
    private LinkService linkService = new LinkService();

    @GET
    public Response getMeetings(@Context UriInfo uriInfo) {
        Map<String, Set<Meeting>> meetings = meetingService.getMeetings();
        Response.Status status = (meetings == null)
                ? Response.Status.NO_CONTENT :  Response.Status.OK;
        return Response.status(status)
                .header(LinkService.SELF, linkService.getMeetingsUri(uriInfo))
                .header(LinkService.SCHEDULE, linkService.getScheduleUri(uriInfo))
                .entity(meetings)
                .build();
    }

    @POST
    public Response postMeetings(List<Meeting> meetings) {
        meetingService.postMeetings(meetings);
        System.out.println(meetings);
        return Response.status(Response.Status.CREATED)
                .build();
    }
}