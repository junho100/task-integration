package com.bizcolab.bizcolab.domains.projects;

import com.bizcolab.bizcolab.common.enums.ToolType;
import com.bizcolab.bizcolab.common.exception.BaseException;
import com.bizcolab.bizcolab.common.exception.BaseExceptionStatus;
import com.bizcolab.bizcolab.domains.projects.entity.MondayProjects;
import com.bizcolab.bizcolab.domains.projects.entity.Projects;
import com.bizcolab.bizcolab.domains.projects.model.command.CreateMondayProjectCommand;
import com.bizcolab.bizcolab.domains.projects.model.dto.CreateMondayProjectDto;
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
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final MondayProjectRepository mondayProjectRepository;

    public CreateMondayProjectDto createMondayProjects (List<CreateMondayProjectCommand> createMondayProjectCommandList) {
        Map<String, Long> idMap = new HashMap<>();
        for (CreateMondayProjectCommand createMondayProjectCommand : createMondayProjectCommandList) {
            Projects project = projectRepository.save(
                Projects.builder()
                .name(createMondayProjectCommand.getName())
                .toolType(ToolType.MONDAY)
                .build());

            mondayProjectRepository.save(MondayProjects.builder()
                .idInTool(createMondayProjectCommand.getIdInTool())
                .project(project)
                .build());

            idMap.put(createMondayProjectCommand.getIdInTool(), project.getId());
        }

        return CreateMondayProjectDto.builder()
            .idMap(idMap)
            .build();
    }

    public Projects getProjectById(Long projectId) {
        Optional<Projects> project = projectRepository.findById(projectId);

        if (!project.isPresent()) {
            throw new BaseException(BaseExceptionStatus.PROJECT_NOT_FOUND);
        }

        return project.get();
    }

    public boolean checkIsExistsByIdInTool(String idInTool) {
        return mondayProjectRepository.existsByIdInTool(idInTool);
    }

    public void createMondayProject(String idInTool, String name) {
        Projects project = projectRepository.save(
            Projects.builder()
            .name(name)
            .toolType(ToolType.MONDAY)
            .build());

        mondayProjectRepository.save(MondayProjects.builder()
            .idInTool(idInTool)
            .project(project)
            .build());
    }

    public Projects getProjectByIdInTool(String idInTool) {
        Optional<MondayProjects> mondayProject = mondayProjectRepository.findByIdInTool(idInTool);

        if (!mondayProject.isPresent()) {
            throw new BaseException(BaseExceptionStatus.PROJECT_NOT_FOUND);
        }

        return mondayProject.get().getProject();
    }
}
