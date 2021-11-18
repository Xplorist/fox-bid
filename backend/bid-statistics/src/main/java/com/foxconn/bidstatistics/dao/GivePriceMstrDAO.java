package com.foxconn.bidstatistics.dao;

import com.foxconn.bidstatistics.model.dto.ReceiveUserRankDTO;
import com.foxconn.bidstatistics.model.query.GivePriceMstrQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 報價DAO
 */
@Repository
@Mapper
public interface GivePriceMstrDAO {
    // 累計中標報價求和綜合查詢
    Integer querySumComplex(GivePriceMstrQuery param);

    // 根據廠區查詢報價數量
    Integer queryNumByFactory(String factory);

    // 按照接單量來進行排倒序，按照時間範圍來查詢接單的用戶id的前20個，並查出此接單用戶的總接單量
    List<ReceiveUserRankDTO> listReceiveUserIdByTimeLimit(@Param("firstDay") String firstDay, @Param("lastDay") String lastDay);

    // 按照指定時間範圍來查詢指定用戶的接單中標數和中標金額
    ReceiveUserRankDTO queryWinNumAndWinPriceByTimeLimit(@Param("userId") String userId, @Param("firstDay") String firstDay, @Param("lastDay") String lastDay);
}
