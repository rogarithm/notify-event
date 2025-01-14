package org.rogarithm.notifyevent.web.request;

public class RecurEventAddRequest {
    private String desc;
    private String recurType;
    private RecurParams recurParams;

    public RecurEventAddRequest(String desc, String recurType, RecurParams recurParams) {
        this.desc = desc;
        this.recurType = recurType;
        this.recurParams = recurParams;
    }

    public RecurEventAddRequest() {
    }

    public String getDesc() {
        return desc;
    }

    public String getRecurType() {
        return recurType;
    }

    public RecurParams getRecurParams() {
        return recurParams;
    }
}

/*
json 형태로 다음과 같은 요청을 보내면, spring이 적절하게 RecurEventAddRequest 타입 요청 객체로 바꿀 수 있다
recurType을 enum으로 바꾸기

{
  "desc": "golf games",
  "recur_type": "DayInMonth",
  "recur_params": {
    "day_idx": 1,
    "cnt": 1
  }
}
 */
