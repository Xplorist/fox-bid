package com.foxconn.bidstatistics.dao;

import com.foxconn.bidstatistics.model.dto.SendUserRankDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 訂單DAO
 */
@Repository
@Mapper
public interface BillDAO {
    // 根據產品類型查詢訂單數量
    Integer queryNumByPdType(String pdType);

    // 根據訂單流程類型查詢訂單數量
    Integer queryNumByFlowType(String flowType);

    // 根據廠區和訂單流程類型查詢訂單數
    Integer queryNumByFactoryAndFlowType(@Param("factory") String factory, @Param("flowType") String flowType);

    // 根據廠區查詢訂單發單金額總數
    Integer queryTotalPriceByFactory(String factory);

    // 按照發單量來進行排倒序，按照時間範圍來查詢發單的用戶id的前20個，並查出此發單用戶的總發單量
    List<SendUserRankDTO> listSendUserIdAndSendNumByTimeLimit(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

    // 按照指定時間範圍來查詢指定用戶的發單金額
    Integer querySendPriceByTimeLimit(@Param("userId") String userId, @Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

    // 按照指定時間範圍來查詢指定用戶的流標數量
    Integer querySendFlowNumByTimeLimit(@Param("userId") String userId, @Param("firstDay") String firstDay, @Param("lastDay") String lastDay);
}
