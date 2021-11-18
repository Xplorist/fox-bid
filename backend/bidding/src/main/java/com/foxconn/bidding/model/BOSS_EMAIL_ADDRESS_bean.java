package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 老闆郵箱地址bean
 */
@Data
public class BOSS_EMAIL_ADDRESS_bean {
    private String pkid;//主鍵id
    private String email;//郵箱地址
    private String list_order;//列表序號
    private String create_date;//創建時間
}
