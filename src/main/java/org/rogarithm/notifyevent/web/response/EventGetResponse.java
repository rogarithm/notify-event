package org.rogarithm.notifyevent.web.response;

import org.rogarithm.notifyevent.model.Event;

import java.time.LocalDateTime;

public class EventGetResponse {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    public EventGetResponse(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public static EventGetResponse from(Event event) {
        return new EventGetResponse(
                event.getEventRange().getStartDateTime(),
                event.getEventRange().getEndDateTime(),
                event.getDescription()
        );
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public String getDescription() {
        return description;
    }
}
