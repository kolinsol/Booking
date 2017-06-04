package by.bsuir.tsiarokhin.booking.models;

import by.bsuir.tsiarokhin.booking.deserializers.TimeDeserializer;
import by.bsuir.tsiarokhin.booking.serializers.TimeSerializer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalTime;

public class WorkingHours {

    @JsonSerialize(using = TimeSerializer.class)
    @JsonDeserialize(using = TimeDeserializer.class)
    private LocalTime openingTime;

    @JsonSerialize(using = TimeSerializer.class)
    @JsonDeserialize(using = TimeDeserializer.class)
    private LocalTime closingTime;

    @JsonCreator
    public WorkingHours(@JsonProperty("openingTime") LocalTime openingTime,
                        @JsonProperty("closingTime") LocalTime closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    @JsonIgnore
    public Boolean isValid() {
        return getClosingTime().isAfter(getOpeningTime());
    }
}