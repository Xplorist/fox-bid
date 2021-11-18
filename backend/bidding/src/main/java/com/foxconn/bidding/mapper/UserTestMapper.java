package com.foxconn.bidding.mapper;

import com.foxconn.bidding.model.USER_INFO_bean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestMapper {
    // 通過用戶名查找用戶
    USER_INFO_bean findUserByUsername(String username);

    // 通過id查找用戶
    USER_INFO_bean findUserById(String id);

    // 新增用戶信息
    Integer addUserInfo(USER_INFO_bean param);
}
