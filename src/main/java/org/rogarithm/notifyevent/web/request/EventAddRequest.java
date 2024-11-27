package org.rogarithm.notifyevent.web.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventAddRequest {
    private EventType eventType;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    private String description;

    public EventAddRequest(
            EventType eventType,
            LocalDate startDate,
            LocalDate endDate,
            LocalTime startTime,
            LocalTime endTime,
            String description
    ) {
        this.eventType = eventType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public EventAddRequest() {
    }

    public EventType getEventType() {
        return eventType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }
}
