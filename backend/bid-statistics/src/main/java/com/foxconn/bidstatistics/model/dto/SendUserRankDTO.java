package com.foxconn.bidstatistics.model.dto;

import lombok.Data;

/**
 * 發單用戶排行DTO
 */
@Data
public class SendUserRankDTO {
    private String userId;// 用戶id
    private String username;// 用戶名
    private String deptName;// 單位名稱
    private Integer sendNum;// 發單數
    private Integer sendPrice;// 發單金額
    private Integer receiveRate;// （被）接單率
    private Integer score;// 客服評分
    private Integer payment;// 付款時效
    private Integer service;// 服務態度
}
