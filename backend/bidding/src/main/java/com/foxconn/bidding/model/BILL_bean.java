package com.foxconn.bidding.model;

import lombok.Data;

import java.util.List;

/**
 * 訂單表bean
 */
@Data
public class BILL_bean {
    private String pkid;//主鍵id(由java後台生成)
    private String bill_no;//單號
    private String bill_status;//訂單狀態（0：待發佈【send保存後】，1：待報價【send發佈後】, 2：待發貨【中標後】，3：待收貨【recv發貨後】，4：待付款【send收貨後】，5：待收款（send付款後），6：已完成）
    private String send_user_pkid;//發單用戶id
    private String recv_user_pkid;//中標接單用戶id
    private String give_price_mstr_pkid;//中標報價表id
    private String pd_type;//用途（產品類型）
    private String deliver_date;//交貨時間
    private String deliver_address;//交貨地點
    private String tel;//聯繫電話
    private String f_show_tel;//顯示聯繫電話標誌（y:是，n:否）
    private Integer total_price;//接受總價
    private String money_type;//幣別
    private Integer amount;//需求數量
    private String bid_range;//開標範圍（類型）
    private String bid_start_date;//競價開始時間
    private String bid_end_date;//競價結束時間
    private String deliver_way;//交貨模式（物流費用）
    private String make_requ;//製作要求
    private String f_open_hide;//公開隱藏標誌（o:公開[open]，h:隱藏[hide]）
    private String part_doc_file_rel_id;//零件圖檔文件表關聯id
    private String create_date;//創建時間
    private String f_send_eval;// 發單評價標誌（Y：是，N：否）
    private String f_recv_eval;// 接單評價標誌（Y：是，N：否）
    private String publish_date;// 發佈時間
    private String pd_type_no;// 用途編號（產品類型）
    private String bid_range_value;// 開標範圍具體值
    private String f_bill_no;// 單號標誌（發佈訂單時生成單號前先更新此字段）

    // 非表中字段
    private List<PART_DOC_FILE_bean> file_list;// 文件list
    private USER_INFO_bean recv_user;// 中標接單用戶（包含中標單位）
    private Integer recv_user_num;// 參與投標的接單用戶個數（參與單位個數）
    private USER_INFO_bean send_user;// 發單用戶（需求單位）
    private List<USER_INFO_bean> give_recv_user_list;// 參與報價的接單用戶list
    private SEND_EVAL_bean send_eval;// 發單方評價
    private RECV_EVAL_bean recv_eval;// 接單方評價
    private List<RECV_MNUFC_RANGE_bean> recv_range_list;// 接單方加工範圍list
    private GIVE_PRICE_MSTR_bean give_price;// 中標用戶的報價信息
    private String send_user_name;// 發單用戶名
    private String recv_user_name;// 中標接單用戶名
    private List<String> send_user_pkid_list;// 發單方用戶pkid的list
    private List<String> recv_user_pkid_list;// 接單方用戶pkid的list
    private List<String> bill_pkid_list;// 訂單pkid的list
    private String recv_num_type;// 接單方個數類型, all:全部，single:單個，multiple:多個

    // 分頁查詢中請求參數需要的字段
    private Integer pageIndex;// 頁碼
    private Integer pageSize;// 每頁數據條數

    // 分頁查詢中請求結果需要的字段
    private Integer row_num;// 行數
    private Integer row_total;// 數據總條數
    private Integer page_total;// 總頁數
}
