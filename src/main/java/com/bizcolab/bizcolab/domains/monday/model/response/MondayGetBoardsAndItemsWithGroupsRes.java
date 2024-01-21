package com.bizcolab.bizcolab.domains.monday.model.response;

import com.bizcolab.bizcolab.domains.groups.model.dto.CreateMondayGroupDto;
import com.bizcolab.bizcolab.domains.items.model.command.CreateMondayItemCommand;
import com.bizcolab.bizcolab.domains.projects.model.command.CreateMondayGroupCommand;
import com.bizcolab.bizcolab.domains.projects.model.command.CreateMondayProjectCommand;
import com.bizcolab.bizcolab.domains.projects.model.dto.CreateMondayProjectDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MondayGetBoardsAndItemsWithGroupsRes {
    private Data data;
    private String account_id;

    public static List<CreateMondayProjectCommand> toCreateMondayProjectCommandList(MondayGetBoardsAndItemsWithGroupsRes mondayGetBoardsAndItemsWithGroupsRes) {
        return mondayGetBoardsAndItemsWithGroupsRes
            .getData()
            .getBoards()
            .stream()
            .map(Board::toCreateMondayProjectCommand)
            .toList();
    }

    public static List<CreateMondayGroupCommand> toCreateMondayBoardCommandList(CreateMondayProjectDto createMondayProjectDto, MondayGetBoardsAndItemsWithGroupsRes mondayGetBoardsAndItemsWithGroupsRes) {
        List<CreateMondayGroupCommand> createMondayBoardCommandList = new ArrayList<>();

        for (Board board : mondayGetBoardsAndItemsWithGroupsRes.getData().getBoards()) {
            for (Group group : board.getGroups()) {
                Long projectId = createMondayProjectDto.getIdMap().get(board.getId());
                createMondayBoardCommandList.add(CreateMondayGroupCommand.builder()
                    .projectId(projectId)
                    .idInTool(group.getId())
                    .name(group.getTitle())
                    .build());
            }
        }

        return createMondayBoardCommandList;
    }

    public static List<CreateMondayItemCommand> toCreateMondayItemCommandList(CreateMondayGroupDto createMondayGroupDto, MondayGetBoardsAndItemsWithGroupsRes mondayGetBoardsAndItemsWithGroupsRes){
        // Monday는 Item이 Group 외에 있는 것을 허용하지 않음
        List<CreateMondayItemCommand> createMondayItemCommandList = new ArrayList<>();

        for (Board board : mondayGetBoardsAndItemsWithGroupsRes.getData().getBoards()) {
            for (Item item : board.getItems_page().getItems()) {
                Long groupId = createMondayGroupDto.getIdMap().get(item.getGroup().getId());
                createMondayItemCommandList.add(CreateMondayItemCommand.builder()
                    .groupId(groupId)
                    .idInTool(item.getId())
                    .name(item.getName())
                    .build());
            }
        }

        return createMondayItemCommandList;
    }

    @Getter
    private static class Data {
        private List<Board> boards;
    }
    @Getter
    private static class Board {
        private String id;
        private String name;
        private List<Group> groups;
        private ItemPage items_page;

        private static CreateMondayProjectCommand toCreateMondayProjectCommand(Board board) {
            return CreateMondayProjectCommand.builder()
                .name(board.getName())
                .idInTool(board.getId())
                .build();
        }
    }
    @Getter
    private static class Group {
        private String id;
        private String title;

        private static CreateMondayGroupCommand toCreateMondayGroupCommand(Group group, Long projectId, String projectIdInTool) {
            return CreateMondayGroupCommand.builder()
                .projectId(projectId)
                .idInTool(group.getId())
                .name(group.getTitle())
                .build();
        }
    }
    @Getter
    private static class ItemPage {
        private List<Item> items;
    }
    @Getter
    private static class Item {
        private String id;
        private String name;
        private GroupWithOnlyId group;
    }
    @Getter
    private static class GroupWithOnlyId {
        private String id;
    }
}
