package com.bizcolab.bizcolab.domains.groups;

import com.bizcolab.bizcolab.common.enums.ToolType;
import com.bizcolab.bizcolab.common.exception.BaseException;
import com.bizcolab.bizcolab.common.exception.BaseExceptionStatus;
import com.bizcolab.bizcolab.domains.groups.entity.Groups;
import com.bizcolab.bizcolab.domains.groups.entity.MondayGroups;
import com.bizcolab.bizcolab.domains.groups.model.dto.CreateMondayGroupDto;
import com.bizcolab.bizcolab.domains.projects.ProjectService;
import com.bizcolab.bizcolab.domains.projects.entity.Projects;
import com.bizcolab.bizcolab.domains.projects.model.command.CreateMondayGroupCommand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {
    private final MondayGroupRepository mondayGroupRepository;
    private final GroupRepository groupRepository;
    private final ProjectService projectService;

    public CreateMondayGroupDto createMondayGroups(List<CreateMondayGroupCommand> createMondayGroupCommandList) {
        Map<String, Long> idMap = new HashMap<>();
        for (CreateMondayGroupCommand createMondayGroupCommand : createMondayGroupCommandList) {
            Projects project = projectService.getProjectById(createMondayGroupCommand.getProjectId());

            Groups group = groupRepository.save(Groups.builder()
                .toolType(ToolType.MONDAY)
                .name(createMondayGroupCommand.getName())
                .toolType(ToolType.MONDAY)
                .project(project)
                .build());

            mondayGroupRepository.save(MondayGroups.builder()
                .group_id(group.getId())
                .idInTool(createMondayGroupCommand.getIdInTool())
                .group(group)
                .build());

            idMap.put(createMondayGroupCommand.getIdInTool(), group.getId());
        }

        return CreateMondayGroupDto.builder()
            .idMap(idMap)
            .build();
    }

    public Groups getGroupById(Long groupId) {
        Optional<Groups> group = groupRepository.findById(groupId);

        if (!group.isPresent()) {
            throw new BaseException(BaseExceptionStatus.GROUP_NOT_FOUND);
        }

        return group.get();
    }
}
