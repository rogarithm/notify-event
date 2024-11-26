package org.rogarithm.notifyevent.web.request;

import java.time.LocalDateTime;

public class EventAddRequest {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    public EventAddRequest(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public EventAddRequest() {
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
