package org.rogarithm.notifyevent.model;

import java.time.LocalDate;

public class Event {
    private final EventRange eventRange;
    private final String description;

    public Event(EventRange eventRange, String description) {
        this.eventRange = eventRange;
        this.description = description;
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
}
