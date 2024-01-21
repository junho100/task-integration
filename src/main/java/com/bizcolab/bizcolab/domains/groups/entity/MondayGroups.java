package com.bizcolab.bizcolab.domains.groups.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "monday_groups")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MondayGroups {
    @Id
    private Long group_id;

    @Column
    private String idInTool;

    @OneToOne
    @MapsId
    private Groups group;

    @Builder
    public MondayGroups (Long group_id, String idInTool, Groups group) {
        this.group_id = group_id;
        this.idInTool = idInTool;
        this.group = group;
    }
}
