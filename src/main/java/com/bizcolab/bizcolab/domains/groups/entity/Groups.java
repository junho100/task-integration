package com.bizcolab.bizcolab.domains.groups.entity;

import com.bizcolab.bizcolab.common.enums.ToolType;
import com.bizcolab.bizcolab.domains.projects.entity.Projects;
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

@Entity(name = "task_groups")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private ToolType toolType;

    @Column
    private String name;

    @Column(columnDefinition = "text")
    private String updateHash;

    @ManyToOne
    @JoinColumn(name = "task_integration_configuration_id")
    private TaskIntegrationConfigurations configuration;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;

    @Builder
    public Groups (ToolType toolType, String name, TaskIntegrationConfigurations configuration, Projects project) {
        this.toolType = toolType;
        this.name = name;
        this.configuration = configuration;
        this.project = project;
    }
}
