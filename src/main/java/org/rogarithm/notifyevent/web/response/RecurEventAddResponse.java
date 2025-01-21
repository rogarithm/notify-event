package org.rogarithm.notifyevent.web.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RecurEventAddResponse {
    @JsonProperty("evt_name")
    private final String eventName;

    @JsonCreator
    public RecurEventAddResponse(@JsonProperty("evt_name") String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
