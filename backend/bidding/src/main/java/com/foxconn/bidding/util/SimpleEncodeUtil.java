package com.foxconn.bidding.util;

/**
 * 簡單編碼處理工具
 */
public class SimpleEncodeUtil {
    /**
     *  編碼字符串
     * @param param 編碼前的字符串
     * @return 編碼後的字符串
     */
    public static String encode(String param) {
        char[] chars = param.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ 'x');
        }

        return  new String(chars);
    }
}
