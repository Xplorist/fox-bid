package com.foxconn.bidding.model;

import lombok.Data;

/**
 * 次集團bean
 */
@Data
public class SECN_CMPY_bean {
    private String pkid;//主鍵id
    private String name;//名稱
    private String fctry_zone_pkid;//廠區主鍵id
    private Integer order_in_fctry;//廠區內序號
}
