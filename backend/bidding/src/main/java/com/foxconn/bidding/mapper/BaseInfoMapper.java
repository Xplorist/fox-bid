package com.foxconn.bidding.mapper;

import com.foxconn.bidding.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseInfoMapper {
    // 查詢廠區list
    List<FCTRY_ZONE_bean> query_factory_list();

    // 查詢次集團list
    List<SECN_CMPY_bean> query_SECN_CMPY_list(String fctry_zone_pkid);

    // 查詢事業群list
    List<ENTRPS_GROUP_bean> query_ENTRPS_GROUP_list(String secn_cmpy_pkid);

    // 查詢產品處list
    List<PD_OFFICE_bean> query_PD_OFFICE_list(String entrps_group_pkid);

    // 查詢交貨地點list
    List<BaseInfoParam> query_deliver_address_list();

    // 查詢開標範圍list
    List<BaseInfoParam> query_bid_range_list();

    // 查詢產品類型list
    List<BaseInfoParam> query_pd_type_list();

    // 根據單位代碼查詢單位名稱
    B_DEPT_bean query_dept_name_by_dept_no(String dept_no);

    // 模糊搜索法人
    List<BaseInfoParam> query_legal_person(String legal_person);

    // 查詢廠區條件子項
    List<QueryConditionSub> listFactorySub();

    // 查詢事業群條件子項
    List<QueryConditionSub> listBusinessSub();

    // 查詢產品處條件子項
    List<QueryConditionSub> listProductSub();

    // 查詢類型條件子項
    List<QueryConditionSub> listTypeSub();

    // 查詢訂單狀態list
    List<BillStatusDO> listBillStatus();
}
