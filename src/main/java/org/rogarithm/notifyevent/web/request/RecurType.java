package org.rogarithm.notifyevent.web.request;

public enum RecurType {
    DAY_IN_MONTH("DAY_IN_MONTH");

    private final String type;

    RecurType(String type) {
        this.type = type;
    }

    public static RecurType of(String type) {
        return RecurType.valueOf(type);
    }
}
