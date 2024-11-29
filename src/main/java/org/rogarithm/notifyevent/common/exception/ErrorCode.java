package org.rogarithm.notifyevent.common.exception;

public enum ErrorCode {

    BAD_REQUEST("400", "잘못된 요청"),
    NOT_EXISTS("404", "리소스가 존재하지 않음"),
    INVALID_REQUEST_BODY("400", "처리할 수 없는 Request Body")
    ;

    private final String code;
    private final String reason;

    ErrorCode(String code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public String getCode() {
        return this.code;
    }

    public String getReason() {
        return this.reason;
    }
}
