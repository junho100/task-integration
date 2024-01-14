package com.bizcolab.bizcolab.domains.task_integration_configurations;

import com.bizcolab.bizcolab.common.response.BaseResponse;
import com.bizcolab.bizcolab.common.response.BaseResponseService;
import com.bizcolab.bizcolab.domains.task_integration_configurations.model.command.CreateTaskIntegrationConfigCommand;
import com.bizcolab.bizcolab.domains.task_integration_configurations.model.request.AddConfigRequest;
import com.bizcolab.bizcolab.domains.task_integration_configurations.model.response.AddConfigResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task-configs")
@RequiredArgsConstructor
public class TaskConfigController {
    private final TaskConfigService taskConfigService;
    private final BaseResponseService baseResponseService;

    @PostMapping("/{providerName}/add-config")
    public ResponseEntity<BaseResponse<AddConfigResponse>> addConfig(@RequestBody AddConfigRequest addConfigRequest, @PathVariable String providerName) {
        String validatedProviderName = taskConfigService.validateProviderName(providerName);

        CreateTaskIntegrationConfigCommand createTaskIntegrationConfigCommand = CreateTaskIntegrationConfigCommand.of(addConfigRequest, validatedProviderName);
        Long taskIntegrationConfigurationsId = taskConfigService.createTaskIntegrationConfig(createTaskIntegrationConfigCommand);

        taskConfigService.addInitialDataByConfigId(taskIntegrationConfigurationsId, validatedProviderName);

        AddConfigResponse addConfigResponse = AddConfigResponse.builder()
            .configId(taskIntegrationConfigurationsId)
            .build();
        BaseResponse<AddConfigResponse> response = baseResponseService.createSuccessResponse(addConfigResponse);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
