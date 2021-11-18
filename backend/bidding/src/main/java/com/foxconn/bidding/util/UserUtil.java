package com.foxconn.bidding.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 用戶工具類
 */
public class UserUtil {
    // 從HttpServletRequest中獲取登錄用戶pkid
    public static String getLoginUserPkid(HttpServletRequest request) {
        return (String) request.getAttribute("user_pkid");
    }
}
