package org.rogarithm.notifyevent.web.request;

public class RecurEventAddRequest {
    private String desc;
    private RecurType recurType;
    private RecurParams recurParams;

    public RecurEventAddRequest(String desc, RecurType recurType, RecurParams recurParams) {
        this.desc = desc;
        this.recurType = recurType;
        this.recurParams = recurParams;
    }

    public RecurEventAddRequest() {
    }

    public String getDesc() {
        return desc;
    }

    public RecurType getRecurType() {
        return recurType;
    }

    public RecurParams getRecurParams() {
        return recurParams;
    }
}
