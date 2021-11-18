package com.foxconn.bidding.controller;

import com.foxconn.bidding.model.BaseInfoParam;
import com.foxconn.bidding.model.ResultParam;
import com.foxconn.bidding.service.BaseInfoService;
import com.foxconn.bidding.util.Client_Real_IP_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/baseInfo")
@CrossOrigin
public class BaseInfoController {
    @Autowired
    private BaseInfoService svc;

    // 查詢廠區list
    @RequestMapping("/query_factory_list")
    public ResultParam query_factory_list(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.query_factory_list(param, request);

        return result;
    }

    // 查詢次集團list
    @RequestMapping("/query_SECN_CMPY_list")
    public ResultParam query_SECN_CMPY_list(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.query_SECN_CMPY_list(param, request);

        return result;
    }

    // 查詢事業群list
    @RequestMapping("/query_ENTRPS_GROUP_list")
    public ResultParam query_ENTRPS_GROUP_list(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.query_ENTRPS_GROUP_list(param, request);

        return result;
    }

    // 查詢產品處list
    @RequestMapping("/query_PD_OFFICE_list")
    public ResultParam query_PD_OFFICE_list(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.query_PD_OFFICE_list(param, request);

        return result;
    }

    // 【05】查詢交貨地點list
    @RequestMapping("/query_deliver_address_list")
    public ResultParam query_deliver_address_list(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.query_deliver_address_list(param, request);

        return result;
    }

    // 【06】查詢開標範圍list
    @RequestMapping("/query_bid_range_list")
    public ResultParam query_bid_range_list(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.query_bid_range_list(param, request);

        return result;
    }

    // 【07】查詢產品類型list
    @RequestMapping("/query_pd_type_list")
    public ResultParam query_pd_type_list(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.query_pd_type_list(param, request);

        return result;
    }

    // 【08】根據單位代碼查詢單位名稱
    @RequestMapping("/query_dept_name_by_dept_no")
    public ResultParam query_dept_name_by_dept_no(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.query_dept_name_by_dept_no(param, request);

        return result;
    }

    // 【test】測試IP
    @RequestMapping("/testIP")
    public ResultParam testIP(HttpServletRequest request) {
        String ip = Client_Real_IP_Util.getRealIP(request);
        ResultParam result = ResultParam.of("1", "IP:" + ip);

        return result;
    }

    // 【09】模糊搜索法人
    @RequestMapping("/fuzzy_search_legal_person")
    public ResultParam fuzzy_search_legal_person(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.fuzzy_search_legal_person(param, request);

        return result;
    }

    // 【10】查詢搜索條件list(管理員賬號管理條件)
    @RequestMapping("/listQueryConditions")
    public ResultParam listQueryConditions(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.listQueryConditions(param, request);

        return result;
    }

    // 【11】查詢訂單狀態list
    @RequestMapping("/listBillStatus")
    public ResultParam listBillStatus(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = svc.listBillStatus(param, request);

        return result;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    // 【】模板,放在最後
    @RequestMapping("/template")
    public ResultParam template(@RequestBody BaseInfoParam param, HttpServletRequest request) {
        ResultParam result = ResultParam.of("", "");

        return result;
    }
}
