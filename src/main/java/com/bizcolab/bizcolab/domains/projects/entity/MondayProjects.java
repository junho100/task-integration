package com.bizcolab.bizcolab.domains.projects.entity;

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
@Entity(name = "monday_projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MondayProjects {
    @Id
    private Long project_id;

    @Column
    private String idInTool;

    @OneToOne
    @MapsId
    private Projects project;

    @Builder
    public MondayProjects(String idInTool, Projects project) {
        this.idInTool = idInTool;
        this.project = project;
    }
}
