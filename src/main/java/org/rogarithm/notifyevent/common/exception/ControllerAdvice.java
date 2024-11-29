package org.rogarithm.notifyevent.common.exception;

import org.rogarithm.notifyevent.exception.EventException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    protected ErrorResponse handleBusinessException(final EventException exception) {
        return new ErrorResponse(exception.getErrorCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ErrorResponse handleHttpMessageNotReadableException(final HttpMessageNotReadableException exception) {
        return new ErrorResponse(ErrorCode.INVALID_REQUEST_BODY);
    }

    private static class ErrorResponse {
        private final String code;
        private final String reason;

        private ErrorResponse(ErrorCode errorCode) {
            this.code = errorCode.getCode();
            this.reason = errorCode.getReason();
        }

        public String getCode() {
            return this.code;
        }
        public String getReason() {
            return this.reason;
        }
    }
}
