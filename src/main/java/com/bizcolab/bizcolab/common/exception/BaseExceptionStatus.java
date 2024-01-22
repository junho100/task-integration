package com.bizcolab.bizcolab.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseExceptionStatus {
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "invalid date format."),
    GROUP_NOT_FOUND(HttpStatus.NOT_FOUND, "group not found."),
    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "project not found."),
    NOT_DEV_YET(HttpStatus.BAD_REQUEST,"not yet."),
    CONFIG_NOT_FOUND(HttpStatus.NOT_FOUND, "config not found."),
    INVALID_TASK_PROVIDER_NAME(HttpStatus.BAD_REQUEST, "invalid task provider name.");
    private final HttpStatus status;
    private final String message;

    private BaseExceptionStatus(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
