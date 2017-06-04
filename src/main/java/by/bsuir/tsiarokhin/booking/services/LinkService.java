package by.bsuir.tsiarokhin.booking.services;

import by.bsuir.tsiarokhin.booking.resources.ScheduleResource;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class LinkService {

    public static final String SELF = "self";
    public static final String MEETINGS = "meetings";
    public static final String WORKING_HOURS = "workingHours";
    public static final String SCHEDULE = "schedule";

    public URI getScheduleUri(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(ScheduleResource.class)
                .build();
    }

    public URI getMeetingsUri(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(ScheduleResource.class)
                .path(ScheduleResource.class, "getMeetingsResource")
                .build();
    }

    public URI getWorkingHoursUri(UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(ScheduleResource.class)
                .path(ScheduleResource.class, "getWorkingHoursResource")
                .build();
    }
}