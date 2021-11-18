package com.foxconn.bidsso.mapper;

import com.foxconn.bidsso.model.UserInfoDO;
import org.springframework.stereotype.Repository;

@Repository
public interface SsoMapper {
    // 根據用戶名查詢用戶id和密碼
    UserInfoDO queryUserIdPswByUsername(String username);
    // 通過用戶id查詢密碼
    UserInfoDO queryUserPswById(String userId);
}
