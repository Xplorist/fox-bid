package com.foxconn.bidstatistics.controller;

import com.foxconn.bidstatistics.model.dto.ResultDTO;
import com.foxconn.bidstatistics.service.BidStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

@RestController
public class BidStatisticsController {
    private BidStatisticsService svc;

    @Autowired
    public BidStatisticsController(BidStatisticsService svc) {
        this.svc = svc;
    }

    // 【01】實時看板
    @RequestMapping(value = "/realtimeDataBoard", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO realtimeDataBoard(String factory, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        return svc.realtimeDataBoard(factory, request, response);
    }

    // 【02】發佈類型
    @RequestMapping(value = "/publishType", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO publishType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.publishType(request, response);
    }

    // 【03】客戶營銷
    @RequestMapping(value = "/customerMarketing", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO customerMarketing(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.customerMarketing(request, response);
    }

    // 【04】訂單分析
    @RequestMapping(value = "/billAnalysis", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO billAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.billAnalysis(request, response);
    }

    // 【05】廠部分析
    @RequestMapping(value = "/factoryAnalysis", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO factoryAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.factoryAnalysis(request, response);
    }

    // 【06】發單用戶實時排行
    @RequestMapping(value = "/sendUserRank", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO sendUserRank(String timeLimit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.sendUserRank(timeLimit, request, response);
    }

    // 【07】接單用戶實時排行
    @RequestMapping(value = "/receiveUserRank", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO receiveUserRank(String timeLimit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.receiveUserRank(timeLimit, request, response);
    }

    /*---------模板，放在最後------------*/
    // 模板
    @RequestMapping(value = "/template", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public ResultDTO template(String param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResultDTO.of("", "");
    }
}
