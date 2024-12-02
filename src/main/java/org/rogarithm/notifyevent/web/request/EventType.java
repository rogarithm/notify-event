package org.rogarithm.notifyevent.web.request;

import java.util.List;
import java.util.stream.Stream;

public enum EventType {
    HAS_TIME, HAS_NO_TIME;

    public static List<String> getNames() {
        return Stream.of(EventType.values())
                .map(EventType::name)
                .toList();
    }
}
