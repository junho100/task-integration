package com.bizcolab.bizcolab.common.response;

import org.springframework.stereotype.Service;

@Service
public class BaseResponseService {
    public <T> BaseResponse createSuccessResponse(T data) {
        return BaseResponse.builder()
            .isSuccess(true)
            .result(data)
            .build();
    }
}