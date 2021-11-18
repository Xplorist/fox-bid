package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 接單方評價
 */
@Data
public class RECV_EVAL_bean {
    private String pkid;//主鍵id
    private String bill_pkid;//訂單表id
    private String recv_user_pkid;//評價用戶id（接單方）
    private String send_user_pkid;//被評價用戶id（發單方）
    private Integer summary_score;//綜合評價分數
    private Integer pay_rate_score;//付款時效分數
    private Integer svc_atitu_score;//服務態度分數
    private String summary_text;//綜合評價文字
    private String f_anomus;//匿名評價標誌（Y:是，N:否）
    private String create_date;//創建時間

    // 分頁需要的字段
    private Integer page_total;// 分頁總頁數
    private Integer row_total;// 分頁數據總條數
    private Integer row_num;// 分頁當前數據在第幾條

    // 查詢發單方收到的評價平均分字段
    private String pay_rate_avg;// 付款時效平均分
    private String svc_atitu_avg;// 服務態度平均分

    // 用戶信息
    private USER_INFO_bean send_user;// 發單方用戶信息
    private USER_INFO_bean recv_user;// 接單方用戶信息
    private USER_INFO_bean user;// 不區分接單發單的用戶信息
}
