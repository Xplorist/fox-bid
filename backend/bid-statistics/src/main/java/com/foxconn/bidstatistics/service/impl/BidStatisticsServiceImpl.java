package com.foxconn.bidstatistics.service.impl;

import com.foxconn.bidstatistics.constant.ConfigConsts;
import com.foxconn.bidstatistics.dao.BillDAO;
import com.foxconn.bidstatistics.dao.GivePriceMstrDAO;
import com.foxconn.bidstatistics.dao.PdTypeDAO;
import com.foxconn.bidstatistics.dao.UserInfoDAO;
import com.foxconn.bidstatistics.model.PdTypeDO;
import com.foxconn.bidstatistics.model.dto.*;
import com.foxconn.bidstatistics.model.query.GivePriceMstrQuery;
import com.foxconn.bidstatistics.service.BidStatisticsService;
import com.foxconn.bidstatistics.util.DateGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BidStatisticsServiceImpl implements BidStatisticsService {
    @Autowired
    private GivePriceMstrDAO givePriceMstrDAO;// 報價DAO
    @Autowired
    private BillDAO billDAO;//訂單DAO
    @Autowired
    private PdTypeDAO pdTypeDAO;// 產品類型DAO
    @Autowired
    private UserInfoDAO userInfoDAO;// 用戶DAO

    // 【01】實時看板
    @Override
    public ResultDTO realtimeDataBoard(String factory, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        // 參數非空檢查
        if (factory == null || "".equals(factory)) {
            //return ResultDTO.of("0", "請求參數中廠區不能為空");
            factory = ConfigConsts.TOTAL;
        }

        List<RealtimeDataBoardDTO> thisResultList = new ArrayList<>();
        List<RealtimeDataBoardDTO> preResultList = new ArrayList<>();

        List<String> thisList = DateGenerateUtil.getThisMonthNowAndBeforeDateList();
        List<String> preList = DateGenerateUtil.getPreMonthAllDateList();

        /*-----------------------------本月------------------------------------*/
        thisResultList = queryRealtimeDataBoardData(factory, thisList, thisResultList);

        /*-----------------------------上月--------------------------------*/
        preResultList = queryRealtimeDataBoardData(factory, preList, preResultList);

        Map<String, Object> map = new HashMap<>();
        map.put("curMonth", thisResultList);
        map.put("preMonth", preResultList);

        return new ResultDTO(ConfigConsts.SUCCESS, "【01】實時看板查詢成功", map);
    }

    // 查詢實時看板數據
    public List<RealtimeDataBoardDTO> queryRealtimeDataBoardData(String factory, List<String> dateList, List<RealtimeDataBoardDTO> resultList) throws ParseException {
        for (int i = 0; i < dateList.size(); i++) {
            String xDate = dateList.get(i);
            // 某個日期當天的中標報價
            GivePriceMstrQuery paramA = new GivePriceMstrQuery();
            paramA.setQueryType(ConfigConsts.X_DATE_SUM);
            paramA.setFactory(factory);
            paramA.setXDate(xDate);
            Integer resultA = givePriceMstrDAO.querySumComplex(paramA);

            // 截止某個日期的累計中標報價
            GivePriceMstrQuery paramB = new GivePriceMstrQuery();
            paramB.setQueryType(ConfigConsts.UNTIL_X_DATE_SUM);
            paramB.setFactory(factory);
            paramB.setXDate(xDate);
            Integer resultB = givePriceMstrDAO.querySumComplex(paramB);

            // 某個日期的當月累計中標報價（某個日期和某個日期所在月的第一天）
            GivePriceMstrQuery paramC = new GivePriceMstrQuery();
            paramC.setQueryType(ConfigConsts.BETWEEN_X_AND_Y_DATE_SUM);
            paramC.setFactory(factory);
            paramC.setXDate(dateList.get(0));
            paramC.setYDate(xDate);
            Integer resultC = givePriceMstrDAO.querySumComplex(paramC);

            // 某個日期的上月累計中標報價(上月第一天和最後一天)
            List<String> preMonthList = DateGenerateUtil.getPreMothAllDateList(xDate);
            GivePriceMstrQuery paramD = new GivePriceMstrQuery();
            paramD.setQueryType(ConfigConsts.BETWEEN_X_AND_Y_DATE_SUM);
            paramD.setFactory(factory);
            paramD.setXDate(preMonthList.get(0));
            paramD.setYDate(preMonthList.get(preMonthList.size() - 1));
            Integer resultD = givePriceMstrDAO.querySumComplex(paramD);

            RealtimeDataBoardDTO dto = new RealtimeDataBoardDTO();
            dto.setDate(xDate);
            dto.setCurDaySum(parseNullToZero(resultA));
            dto.setTotalSum(parseNullToZero(resultB));
            dto.setThisMonthSum(parseNullToZero(resultC));
            dto.setPreMonthSum(parseNullToZero(resultD));
            resultList.add(dto);
        }

        return resultList;
    }

    // 將為null的數字轉換為0
    public Integer parseNullToZero(Integer num) {
        if (num == null) {
            return 0;
        } else {
            return num;
        }
    }

    // 【02】發佈類型
    @Override
    public ResultDTO publishType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<PublishTypeDTO> result = new ArrayList<>();

        // 查詢產品類型list
        List<PdTypeDO> pdTypeList = pdTypeDAO.listPdType();
        // 根據產品類型查詢訂單數量
        for (int i = 0; i < pdTypeList.size(); i++) {
            PdTypeDO pdType = pdTypeList.get(i);
            String pdTypeName = pdType.getName();
            Integer billNum = billDAO.queryNumByPdType(pdTypeName);

            PublishTypeDTO dto = new PublishTypeDTO();
            dto.setName(pdTypeName);
            dto.setValue(billNum);
            result.add(dto);
        }

        return new ResultDTO(ConfigConsts.SUCCESS, "【02】發佈類型查詢成功", result);
    }

    // 【03】客戶營銷
    @Override
    public ResultDTO customerMarketing(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<PublishTypeDTO> result = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("send", "發佈方");
        map.put("recv", "接單方");
        Set<Map.Entry<String, String>> entries = map.entrySet();

        for (Map.Entry<String, String> entry : entries) {
            String userType = entry.getKey();
            String userTypeName = entry.getValue();

            // 根據用戶類型查詢用戶個數
            Integer userNum = userInfoDAO.queryNumByUserType(userType);
            PublishTypeDTO dto = new PublishTypeDTO();
            dto.setName(userTypeName);
            dto.setValue(userNum);

            result.add(dto);
        }

        return new ResultDTO(ConfigConsts.SUCCESS, "【03】客戶營銷查詢成功", result);
    }

    // 【04】訂單分析
    @Override
    public ResultDTO billAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<PublishTypeDTO> result = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("wait", "待選數");
        map.put("win", "中標數");
        map.put("flow", "流標數");
        map.put("abandon", "棄標數");
        Set<Map.Entry<String, String>> entries = map.entrySet();

        for (Map.Entry<String, String> entry : entries) {
            String flowType = entry.getKey();
            String flowTypeName = entry.getValue();

            // 根據訂單流程類型查詢訂單數量
            Integer billNum = billDAO.queryNumByFlowType(flowType);
            PublishTypeDTO dto = new PublishTypeDTO();
            dto.setName(flowTypeName);
            dto.setValue(billNum);

            result.add(dto);
        }

        return new ResultDTO(ConfigConsts.SUCCESS, "【04】訂單分析查詢成功", result);
    }

    // 【05】廠部分析
    @Override
    public ResultDTO factoryAnalysis(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<FactoryAnalysisDTO> result = new ArrayList<>();

        List<String> factoryList = new ArrayList<>();
        factoryList.add("成都");
        factoryList.add("深圳");

        for (int i = 0; i < factoryList.size(); i++) {
            FactoryAnalysisDTO dto = new FactoryAnalysisDTO();

            String factory = factoryList.get(i);
            dto.setName(factory);
            List<PublishTypeDTO> valueList = new ArrayList<>();
            dto.setValue(valueList);
            result.add(dto);

            // 根據廠區查詢用戶數量
            Integer userNum = userInfoDAO.queryNumByFactory(factory);
            PublishTypeDTO valueA = new PublishTypeDTO();
            valueA.setName("廠商數");
            valueA.setValue(userNum);
            valueList.add(valueA);

            // 根據廠區查詢訂單數量
            Integer billNum = billDAO.queryNumByFactoryAndFlowType(factory, null);
            PublishTypeDTO valueB = new PublishTypeDTO();
            valueB.setName("發單量");
            valueB.setValue(billNum);
            valueList.add(valueB);

            // 根據廠區查詢報價數量
            Integer givePriceNum = givePriceMstrDAO.queryNumByFactory(factory);
            PublishTypeDTO valueC = new PublishTypeDTO();
            valueC.setName("接單數");
            valueC.setValue(givePriceNum);
            valueList.add(valueC);

            // 根據廠區查詢中標訂單數量
            Integer billWinNum = billDAO.queryNumByFactoryAndFlowType(factory, "win");
            PublishTypeDTO valueD = new PublishTypeDTO();
            valueD.setName("中標數");
            valueD.setValue(billWinNum);
            valueList.add(valueD);

            // 根據廠區查詢報價金額總數
            GivePriceMstrQuery givePriceMstrQuery = new GivePriceMstrQuery();
            givePriceMstrQuery.setQueryType(ConfigConsts.UNTIL_X_DATE_SUM);
            givePriceMstrQuery.setFactory(factory);
            givePriceMstrQuery.setXDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            Integer givePriceTotal = givePriceMstrDAO.querySumComplex(givePriceMstrQuery);
            PublishTypeDTO valueE = new PublishTypeDTO();
            valueE.setName("接單金額");
            valueE.setValue(givePriceTotal);
            valueList.add(valueE);

            // 根據廠區查詢訂單發單金額總數
            Integer billTotalPrice = billDAO.queryTotalPriceByFactory(factory);
            PublishTypeDTO valueF = new PublishTypeDTO();
            valueF.setName("發單金額");
            valueF.setValue(billTotalPrice);
            valueList.add(valueF);

            // 根據廠區查詢訂單中標率
            int billWinRate = Math.round(((float) billWinNum) / billNum * 100);
            PublishTypeDTO valueG = new PublishTypeDTO();
            valueG.setName("中標率");
            valueG.setValue(billWinRate);
            valueList.add(valueG);

            // 根據廠區查詢接標率 【(被)接標率=(發單總數-流標數)/發單總數】
            Integer billFlowNum = billDAO.queryNumByFactoryAndFlowType(factory, "flow");
            int billRecvRate = Math.round(((float) (billNum - billFlowNum)) / billNum * 100);
            PublishTypeDTO valueH = new PublishTypeDTO();
            valueH.setName("(被)接標率");
            valueH.setValue(billRecvRate);
            valueList.add(valueH);
        }

        return new ResultDTO(ConfigConsts.SUCCESS, "【05】廠部分析查詢成功", result);
    }

    // 【06】發單用戶實時排行
    @Override
    public ResultDTO sendUserRank(String timeLimit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 條件非空判斷
        if (timeLimit == null || "".equals(timeLimit) || (!ConfigConsts.THIS_MONTH.equals(timeLimit) && !ConfigConsts.THIS_YEAR.equals(timeLimit))) {
            timeLimit = ConfigConsts.THIS_MONTH;
        }

        List<SendUserRankDTO> result = null;

        Map<String, String> firstDayAndLastDay = getFirstDayAndLastDay(timeLimit);
        String firstDay = firstDayAndLastDay.get("firstDay");
        String lastDay = firstDayAndLastDay.get("lastDay");

        // 按照發單量來進行排倒序，按照時間範圍來查詢發單的用戶id的前20個，並查出此發單用戶的總發單量
        result = billDAO.listSendUserIdAndSendNumByTimeLimit(firstDay, lastDay);

        for (int i = 0; i < result.size(); i++) {
            SendUserRankDTO dto = result.get(i);
            String userId = dto.getUserId();
            Integer sendNum = dto.getSendNum();
            // 按照指定時間範圍來查詢指定用戶的發單金額
            Integer sendPrice = billDAO.querySendPriceByTimeLimit(userId, firstDay, lastDay);
            dto.setSendPrice(sendPrice);

            // 按照指定時間範圍來查詢指定用戶的流標數量
            Integer flowNum = billDAO.querySendFlowNumByTimeLimit(userId, firstDay, lastDay);
            // 求出（被）接單率
            Integer receiveRate = Math.round(((float) (sendNum - flowNum)) / sendNum * 100);
            dto.setReceiveRate(receiveRate);

            // 查詢發單用戶收到的相關評分
            SendUserRankDTO userX = userInfoDAO.querySendUserGetEval(userId);
            if (userX == null) {
                userX = new SendUserRankDTO();
                userX.setScore(0);
                userX.setPayment(0);
                userX.setService(0);
            }
            dto.setScore(parseNullToZero(userX.getScore()));
            dto.setPayment(parseNullToZero(userX.getPayment()));
            dto.setService(parseNullToZero(userX.getService()));

            // 查詢用戶名和單位
            // 根據用戶id查詢用戶名和單位
            SendUserRankDTO user = userInfoDAO.queryUsernameAndDeptById(userId);
            dto.setUsername(user.getUsername());
            dto.setDeptName(user.getDeptName());
        }

        return new ResultDTO(ConfigConsts.SUCCESS, "【06】發單用戶實時排行查詢成功", result);
    }

    // 【07】接單用戶實時排行
    @Override
    public ResultDTO receiveUserRank(String timeLimit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 條件非空判斷
        if (timeLimit == null || "".equals(timeLimit) || (!ConfigConsts.THIS_MONTH.equals(timeLimit) && !ConfigConsts.THIS_YEAR.equals(timeLimit))) {
            timeLimit = ConfigConsts.THIS_MONTH;
        }

        List<ReceiveUserRankDTO> result = null;
        Map<String, String> firstDayAndLastDay = getFirstDayAndLastDay(timeLimit);
        String firstDay = firstDayAndLastDay.get("firstDay");
        String lastDay = firstDayAndLastDay.get("lastDay");

        // 按照接單量來進行排倒序，按照時間範圍來查詢接單的用戶id的前20個，並查出此接單用戶的總接單量
        result = givePriceMstrDAO.listReceiveUserIdByTimeLimit(firstDay, lastDay);

        for (int i = 0; i < result.size(); i++) {
            ReceiveUserRankDTO dto = result.get(i);
            String userId = dto.getUserId();
            Integer receiveNum = dto.getReceiveNum();

            // 按照指定時間範圍來查詢指定用戶的接單中標數和中標金額
            ReceiveUserRankDTO temp = givePriceMstrDAO.queryWinNumAndWinPriceByTimeLimit(userId, firstDay, lastDay);
            dto.setWinNum(parseNullToZero(temp.getWinNum()));
            dto.setWinPrice(parseNullToZero(temp.getWinPrice()));

            // 求出中標率
            Integer winNum = dto.getWinNum();
            Integer winRate = Math.round((((float) winNum) / receiveNum * 100));
            dto.setWinRate(winRate);

            // 查詢接單用戶收到的相關評分
            ReceiveUserRankDTO receiveUser = userInfoDAO.queryReceiveUserGetEval(userId);
            if (receiveUser == null) {
                receiveUser = new ReceiveUserRankDTO();
                receiveUser.setScore(0);
                receiveUser.setOutRate(0);
                receiveUser.setOutQuality(0);
                receiveUser.setService(0);
            }
            dto.setScore(parseNullToZero(receiveUser.getScore()));
            dto.setOutRate(parseNullToZero(receiveUser.getOutRate()));
            dto.setOutQuality(parseNullToZero(receiveUser.getOutQuality()));
            dto.setService(parseNullToZero(receiveUser.getService()));

            // 查詢用戶名和單位
            // 根據用戶id查詢用戶名和單位
            SendUserRankDTO user = userInfoDAO.queryUsernameAndDeptById(userId);
            dto.setUsername(user.getUsername());
            dto.setDeptName(user.getDeptName());
        }

        return new ResultDTO(ConfigConsts.SUCCESS, "【07】接單用戶實時排行查詢成功", result);
    }

    // 獲取第一年和最後一天
    public Map<String, String> getFirstDayAndLastDay(String timeLimit) throws ParseException {
        String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String firstDay = "";
        String lastDay = "";

        /*------根據時間範圍查詢相應的具體時間-------*/
        // 本月
        if (ConfigConsts.THIS_MONTH.equals(timeLimit)) {
            Map<String, String> map = DateGenerateUtil.getTheMonthFirstAndLastDay(nowDate);
            firstDay = map.get("firstDay");
            lastDay = map.get("lastDay");
        }
        // 本年
        if (ConfigConsts.THIS_YEAR.equals(timeLimit)) {
            Map<String, String> map = DateGenerateUtil.getTheYearFirstAndLastDay(nowDate);
            firstDay = map.get("firstDay");
            lastDay = map.get("lastDay");
        }

        /*-------臨時修改數據,如果需要本月，將當前時間前30天和當前時間前365天的代碼注釋掉-------*/
        // 當前時間前30天
        if (ConfigConsts.THIS_MONTH.equals(timeLimit)) {
            firstDay = DateGenerateUtil.getPreNextSomeDaysDate(nowDate, -30);
            lastDay = nowDate;
        }
        // 當前時間前365天
        if (ConfigConsts.THIS_YEAR.equals(timeLimit)) {
            firstDay = DateGenerateUtil.getPreNextSomeDaysDate(nowDate, -365);
            lastDay = nowDate;
        }

        Map<String, String> map = new HashMap<>();
        map.put("firstDay", firstDay);
        map.put("lastDay", lastDay);

        return map;
    }
}
