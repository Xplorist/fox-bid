package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 接單用戶統計數據
 */
@Data
public class RecvUserStatistics {
    private Integer recv_amount;// 接單量
    private Long offer_money;// 報價金額
    private Long win_bid_money;// 中標金額
    private Float win_bid_rate;// 競標成功率
    private Float user_score;// 用戶評分
    private String latest_login_time;// 最近登錄時間
    private String latest_recv_bill_time;// 最近接單時間
}
