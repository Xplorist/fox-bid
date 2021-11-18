package com.foxconn.bidding.model;

import lombok.Data;

import java.util.List;

@Data
public class QueryCondition {
    private String scope;// 名稱
    private String val;// 值
    private String id;// id
    private Boolean active;// 是否有效
    private QueryConditionSub checkedItem;// 被選中的子項
    private List<QueryConditionSub> sub;// 子項
}
