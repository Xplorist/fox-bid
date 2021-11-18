package com.foxconn.bidstatistics.constant;

/**
 * 報價Query常量類
 */
public class ConfigConsts {
    public static final String SUCCESS = "1";// 成功標誌
    public static final String FAIL = "0";// 失敗標誌
    public static final String X_DATE_SUM = "XDateSum";// 某個日期當天的中標報價
    public static final String UNTIL_X_DATE_SUM = "UntilXDateSum";// 截止某個日期的累計中標報價
    public static final String BETWEEN_X_AND_Y_DATE_SUM = "BetweenXAndYDateSum";// 兩個日期之間的累計中標報價
    public static final String TOTAL = "全部";// 全部
    public static final String THIS_MONTH = "thisMonth";// 本月
    public static final String THIS_YEAR = "thisYear";// 本年
    public static final String SEND_NUM = "sendNum";// 發單數
    public static final String SEND_PRICE = "sendPrice";// 發單金額
    public static final String RECEIVE_RATE = "receiveRate";// （被）接單率
    public static final String SCORE = "score";// 客戶評分
    public static final String PAYMENT = "payment";// 付款時效
    public static final String SERVICE = "service";// 服務態度
    public static final String ASC = "asc";// 升序
    public static final String DESC = "desc";// 降序
}
