package com.bizcolab.bizcolab.domains.items;

import com.bizcolab.bizcolab.common.enums.ToolType;
import com.bizcolab.bizcolab.domains.groups.GroupService;
import com.bizcolab.bizcolab.domains.groups.entity.Groups;
import com.bizcolab.bizcolab.domains.items.entity.Items;
import com.bizcolab.bizcolab.domains.items.entity.MondayItems;
import com.bizcolab.bizcolab.domains.items.model.command.CreateMondayItemCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final MondayItemRepository mondayItemRepository;
    private final GroupService groupService;

    public void createMondayItems (List<CreateMondayItemCommand> createMondayItemCommandList) {
        for (CreateMondayItemCommand createMondayItemCommand : createMondayItemCommandList) {
            Groups group = groupService.getGroupById(createMondayItemCommand.getGroupId());

            Items item = itemRepository.save(Items.builder()
                .toolType(ToolType.MONDAY)
                .name(createMondayItemCommand.getName())
                .group(group)
                .build());

            mondayItemRepository.save(MondayItems.builder()
                .item_id(item.getId())
                .idInTool(createMondayItemCommand.getIdInTool())
                .item(item)
                .build());
        }
    }

    public void createMondayItem (Long groupId, String name, String idInTool) {
        Groups group = groupService.getGroupById(groupId);

        Items item = itemRepository.save(Items.builder()
            .toolType(ToolType.MONDAY)
            .name(name)
            .group(group)
            .build());

        mondayItemRepository.save(MondayItems.builder()
            .item_id(item.getId())
            .idInTool(idInTool)
            .item(item)
            .build());
    }
}
