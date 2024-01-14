package com.bizcolab.bizcolab.domains.task_integration_configurations.model.command;

import com.bizcolab.bizcolab.common.enums.ToolType;
import com.bizcolab.bizcolab.domains.task_integration_configurations.model.request.AddConfigRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateTaskIntegrationConfigCommand {
    private ToolType toolType;
    private String apiKey;
    private String accessToken;

    public static CreateTaskIntegrationConfigCommand of(AddConfigRequest addConfigRequest, String providerName) {
        return CreateTaskIntegrationConfigCommand.builder()
            .toolType(ToolType.valueOf(providerName))
            .apiKey(addConfigRequest.getApiKey())
            .accessToken(addConfigRequest.getAccessToken())
            .build();
    }

}
