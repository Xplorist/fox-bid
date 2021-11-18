package com.foxconn.bidding.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化工具類
 */
public class DateParseUtil {
    /**
     *  轉換格林威治標準時的字符串為'yyyy-MM-dd HH:mm:ss'格式的字符串時間
     * @param gmtStr GMT 格林威治標準時的字符串
     * @return
     */
    public static String gmtStrToJavaStyleStr(String gmtStr) throws ParseException {
        gmtStr = gmtStr.replace("Z", " UTC");
        Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").parse(gmtStr);
        gmtStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parse);

        return gmtStr;
    }
}
