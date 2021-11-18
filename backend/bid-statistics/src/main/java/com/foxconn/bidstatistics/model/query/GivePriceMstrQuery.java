package com.foxconn.bidstatistics.model.query;

import lombok.*;

/**
 * 報價Query
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class GivePriceMstrQuery {
    private String queryType;// 查詢類型, XDateSum: 某個日期當天的中標報價, UtilXDateSum: 截止某個日期的累計中標報價, BetweenXAndYDateSum:兩個日期之間的累計中標報價
    @NonNull
    private String factory;// 廠區
    private String xDate;// 日期x(格式：yyyy-MM-dd)
    private String yDate;// 日期y(格式：yyyy-MM-dd)
}
