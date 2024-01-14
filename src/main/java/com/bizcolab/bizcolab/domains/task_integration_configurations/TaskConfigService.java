package com.bizcolab.bizcolab.domains.task_integration_configurations;

import com.bizcolab.bizcolab.common.enums.ToolType;
import com.bizcolab.bizcolab.common.exception.BaseException;
import com.bizcolab.bizcolab.common.exception.BaseExceptionStatus;
import com.bizcolab.bizcolab.domains.monday.MondayService;
import com.bizcolab.bizcolab.domains.task_integration_configurations.entity.MondayIntegrationConfigurations;
import com.bizcolab.bizcolab.domains.task_integration_configurations.entity.TaskIntegrationConfigurations;
import com.bizcolab.bizcolab.domains.task_integration_configurations.model.command.CreateTaskIntegrationConfigCommand;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskConfigService {
    private final TaskIntegrationConfigurationRepository taskIntegrationConfigurationRepository;
    private final MondayIntegrationConfigurationsRepository mondayIntegrationConfigurationsRepository;
    private final MondayService mondayService;
    public String validateProviderName(String providerName) {
        if (EnumUtils.isValidEnum(ToolType.class, providerName.toUpperCase())) {
            return providerName.toUpperCase();
        }

        throw new BaseException(BaseExceptionStatus.INVALID_TASK_PROVIDER_NAME);
    }

    public Long createTaskIntegrationConfig(
        CreateTaskIntegrationConfigCommand createTaskIntegrationConfigCommand) {

        TaskIntegrationConfigurations taskIntegrationConfigurations = taskIntegrationConfigurationRepository.save(TaskIntegrationConfigurations.builder()
            .toolType(createTaskIntegrationConfigCommand.getToolType())
            .build());


        String providerName = String.valueOf(createTaskIntegrationConfigCommand.getToolType());
        String validatedProviderName = validateProviderName(providerName);
        if (validatedProviderName == ToolType.MONDAY.name()) {
            mondayIntegrationConfigurationsRepository.save(
                MondayIntegrationConfigurations.builder()
                    .apiKey(createTaskIntegrationConfigCommand.getApiKey())
                    .configurations(taskIntegrationConfigurations)
                    .build()
            );

            return taskIntegrationConfigurations.getId();
        }

        throw new BaseException(BaseExceptionStatus.NOT_DEV_YET);
    }

    public void addInitialDataByConfigId(Long configId, String providerName) {
        String validatedProviderName = validateProviderName(providerName);
        if (validatedProviderName.equals(ToolType.MONDAY.name())) {
            String apiKey = getMondayApiKeyByConfigId(configId);
            mondayService.addInitialDataByApiKey(apiKey);
        }
        if (validatedProviderName.equals(ToolType.JIRA.name())) {
        }
    }

    public String getMondayApiKeyByConfigId(Long configId) {
        Optional<MondayIntegrationConfigurations> configuration = mondayIntegrationConfigurationsRepository.findById(configId);

        if (configuration.isEmpty()){
            throw new BaseException(BaseExceptionStatus.CONFIG_NOT_FOUND);
        }

        return configuration.get().getApiKey();
    }
}
