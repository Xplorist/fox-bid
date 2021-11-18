package com.foxconn.bidstatistics.model.dto;

import lombok.Data;

/**
 * 實時數據看板DTO
 */
@Data
public class RealtimeDataBoardDTO {
    private String date;// 日期
    private Integer curDaySum;// 僅當天累計
    private Integer totalSum;// 截止當天所有累計
    private Integer thisMonthSum;// 本月截止當天累計
    private Integer preMonthSum;// 上月累計
}
