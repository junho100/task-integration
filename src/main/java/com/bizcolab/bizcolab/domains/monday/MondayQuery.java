package com.bizcolab.bizcolab.domains.monday;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MondayQuery {
    BOARDS_ITEMS_GROUP("query { boards { id name updated_at groups { id title } items_page (query_params : {order_by : {column_id : \"__last_updated__\", direction : desc}}) { items { id name updated_at group { id title } } } } }");
    private String query;
}
