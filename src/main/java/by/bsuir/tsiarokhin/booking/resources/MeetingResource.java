package by.bsuir.tsiarokhin.booking.resources;

import by.bsuir.tsiarokhin.booking.models.Meeting;
import by.bsuir.tsiarokhin.booking.services.LinkService;
import by.bsuir.tsiarokhin.booking.services.MeetingService;
import org.glassfish.jersey.server.Uri;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yauheni Tsiarokhin on 5/30/17.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MeetingResource {

    private MeetingService meetingService =new MeetingService();
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
    public Response postMeeting(Meeting meeting) {
            return Response.status(Response.Status.CREATED)
                    .entity(meetingService.postMeeting(meeting))
                    .build();
    }
}