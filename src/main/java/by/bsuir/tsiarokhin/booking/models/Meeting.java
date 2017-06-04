package by.bsuir.tsiarokhin.booking.models;

import by.bsuir.tsiarokhin.booking.deserializers.DateDeserializer;
import by.bsuir.tsiarokhin.booking.deserializers.DateTimeDeserializer;
import by.bsuir.tsiarokhin.booking.deserializers.TimeDeserializer;
import by.bsuir.tsiarokhin.booking.serializers.TimeSerializer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonPropertyOrder({
        "startTime",
        "endTime",
        "employeeId"})
public class Meeting implements Comparable<Meeting> {

    @JsonDeserialize(using = DateTimeDeserializer.class)
    private LocalDateTime submissionTime;

    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate meetingDate;

    @JsonSerialize(using = TimeSerializer.class)
    @JsonDeserialize(using = TimeDeserializer.class)
    private LocalTime startTime;

    @JsonSerialize(using = TimeSerializer.class)
    @JsonDeserialize(using = TimeDeserializer.class)
    private LocalTime endTime;

    private String employeeId;

    @JsonCreator
    public Meeting(@JsonProperty("submissionDate") LocalDateTime submissionTime,
                   @JsonProperty("meetingDate") LocalDate meetingDate,
                   @JsonProperty("startTime") LocalTime startTime,
                   @JsonProperty("duration") Integer duration,
                   @JsonProperty("employeeId") String employeeId) {
        this.submissionTime = submissionTime;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.endTime = startTime.plusHours(duration);
        this.employeeId = employeeId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @JsonIgnore
    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    @JsonIgnore
    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Boolean isOvertime(WorkingHours workingHours) {
        return getStartTime().isBefore(workingHours.getOpeningTime())
                || getEndTime().isAfter(workingHours.getClosingTime())
                || getStartTime().isAfter(workingHours.getClosingTime())
                || getEndTime().isBefore(workingHours.getOpeningTime());
    }

    @JsonIgnore
    public Boolean isValid() {
        return LocalDateTime.of(getMeetingDate(), getStartTime()).isAfter(LocalDateTime.now())
                && getEndTime().isAfter(getStartTime())
                && getSubmissionTime().isBefore(LocalDateTime.now());
    }

    /**
     * Sorts Meeting entities depending on
     * 1) submissionTime (doesn't let to add two meetings submitted at the same moment
     * 2) startTime and endTime (doesn't let to add overlapping meeting)
     */
    @Override
    public int compareTo(Meeting o) {
        if (submissionTime.equals(o.submissionTime)) return 0;
        if (startTime.isAfter(o.endTime) || startTime.equals(o.endTime)) return 1;
        if (endTime.isBefore(o.startTime) || endTime.equals(o.startTime)) return -1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meeting meeting = (Meeting) o;

        if (submissionTime.equals(meeting.submissionTime)) return true;
        if (!submissionTime.equals(meeting.submissionTime)) return false;
        if (!meetingDate.equals(meeting.meetingDate)) return false;
        if (!startTime.equals(meeting.startTime)) return false;
        if (!endTime.equals(meeting.endTime)) return false;
        return employeeId.equals(meeting.employeeId);
    }

    @Override
    public int hashCode() {
        int result = submissionTime.hashCode();
        result = 31 * result + meetingDate.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        result = 31 * result + employeeId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "submissionTime=" + submissionTime +
                ", meetingDate=" + meetingDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}