package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 公告表DO
 */
@Data
public class BulletinDO {
    private String id;//公告表id
    private String create_time;//創建時間
    private String update_time;//修改時間
    private String list_content;//列表內容
    private Integer list_order;//列表序號
}
