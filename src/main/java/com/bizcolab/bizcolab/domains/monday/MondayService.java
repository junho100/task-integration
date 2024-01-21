package com.bizcolab.bizcolab.domains.monday;

import com.bizcolab.bizcolab.domains.groups.GroupService;
import com.bizcolab.bizcolab.domains.groups.model.dto.CreateMondayGroupDto;
import com.bizcolab.bizcolab.domains.items.ItemService;
import com.bizcolab.bizcolab.domains.items.model.command.CreateMondayItemCommand;
import com.bizcolab.bizcolab.domains.monday.model.response.MondayGetBoardsAndItemsWithGroupsRes;
import com.bizcolab.bizcolab.domains.projects.ProjectService;
import com.bizcolab.bizcolab.domains.projects.model.command.CreateMondayGroupCommand;
import com.bizcolab.bizcolab.domains.projects.model.command.CreateMondayProjectCommand;
import com.bizcolab.bizcolab.domains.projects.model.dto.CreateMondayProjectDto;
import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
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

    public void addInitialDataByApiKey(String apiKey) {
        GraphQLRequest request = GraphQLRequest.builder()
                .query(MondayQuery.BOARDS_AND_ITEMS_WITH_GROUP.getQuery()).build();
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
}
