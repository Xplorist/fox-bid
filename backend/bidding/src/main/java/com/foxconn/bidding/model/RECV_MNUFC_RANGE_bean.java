package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 接單方加工範圍表bean
 */
@Data
public class RECV_MNUFC_RANGE_bean {
    private String pkid;// 主鍵id
    private String name;// 接單方加工範圍名稱
    private String recv_mnufc_range_rel_id;// 接單方加工範圍關聯id
    private String create_date;// 創建時間
    private Integer list_order;// 列表序號
}