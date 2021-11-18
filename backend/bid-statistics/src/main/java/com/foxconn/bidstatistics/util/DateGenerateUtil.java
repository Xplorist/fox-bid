package com.foxconn.bidstatistics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期生成工具類
 */
public class DateGenerateUtil {
    // 生成當前日期及以前的當月內日期list
    public static List<String> getThisMonthNowAndBeforeDateList() {
        List<String> result = new ArrayList<>();

        Date nowDate = new Date();
        String curYear = new SimpleDateFormat("yyyy").format(nowDate);
        String curMonth = new SimpleDateFormat("MM").format(nowDate);
        String curDay = new SimpleDateFormat("dd").format(nowDate);
        int day = Integer.valueOf(curDay);

        for (int i = 1; i <= day; i++) {
            String iStr = addZeroForLittleNineNum(i);
            result.add(curYear + "-" + curMonth + "-" + iStr);
        }

        return result;
    }

    // 生成當前日期上一月內的所有日期list
    public static List<String> getPreMonthAllDateList() throws ParseException {
        String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<String> result = getPreMothAllDateList(nowDate);

        return result;
    }

    // 生成指定日期的上一月內的所有日期list
    public static List<String> getPreMothAllDateList(String date) throws ParseException {
        List<String> result = new ArrayList<>();

        Date nowDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String curYear = new SimpleDateFormat("yyyy").format(nowDate);
        int year = Integer.valueOf(curYear);
        String curMonth = new SimpleDateFormat("MM").format(nowDate);
        int month = Integer.valueOf(curMonth);
        int preMonth = month - 1;
        if (month == 1) {
            year -= 1;
            preMonth = 12;
        }
        int days = getMonthTotalDayNum(preMonth, year);

        for (int i = 1; i <= days; i++) {
            String iStr = addZeroForLittleNineNum(i);
            result.add("" + year + "-" + preMonth + "-" + iStr);
        }

        return result;
    }

    // 獲取指定日期的本年的第一天後最後一天
    public static Map<String, String> getTheYearFirstAndLastDay(String date) throws ParseException {
        Date nowDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String curYear = new SimpleDateFormat("yyyy").format(nowDate);

        String firstDay = curYear + "-01-01";
        String lastDay = curYear + "-12-31";

        Map<String, String> map = new HashMap<>();
        map.put("firstDay", firstDay);
        map.put("lastDay", lastDay);

        return map;
    }

    // 獲取指定日期的本月的第一天和最後一天
    public static Map<String, String> getTheMonthFirstAndLastDay(String date) throws ParseException {
        Date theDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String theYear = new SimpleDateFormat("yyyy").format(theDate);
        String theMonth = new SimpleDateFormat("MM").format(theDate);

        int year = Integer.valueOf(theYear);
        int month = Integer.valueOf(theMonth);
        Integer monthTotalDayNum = getMonthTotalDayNum(month, year);

        String firstDay = theYear + "-" + theMonth + "-01";
        String lastDay = theYear + "-" + theMonth + "-" + monthTotalDayNum;

        Map<String, String> map = new HashMap<>();
        map.put("firstDay", firstDay);
        map.put("lastDay", lastDay);

        return map;
    }

    // 獲取指定日期前後（正數為前，負數為後）指定天數的日期
    public static String getPreNextSomeDaysDate(String date, Integer amount) throws ParseException {
        Date theDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(theDate);
        calendar.add(calendar.DATE, amount);
        theDate = calendar.getTime();
        String theDateStr = new SimpleDateFormat("yyyy-MM-dd").format(theDate);

        return theDateStr;
    }

    // 日期小於10的加0處理
    public static String addZeroForLittleNineNum(Integer num) {
        String numStr = "" + num;
        if (num < 10) {
            numStr = "0" + num;
        }

        return numStr;
    }

    // 判斷年份是否是閏年
    public static Boolean checkIsLeapYear(Integer year) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (year % 4 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    // 獲取該月的天數
    public static Integer getMonthTotalDayNum(Integer month, Integer year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else if (month == 2) {
            if (checkIsLeapYear(year)) {
                return 29;
            } else {
                return 28;
            }
        } else {
            return 30;
        }
    }
}
