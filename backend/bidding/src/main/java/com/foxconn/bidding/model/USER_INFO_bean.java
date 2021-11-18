package com.foxconn.bidding.model;

import lombok.Data;

import java.util.List;

/**
 * 用戶表bean
 */
@Data
public class USER_INFO_bean<T> {
    private String pkid;//主鍵id
    private String username;//賬號名稱
    private String password;//賬號密碼
    private String in_out_type;//公司內外類別(in：公司內，out：公司外)
    private String send_recv_type;//發單接單類別(send：發單方，recv：接單方)
    private String fctry_zone;//所屬廠區
    private String secn_cmpy;//所屬次集團
    private String entrps_group;//所屬事業群
    private String pd_office;//所屬產品處
    private String dept_name;//單位名稱
    private String legal_person;//交易法人
    private String cost_code;//費用代碼
    private String recv_mnufc_range_rel_id;//接單方加工範圍關聯id
    private String bank_name;//收/付款銀行名稱
    private String bank_acunt;//收/付款銀行賬號
    private String busis_mngr;//業務經理
    private String tel;//聯繫電話（固定電話）
    private String email;//email
    private String summary;//簡介
    private String user_pic_file_pkid;//頭像文件pkid
    private String create_date;//創建時間
    private String phone;// 聯繫電話（移動電話）
    private String dept_code;// 單位代碼
    private String f_valid;// 賬號有效標誌（Y：有效，N：無效）
    private String latest_login_time;// 賬號最近登錄時間

    // 非表中字段
    private USER_PIC_FILE_bean user_pic_file;// 用戶頭像文件
    private String f_win_bid;// 是否中標標誌（Y:是，N:否）
    private List<RECV_MNUFC_RANGE_bean> recv_range_list;// 接單方加工範圍list
    private List<T> get_eval_list;// 收到的評價list
    private String au_status;// 賬號狀態，0：為審核，1：通過，2：駁回
    private String au_type;// 審核類型，1：通過，2：駁回
    private String rej_msg;// 駁回信息
    private String f_active;// 活躍標誌, Y:活躍，N:不活躍
    private GIVE_PRICE_MSTR_bean give_price;// 接單用戶的報價

    // 分頁字段
    private Integer pageIndex;// 分頁頁碼
    private Integer pageSize;// 每頁數據條數
    private Integer row_num;// 第幾行
    private Integer row_total;// 總行數

    // 統計數據
    private SendUserStatistics send_user_statistics;// 發單用戶統計數據
    private RecvUserStatistics recv_user_statistics;// 接單用戶統計數據
}
