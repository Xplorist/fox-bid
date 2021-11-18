package com.foxconn.bidding.model;

import lombok.Data;

@Data
public class ENTRPS_GROUP_bean {
    private String pkid;//主鍵id
    private String name;//名稱
    private String secn_cmpy_pkid;//次集團主鍵id
    private Integer order_in_cmpy;//次集團內序號
}
