package com.foxconn.bidding.service;

import com.foxconn.bidding.model.RequestParam;
import com.foxconn.bidding.model.ResultParam;
import com.foxconn.bidding.model.USER_INFO_bean;
import com.foxconn.bidding.model.VERIFICATION_CODE_bean;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    // 註冊提交前判斷用戶是否存在
    ResultParam ck_user_is_exist(USER_INFO_bean param, HttpServletRequest request);

    // 註冊
    ResultParam register(USER_INFO_bean param, HttpServletRequest request);

    // 登錄
    ResultParam login(USER_INFO_bean param, HttpServletRequest request);

    // 通過id查找用戶
    USER_INFO_bean findUserById(String id);

    // 登錄後查詢用戶信息
    ResultParam login_user_info(USER_INFO_bean param, HttpServletRequest request);

    // 根據用戶id查詢用戶信息
    ResultParam query_user_info_by_pkid(USER_INFO_bean param, HttpServletRequest request);

    // 更改用戶信息
    ResultParam update_user_info(USER_INFO_bean param, HttpServletRequest request);

    // 【07】忘記密碼，生成驗證碼
    ResultParam generate_code(USER_INFO_bean param, HttpServletRequest request);

    // 【08】忘記密碼，檢查驗證碼是否正確
    ResultParam check_code(VERIFICATION_CODE_bean param, HttpServletRequest request);

    // 【09】忘記密碼，提交更新密碼
    ResultParam update_password(VERIFICATION_CODE_bean param, HttpServletRequest request);

    // 【10】條件分頁查詢用戶list
    ResultParam queryUserList(USER_INFO_bean param, HttpServletRequest request);

    // 【11】刪除賬號
    ResultParam deleteUser(RequestParam param, HttpServletRequest request);

    // 【12】統計所有賬號的相關信息
    ResultParam queryAllUsersStatistics(RequestParam param, HttpServletRequest request);
}
