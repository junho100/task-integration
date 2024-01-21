package com.bizcolab.bizcolab.domains.projects.entity;

import com.bizcolab.bizcolab.common.enums.ToolType;
import com.bizcolab.bizcolab.domains.task_integration_configurations.entity.TaskIntegrationConfigurations;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "projects")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private ToolType toolType;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "task_integration_configuration_id")
    private TaskIntegrationConfigurations configuration;

    @Builder
    public Projects (ToolType toolType, String name, TaskIntegrationConfigurations configurations) {
        this.toolType = toolType;
        this.name = name;
        this.configuration = configurations;
    }
}
