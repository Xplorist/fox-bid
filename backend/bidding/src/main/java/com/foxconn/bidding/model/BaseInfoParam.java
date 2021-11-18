package com.foxconn.bidding.model;

import lombok.Data;

@Data
public class BaseInfoParam {
    private String pkid;// 主鍵id
    private String name;// 名稱
    private String fctry_zone_pkid;// 產區主鍵id
    private String secn_cmpy_pkid;// 次集團主鍵id
    private String entrps_group_pkid;// 事業群主鍵id
    private String dept_name;// 單位名稱
    private String dept_no;// 單位代碼
    private String fctry_zone;// 廠區
    private String legal_person;// 法人
}
