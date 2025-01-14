package org.rogarithm.notifyevent.web.request;

public class RecurParams {
    private String dayIdx;
    private String cnt;

    public RecurParams(String dayIdx, String cnt) {
        this.dayIdx = dayIdx;
        this.cnt = cnt;
    }

    public RecurParams() {
    }

    public String getDayIdx() {
        return dayIdx;
    }

    public String getCnt() {
        return cnt;
    }
}
