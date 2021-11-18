package com.foxconn.bidding.model;

import lombok.Data;

import java.util.List;

/**
 * 報價主表bean
 */
@Data
public class GIVE_PRICE_MSTR_bean {
    private String pkid;//主鍵id
    private String bill_pkid;//訂單表id
    private String recv_user_pkid;//接單用戶id
    private String give_price_slav_rel_id;//報價從表關聯id
    private Integer total_price;//總報價
    private String deliver_date;//交貨日期
    private String descp;//理由說明
    private String f_win_bid;//是否中標標誌（Y:是，N:否）
    private String create_date;//創建日期

    // 非表中字段
    private List<GIVE_PRICE_SLAV_bean> slav_list;// 報價明細list（從表list）
    private String sort_way;// 排序方式（complex: 綜合排序，price: 報價排序，date: 交期排序）
    private USER_INFO_bean recv_user;// 接單用戶
    private String f_valid;// 有效標誌（Y-有效，能選； N-無效，不能選）

    // 分頁查詢參數字段
    private Integer pageIndex;// 分頁頁碼
    private Integer pageSize;// 分頁每頁數據條數

    // 分頁查詢返回字段
    private Integer row_total;// 總行數

    // 綜合排序字段
    private Float diff_ratio;// 價差比
}
