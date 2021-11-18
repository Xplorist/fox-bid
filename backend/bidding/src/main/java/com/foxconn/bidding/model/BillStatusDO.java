package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 訂單狀態表DO
 */
@Data
public class BillStatusDO {
    private String id;//狀態id
    private String name;//狀態名稱
    private Integer list_order;//列表序號
    private String create_time;//創建時間
    private String update_time;//修改時間
}
