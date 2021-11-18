package com.foxconn.bidding.service;

import com.foxconn.bidding.model.GIVE_PRICE_MSTR_bean;

import java.util.List;

public interface GivePriceSortService {
    // 綜合排序查詢報價list
    List<GIVE_PRICE_MSTR_bean> query_give_price_list_complex(GIVE_PRICE_MSTR_bean param);

    // 非綜合排序時，判斷各報價是否有效
    List<GIVE_PRICE_MSTR_bean> decide_give_price_valid(List<GIVE_PRICE_MSTR_bean> give_price_list);
}
