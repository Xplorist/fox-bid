package com.foxconn.bidding.service;

import com.foxconn.bidding.model.BaseInfoParam;
import com.foxconn.bidding.model.ResultParam;

import javax.servlet.http.HttpServletRequest;

public interface BaseInfoService {
    // 查詢廠區list
    ResultParam query_factory_list(BaseInfoParam param, HttpServletRequest request);

    // 查詢次集團list
    ResultParam query_SECN_CMPY_list(BaseInfoParam param, HttpServletRequest request);

    // 查詢事業群list
    ResultParam query_ENTRPS_GROUP_list(BaseInfoParam param, HttpServletRequest request);

    // 查詢產品處list
    ResultParam query_PD_OFFICE_list(BaseInfoParam param, HttpServletRequest request);

    // 【05】查詢交貨地點list
    ResultParam query_deliver_address_list(BaseInfoParam param, HttpServletRequest request);

    // 【06】查詢開標範圍list
    ResultParam query_bid_range_list(BaseInfoParam param, HttpServletRequest request);

    // 【07】查詢產品類型list
    ResultParam query_pd_type_list(BaseInfoParam param, HttpServletRequest request);

    // 【08】根據單位代碼查詢單位名稱
    ResultParam query_dept_name_by_dept_no(BaseInfoParam param, HttpServletRequest request);

    // 【09】模糊搜索法人
    ResultParam fuzzy_search_legal_person(BaseInfoParam param, HttpServletRequest request);

    // 【10】查詢搜索條件list(管理員賬號管理條件)
    ResultParam listQueryConditions(BaseInfoParam param, HttpServletRequest request);

    // 【11】查詢訂單狀態list
    ResultParam listBillStatus(BaseInfoParam param, HttpServletRequest request);
}
