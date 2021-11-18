package com.foxconn.bidstatistics.dao;


import com.foxconn.bidstatistics.model.UserInfoDO;
import com.foxconn.bidstatistics.model.dto.ReceiveUserRankDTO;
import com.foxconn.bidstatistics.model.dto.SendUserRankDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用戶DAO
 */
@Repository
@Mapper
public interface UserInfoDAO {
    // 根據用戶類型查詢用戶個數
    Integer queryNumByUserType(String userType);

    // 根據廠區查詢用戶數量
    Integer queryNumByFactory(String factory);

    // 根據用戶id查詢用戶名和單位
    SendUserRankDTO queryUsernameAndDeptById(String userId);

    // 查詢發單用戶收到的相關評分
    SendUserRankDTO querySendUserGetEval(String userId);

    // 查詢接單用戶收到的相關評分
    ReceiveUserRankDTO queryReceiveUserGetEval(String userId);
}
