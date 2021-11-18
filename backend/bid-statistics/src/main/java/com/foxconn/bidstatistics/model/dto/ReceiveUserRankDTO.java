package com.foxconn.bidstatistics.model.dto;

import lombok.Data;

/**
 * 接單用戶實時排行DTO
 */
@Data
public class ReceiveUserRankDTO {
    private String userId;// 用戶id
    private String username;// 用戶名
    private String deptName;// 單位名稱
    private Integer receiveNum;// 接单數
    private Integer winNum;// 中標數
    private Integer winPrice;// 中標金額
    private Integer winRate;// 中標率
    private Integer score;// 綜合評分
    private Integer outRate;// 出貨時效
    private Integer outQuality;// 出貨質量
    private Integer service;// 服務態度
}
