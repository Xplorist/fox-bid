package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 發單用戶統計數據
 */
@Data
public class SendUserStatistics {
    private Integer send_amount;// 發單量
    private Long send_money;// 發單金額
    private Long pick_money;// 選標金額
    private Float abandon_rate;// 棄標率
    private Float flow_rate;// 流標率
    private Float pick_rate;// 選標率
    private Float user_score;// 客戶評分
    private String latest_login_time;// 最近登錄時間
    private String latest_send_bill_time;// 最近發單時間
    private Float reputation;// 信用
}
