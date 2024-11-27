package org.rogarithm.notifyevent.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private EventRange eventRange;
    private String description;

    public Event(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.eventRange = new EventRange(startDateTime, endDateTime);
        this.description = description;
    }

    public Event() {

    }

    public static Event of1Day(LocalDate eventDate, String description) {
        EventRange eventRange = EventRange.of1Day(eventDate);
        return new Event(eventRange.getStartDateTime(), eventRange.getEndDateTime(), description);
    }

    public EventRange getEventRange() {
        return this.eventRange;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getId() {
        return this.id;
    }
}
