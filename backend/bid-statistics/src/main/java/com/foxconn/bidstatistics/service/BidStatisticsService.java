package com.foxconn.bidstatistics.service;

import com.foxconn.bidstatistics.model.dto.ResultDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public interface BidStatisticsService {
    // 【01】實時看板
    ResultDTO realtimeDataBoard(String factory, HttpServletRequest request, HttpServletResponse response) throws ParseException;

    // 【02】發佈類型
    ResultDTO publishType(HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【03】客戶營銷
    ResultDTO customerMarketing(HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【04】訂單分析
    ResultDTO billAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【05】廠部分析
    ResultDTO factoryAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【06】發單用戶實時排行
    ResultDTO sendUserRank(String timeLimit, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 【07】接單用戶實時排行
    ResultDTO receiveUserRank(String timeLimit, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
