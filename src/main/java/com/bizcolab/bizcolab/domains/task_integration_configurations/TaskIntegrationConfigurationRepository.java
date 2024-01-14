package com.bizcolab.bizcolab.domains.task_integration_configurations;

import com.bizcolab.bizcolab.domains.task_integration_configurations.entity.TaskIntegrationConfigurations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskIntegrationConfigurationRepository extends JpaRepository<TaskIntegrationConfigurations, Long> {
}
