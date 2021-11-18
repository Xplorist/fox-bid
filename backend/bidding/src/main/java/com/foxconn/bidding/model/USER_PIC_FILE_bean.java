package com.foxconn.bidding.model;

import lombok.Data;

@Data
public class USER_PIC_FILE_bean {
    private String pkid;//主鍵id
    private String file_origin_name;//文件原始名稱
    private String file_save_name;//文件保存名稱
    private String file_save_path;//文件保存路徑
    private String create_date;//創建日期
}
