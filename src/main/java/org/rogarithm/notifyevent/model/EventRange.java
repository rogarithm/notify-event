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
        return new EventRange(LocalDateTime.of(date, LocalTime.of(0,0,0)), LocalDateTime.of(date, LocalTime.of(23,59,59)));
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

    public boolean includes(LocalDate date) {
        EventRange rangeToCompare = EventRange.of1Day(date);

        if (this.startDateTime.isAfter(rangeToCompare.getEndDateTime())) {
            return false;
        }
        if (this.endDateTime.isBefore(rangeToCompare.getStartDateTime())) {
            return false;
        }
        return true;
    }
}
