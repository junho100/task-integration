package com.bizcolab.bizcolab.domains.items.model.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateMondayItemCommand {
    private Long groupId;
    private String idInTool;
    private String name;
}
