package com.bizcolab.bizcolab.domains.monday.model.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetBoardAndGroupResponse {
    @Getter
    @Setter
    @Builder
    public static class BoardData {
        private String name;
        private List<GroupData> groups;
    }

    @Getter
    @Setter
    @Builder
    public static class GroupData {
        private String id;
        private String title;
    }
}
