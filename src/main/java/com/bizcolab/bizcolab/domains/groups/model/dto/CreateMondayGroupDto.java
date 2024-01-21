package com.bizcolab.bizcolab.domains.groups.model.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateMondayGroupDto {
    // idInTool -> key, id -> value
    private Map<String, Long> idMap;
}
