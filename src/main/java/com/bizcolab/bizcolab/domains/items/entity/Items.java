package com.bizcolab.bizcolab.domains.items.entity;

import com.bizcolab.bizcolab.common.enums.ToolType;
import com.bizcolab.bizcolab.domains.groups.entity.Groups;
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

@Entity(name = "items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items {
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

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groups group;

    @Builder
    public Items (ToolType toolType, String name, Groups group, Projects project, TaskIntegrationConfigurations taskIntegrationConfiguration) {
        this.toolType = toolType;
        this.name = name;
        this.project = project;
        this.group = group;
        this.configuration = taskIntegrationConfiguration;
    }
}
