package com.bizcolab.bizcolab.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BaseResponse<T> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Boolean isSuccess;
    private T result;
    private String message;

    public String convertToJson() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }
}
