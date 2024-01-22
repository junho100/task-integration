package com.bizcolab.bizcolab.domains.monday;

import com.bizcolab.bizcolab.common.utils.DateService;
import com.bizcolab.bizcolab.domains.groups.GroupService;
import com.bizcolab.bizcolab.domains.groups.model.dto.CreateMondayGroupDto;
import com.bizcolab.bizcolab.domains.items.ItemService;
import com.bizcolab.bizcolab.domains.items.model.command.CreateMondayItemCommand;
import com.bizcolab.bizcolab.domains.monday.model.response.MondayGetBoardsAndItemsWithGroupsRes;
import com.bizcolab.bizcolab.domains.projects.ProjectService;
import com.bizcolab.bizcolab.domains.projects.model.command.CreateMondayGroupCommand;
import com.bizcolab.bizcolab.domains.projects.model.command.CreateMondayProjectCommand;
import com.bizcolab.bizcolab.domains.projects.model.dto.CreateMondayProjectDto;
import com.bizcolab.bizcolab.domains.task_integration_configurations.TaskConfigService;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MondayService {
    private final WebClient webClient;
    private final ProjectService projectService;
    private final GroupService groupService;
    private final ItemService itemService;
    private final DateService dateService;


    public void addInitialDataByApiKey(String apiKey) {
        GraphQLRequest request = GraphQLRequest.builder()
                .query(MondayQuery.BOARDS_ITEMS_GROUP.getQuery()).build();
        MondayGetBoardsAndItemsWithGroupsRes response = webClient.post()
             .header("Authorization", apiKey)
             .bodyValue(request.getRequestBody()).retrieve().bodyToMono(
                MondayGetBoardsAndItemsWithGroupsRes.class).block();

        List<CreateMondayProjectCommand> createMondayProjectCommandList = MondayGetBoardsAndItemsWithGroupsRes.toCreateMondayProjectCommandList(response);
        CreateMondayProjectDto createMondayProjectDto = projectService.createMondayProjects(createMondayProjectCommandList);

        List<CreateMondayGroupCommand> createMondayGroupCommandList = MondayGetBoardsAndItemsWithGroupsRes.toCreateMondayBoardCommandList(createMondayProjectDto, response);
        CreateMondayGroupDto createMondayGroupDto = groupService.createMondayGroups(createMondayGroupCommandList);

        List<CreateMondayItemCommand> createMondayItemCommandList = MondayGetBoardsAndItemsWithGroupsRes.toCreateMondayItemCommandList(createMondayGroupDto, response);
        itemService.createMondayItems(createMondayItemCommandList);
    }

    public void syncMondayDataSinceLastUpdatedAt(String apiKey, LocalDateTime lastUpdatedAt, Long taskConfigId) {
        GraphQLRequest request = GraphQLRequest.builder()
            .query(MondayQuery.BOARDS_ITEMS_GROUP.getQuery()).build();
        MondayGetBoardsAndItemsWithGroupsRes response = webClient.post()
            .header("Authorization", apiKey)
            .bodyValue(request.getRequestBody()).retrieve().bodyToMono(
                MondayGetBoardsAndItemsWithGroupsRes.class).block();
        for (MondayGetBoardsAndItemsWithGroupsRes.Board board : response.getData().getBoards()) {
            for (MondayGetBoardsAndItemsWithGroupsRes.Item item : board.getItems_page().getItems()) {
                LocalDateTime itemUpdatedAt = dateService.convertUTCStringToLocalDateTime(item.getUpdated_at());
                if (!itemUpdatedAt.isAfter(lastUpdatedAt)) {
                    break;
                }
                if (!projectService.checkIsExistsByIdInTool(board.getId())){
                    projectService.createMondayProject(board.getId(), board.getName());
                }
                if (!groupService.checkIsExistsByIdInTool(item.getGroup().getId())){
                    Long projectId = projectService.getProjectByIdInTool(board.getId()).getId();
                    groupService.createMondayGroup(item.getGroup().getId(), item.getGroup().getTitle(), projectId);
                }
                Long groupId = groupService.getGroupByIdInTool(item.getGroup().getId()).getId();
                itemService.createMondayItem(groupId, item.getName(), item.getId());
            }
        }
    }
}
