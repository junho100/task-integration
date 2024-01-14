package com.bizcolab.bizcolab.common.exception;

import com.bizcolab.bizcolab.common.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionAdvice {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Object>> handleBaseException(BaseException exception) {
        BaseResponse<Object> response = BaseResponse.builder()
            .isSuccess(false)
            .message(exception.getMessage())
            .build();
        return new ResponseEntity<>(response, exception.getStatus().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGeneralException(Exception exception) {
        BaseResponse<Object> response = BaseResponse.builder()
            .isSuccess(false)
            .message(exception.getMessage())
            .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
