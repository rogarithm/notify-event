package org.rogarithm.notifyevent.web.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class EventAddDto {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    public EventAddDto(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
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

    public static EventAddDto of(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        return new EventAddDto(startDateTime, endDateTime, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventAddDto that = (EventAddDto) o;
        return Objects.equals(startDateTime, that.startDateTime) && Objects.equals(endDateTime, that.endDateTime) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, endDateTime, description);
    }
}
