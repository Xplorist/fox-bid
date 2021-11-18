package com.foxconn.bidding.model;

import lombok.Data;

/**
 *報價從表（清單明細）bean
 */
@Data
public class GIVE_PRICE_SLAV_bean {
    private String pkid;//主鍵id
    private String give_price_slav_rel_id;//報價從表關聯id
    private String part_name;//零件名稱
    private Integer part_amunt;//零件數量
    private Integer part_unit_price;//零件單價
    private Integer part_price_sum;//零件價格小計
    private Integer list_order;//列表序號
    private String create_date;//創建時間
    private String part_doc_file_pkid;// 零件圖檔文件id

    // 非表中字段
    private PART_DOC_FILE_bean part_doc_file;// 零件圖檔文件
}
