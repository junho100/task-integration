package com.bizcolab.bizcolab.domains.task_integration_configurations.model.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetMondayApiKeyWithLastUpdatedAtDto {
    private String apiKey;
    private LocalDateTime lastUpdatedAt;
    private Long configId;
}
