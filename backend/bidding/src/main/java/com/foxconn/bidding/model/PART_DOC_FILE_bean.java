package com.foxconn.bidding.model;

import lombok.Data;

import java.sql.Date;

/**
 * 零件圖檔文件表bean
 */
@Data
public class PART_DOC_FILE_bean {
    private String pkid;//主鍵id
    private String part_doc_file_rel_id;//零件圖檔文件表關聯id
    private String file_origin_name;//文件原始名稱
    private String file_save_name;//文件保存名稱
    private String file_save_path;//文件保存路徑
    private Integer part_amunt;//零件個數
    private Integer list_order;//列表序號
    private Date create_date;//創建時間
    private String create_user_pkid;// 創建用戶pkid
    private String create_user_ip;// 創建用戶IP地址
}
