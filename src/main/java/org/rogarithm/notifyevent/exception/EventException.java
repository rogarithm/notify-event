package org.rogarithm.notifyevent.exception;

import org.rogarithm.notifyevent.common.exception.ErrorCode;

public class EventException extends RuntimeException {

    private final ErrorCode errorCode;

    public EventException(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
