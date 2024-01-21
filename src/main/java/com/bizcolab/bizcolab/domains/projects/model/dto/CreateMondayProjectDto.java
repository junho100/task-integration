package com.bizcolab.bizcolab.domains.projects.model.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateMondayProjectDto {
    // idInTool -> key, id -> value
    private Map<String, Long> idMap;
}
