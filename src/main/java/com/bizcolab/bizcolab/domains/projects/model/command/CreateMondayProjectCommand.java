package com.bizcolab.bizcolab.domains.projects.model.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateMondayProjectCommand {
    private String name;
    private String idInTool;
}
