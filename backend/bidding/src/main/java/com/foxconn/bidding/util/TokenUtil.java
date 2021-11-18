package com.foxconn.bidding.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * token工具類
 */
public class TokenUtil {
    /**
     * 獲取token
     *
     * @param id  用戶id，使用jwt規定的屬性aud存放用戶id
     * @param sec 密鑰
     * @return token
     */
    public static String getToken(String id, String sec) {
        return JWT.create().withAudience(id).sign(Algorithm.HMAC256(sec));
    }
}
