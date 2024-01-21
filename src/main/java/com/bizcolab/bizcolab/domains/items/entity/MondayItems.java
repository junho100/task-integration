package com.bizcolab.bizcolab.domains.items.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "monday_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MondayItems {
    @Id
    private Long item_id;

    @Column
    private String idInTool;

    @OneToOne
    @MapsId
    private Items item;

    @Builder
    public MondayItems (Long item_id, String idInTool, Items item) {
        this.item_id = item_id;
        this.idInTool = idInTool;
        this.item = item;
    }
}
