package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 驗證碼表bean
 */
@Data
public class VERIFICATION_CODE_bean {
    private String pkid;// 主鍵id
    private String user_pkid;// 用戶pkid
    private String code;// 驗證碼
    private String create_date;// 創建時間
    private String f_newest;// 是否最新標誌（Y:是，N：否）

    // 非表中字段
    private String username;// 用戶名
    private String password;// 密碼
}
