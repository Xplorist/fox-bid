package com.foxconn.bidding.model;

import lombok.Data;

@Data
public class PD_OFFICE_bean {
    private String pkid;//主鍵id
    private String name;//名稱
    private String entrps_group_pkid;//事業群主鍵id
    private Integer order_in_group;//事業群內序號
}
