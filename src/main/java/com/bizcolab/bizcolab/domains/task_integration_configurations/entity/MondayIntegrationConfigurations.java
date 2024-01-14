package com.bizcolab.bizcolab.domains.task_integration_configurations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "monday_integration_configurations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MondayIntegrationConfigurations {
    @Id
    Long task_integration_configuration_id;

    @Column(columnDefinition = "text")
    private String apiKey;

    @OneToOne
    @MapsId
    private TaskIntegrationConfigurations configurations;

    @Builder
    public MondayIntegrationConfigurations(String apiKey, TaskIntegrationConfigurations configurations) {
        this.apiKey = apiKey;
        this.configurations = configurations;
    }
}
