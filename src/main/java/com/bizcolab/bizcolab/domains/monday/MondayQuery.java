package com.bizcolab.bizcolab.domains.monday;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MondayQuery {
    BOARDS_AND_ITEMS_WITH_GROUP("query { boards { id name groups { id title } items_page { items { id name group { id } } } } }");
    private String query;
}
