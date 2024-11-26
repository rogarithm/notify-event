package org.rogarithm.notifyevent.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private EventRange eventRange;
    private String description;

    public Event(EventRange eventRange, String description) {
        this.eventRange = eventRange;
        this.description = description;
    }

    public Event() {

    }

    public static Event of1Day(LocalDate eventDate, String description) {
        return new Event(EventRange.of1Day(eventDate), description);
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
