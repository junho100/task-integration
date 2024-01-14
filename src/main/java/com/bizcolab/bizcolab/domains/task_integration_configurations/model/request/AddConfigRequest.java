package com.bizcolab.bizcolab.domains.task_integration_configurations.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddConfigRequest {
    private String apiKey;
    private String accessToken;
}
