package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 單位基本資料bean
 */
@Data
public class B_DEPT_bean {
    private String pkid;//主鍵id
    private String dept_no;//單位代碼
    private String dept_name;//單位名稱
    private String dept_level;//單位層級
    private String dept_upno;//上級單位代碼
    private String depttype;//單位類型
    private String show;//顯示
    private String order_no;//序號
    private String use_flag;//使用標誌
    private String creater;//創建人
    private String createdate;//創建日期
    private String editman;//編輯人
    private String editdate;//編輯日期
}
