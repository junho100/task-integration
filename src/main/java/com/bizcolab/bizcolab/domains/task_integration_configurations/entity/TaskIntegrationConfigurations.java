package com.bizcolab.bizcolab.domains.task_integration_configurations.entity;

import com.bizcolab.bizcolab.common.enums.ToolType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "task_integration_configurations")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskIntegrationConfigurations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private ToolType toolType;

    @Builder
    public TaskIntegrationConfigurations (ToolType toolType) {
        this.toolType = toolType;
    }
}
