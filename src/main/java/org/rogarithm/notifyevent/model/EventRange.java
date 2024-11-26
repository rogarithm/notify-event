package org.rogarithm.notifyevent.model;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Embeddable
public class EventRange {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public EventRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public EventRange() {

    }

    public static EventRange of1Day(LocalDate date) {
        return new EventRange(LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public String toString() {
        return this.startDateTime.toString() + " - " + this.endDateTime.toString();
    }
}
