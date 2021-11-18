package com.foxconn.bidding.service.impl;

import com.foxconn.bidding.mapper.BillMapper;
import com.foxconn.bidding.mapper.UserMapper;
import com.foxconn.bidding.model.BILL_bean;
import com.foxconn.bidding.model.GIVE_PRICE_MSTR_bean;
import com.foxconn.bidding.model.USER_INFO_bean;
import com.foxconn.bidding.service.GivePriceSortService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GivePriceSortServiceImpl implements GivePriceSortService {
    @Autowired
    private BillMapper mapper;

    @Autowired
    private UserMapper userMapper;

    // 綜合排序查詢報價list
    @Override
    public List<GIVE_PRICE_MSTR_bean> query_give_price_list_complex(GIVE_PRICE_MSTR_bean param) {
        List<GIVE_PRICE_MSTR_bean> give_price_list = null;

        String bill_pkid = param.getBill_pkid();
        Integer pageIndex = param.getPageIndex();
        Integer pageSize = param.getPageSize();

        List<GIVE_PRICE_MSTR_bean> zone_1 = new ArrayList<>();// 【價差比 < -10%】的集合
        List<GIVE_PRICE_MSTR_bean> zone_2 = new ArrayList<>();// 【-10%  <= 價差比 < -5%】的集合
        List<GIVE_PRICE_MSTR_bean> zone_3 = new ArrayList<>();// 【-5% <=  價差比 <= 0】的集合
        List<GIVE_PRICE_MSTR_bean> zone_4 = new ArrayList<>();// 【0 < 價差比 <= 5%】的集合
        List<GIVE_PRICE_MSTR_bean> zone_5 = new ArrayList<>();// 【5% < 價差比 <= 10%】的集合
        List<GIVE_PRICE_MSTR_bean> zone_6 = new ArrayList<>();// 【 10% < 價差比 < 50%】的集合
        List<GIVE_PRICE_MSTR_bean> zone_7 = new ArrayList<>();// 【價差比 >= 50%】的集合

        // 1.根據報價進行排序（從小到大）
        give_price_list = mapper.query_give_price_list(bill_pkid);
        // 2.計算價差，價差比 （價差 = 報價 - 標價， 價差比 = 價差 / 標價）
        BILL_bean bill_bean = mapper.query_bill_by_pkid(bill_pkid);
        String send_user_pkid = bill_bean.getSend_user_pkid();
        USER_INFO_bean send_user = userMapper.findUserById(send_user_pkid);
        bill_bean.setSend_user(send_user);
        String send_secn_cmpy = send_user.getSecn_cmpy();
        String send_entrps_group = send_user.getEntrps_group();
        /* 這是以發單方的標價作為標準來進行排序的算法 */
        //Integer send_total_price = bill_bean.getTotal_price();// 標價

        /* 這是以接單方最小的報價最為標準來進行排序的算法 */
        Integer send_total_price = 0;
        if (!give_price_list.isEmpty()) {
            send_total_price = give_price_list.get(0).getTotal_price();
        }

        if (send_total_price == null || send_total_price == 0) {
            send_total_price = 100000;// 如果發單方沒有填寫接受總價，默認為100000，這樣導致排序算法肯定有問題，後面再改
        }
        for (int i = 0; i < give_price_list.size(); i++) {
            GIVE_PRICE_MSTR_bean give_price = give_price_list.get(i);
            Integer recv_total_price = give_price.getTotal_price();
            Float diff_ratio = (recv_total_price - send_total_price) / (float) send_total_price;
            give_price.setDiff_ratio(diff_ratio);
            String recv_user_pkid = give_price.getRecv_user_pkid();
            USER_INFO_bean recv_user = userMapper.findUserById(recv_user_pkid);
            give_price.setRecv_user(recv_user);
        }

        // 劃分區間
        for (int i = 0; i < give_price_list.size(); i++) {
            GIVE_PRICE_MSTR_bean give_price = give_price_list.get(i);
            give_price.setF_valid("Y");// 默認有效，能夠選擇
            Float diff_ratio = give_price.getDiff_ratio();

            if (diff_ratio < -0.10f) {
                zone_1.add(give_price);
            } else if (diff_ratio >= -0.10f && diff_ratio < -0.05f) {
                zone_2.add(give_price);
            } else if (diff_ratio >= -0.05f && diff_ratio <= 0.00f) {
                zone_3.add(give_price);
            } else if (diff_ratio > 0.00f && diff_ratio <= 0.05f) {
                zone_4.add(give_price);
            } else if (diff_ratio > 0.05f && diff_ratio <= 0.10f) {
                zone_5.add(give_price);
            } else if (diff_ratio > 0.10f && diff_ratio < 0.50f) {
                zone_6.add(give_price);
            } else if (diff_ratio >= 0.50f) {
                give_price.setF_valid("N");// 超過50%，不能選此標
                zone_7.add(give_price);
            }
        }

        /* zone_2開始 【-10%  <= 價差比 < -5%】的集合的處理】 */
        zone_2 = sort_by_deliver_date(zone_2);// 按交期排序
        zone_2 = sort_by_same_secn_cmp(zone_2, send_secn_cmpy);// 同一次集團優先

        /* zone_3開始 【-5% <=  價差比 <= 0】的集合的處理】 */
        zone_3 = sort_by_deliver_date(zone_3);// 按交期排序
        zone_3 = sort_by_same_entrps_group(zone_3, send_entrps_group);// 同一事業群優先

        /* zone_4開始 【0 < 價差比 <= 5%】的集合的處理】 */
        zone_4 = sort_by_deliver_date(zone_4);// 按交期排序
        zone_4 = sort_by_same_entrps_group(zone_4, send_entrps_group);// 同一事業群優先

        /* zone_5開始 【5% < 價差比 <= 10%】的集合的處理】 */
        zone_5 = sort_by_deliver_date(zone_5);// 按交期排序
        zone_5 = sort_by_same_secn_cmp(zone_5, send_secn_cmpy);// 同一次集團優先

        // 將6個區間的數據加進結果list中
        List<GIVE_PRICE_MSTR_bean> result = new ArrayList<>();
        add_listA_into_listB(zone_1, result);
        add_listA_into_listB(zone_2, result);
        add_listA_into_listB(zone_3, result);
        add_listA_into_listB(zone_4, result);
        add_listA_into_listB(zone_5, result);
        add_listA_into_listB(zone_6, result);
        add_listA_into_listB(zone_7, result);

        // 分頁
        Integer row_total = result.size();
        Integer fromIndex = (pageIndex - 1) * pageSize;
        Integer toIndex = pageIndex * pageSize;// 因為subList(fromIndex, toIndex)的取值範圍為閉開區間，不用減1

        fromIndex = fromIndex >= row_total ? 0 : fromIndex;
        toIndex = toIndex >= row_total ? row_total : toIndex;

        result = result.subList(fromIndex, toIndex);
        if (!result.isEmpty()) {
            GIVE_PRICE_MSTR_bean bean = result.get(0);
            bean.setRow_total(row_total);
        }

        return result;
    }

    // 遍歷list並加到另外一個list中
    public List<GIVE_PRICE_MSTR_bean> add_listA_into_listB(List<GIVE_PRICE_MSTR_bean> src, List<GIVE_PRICE_MSTR_bean> tar) {
        for (int i = 0; i < src.size(); i++) {
            GIVE_PRICE_MSTR_bean bean = src.get(i);
            tar.add(bean);
        }

        return tar;
    }

    // 按交期排序
    public List<GIVE_PRICE_MSTR_bean> sort_by_deliver_date(List<GIVE_PRICE_MSTR_bean> zone) {
        for (int i = 0; i < zone.size() - 1; i++) {
            for (int j = i + 1; j < zone.size(); j++) {
                GIVE_PRICE_MSTR_bean bean_i = zone.get(i);
                GIVE_PRICE_MSTR_bean bean_j = zone.get(j);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date deliver_date_i = null;
                Date deliver_date_j = null;
                try {
                    deliver_date_i = sdf.parse(bean_i.getDeliver_date());
                    deliver_date_j = sdf.parse(bean_j.getDeliver_date());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                GIVE_PRICE_MSTR_bean i_bean = new GIVE_PRICE_MSTR_bean();
                GIVE_PRICE_MSTR_bean j_bean = new GIVE_PRICE_MSTR_bean();
                BeanUtils.copyProperties(bean_i, i_bean);
                BeanUtils.copyProperties(bean_j, j_bean);

                if (deliver_date_i.after(deliver_date_j)) {
                    zone.set(i, j_bean);
                    zone.set(j, i_bean);
                } else {
                    zone.set(i, i_bean);
                    zone.set(j, j_bean);
                }
            }
        }

        return zone;
    }

    // 同一次集團優先
    public List<GIVE_PRICE_MSTR_bean> sort_by_same_secn_cmp(List<GIVE_PRICE_MSTR_bean> zone, String send_secn_cmpy) {
        List<GIVE_PRICE_MSTR_bean> sec_cmp_in = new ArrayList<>();
        List<GIVE_PRICE_MSTR_bean> sec_cmp_out = new ArrayList<>();

        for (int i = 0; i < zone.size(); i++) {
            GIVE_PRICE_MSTR_bean bean = zone.get(i);
            USER_INFO_bean recv_user = bean.getRecv_user();
            String recv_secn_cmpy = recv_user.getSecn_cmpy();
            if (send_secn_cmpy.equals(recv_secn_cmpy)) {
                sec_cmp_in.add(bean);
            } else {
                sec_cmp_out.add(bean);
            }
        }
        zone = new ArrayList<>();
        for (int i = 0; i < sec_cmp_in.size(); i++) {
            GIVE_PRICE_MSTR_bean bean = sec_cmp_in.get(i);
            zone.add(bean);
        }
        for (int i = 0; i < sec_cmp_out.size(); i++) {
            GIVE_PRICE_MSTR_bean bean = sec_cmp_out.get(i);
            zone.add(bean);
        }

        return zone;
    }

    // 同一事業群優先
    public List<GIVE_PRICE_MSTR_bean> sort_by_same_entrps_group(List<GIVE_PRICE_MSTR_bean> zone, String send_entrps_group) {
        List<GIVE_PRICE_MSTR_bean> entrps_group_in = new ArrayList<>();
        List<GIVE_PRICE_MSTR_bean> entrps_group_out = new ArrayList<>();

        for (int i = 0; i < zone.size(); i++) {
            GIVE_PRICE_MSTR_bean bean = zone.get(i);
            USER_INFO_bean recv_user = bean.getRecv_user();
            String recv_entrps_group = recv_user.getEntrps_group();
            if (send_entrps_group.equals(recv_entrps_group)) {
                entrps_group_in.add(bean);
            } else {
                entrps_group_out.add(bean);
            }
        }
        zone = new ArrayList<>();
        for (int i = 0; i < entrps_group_in.size(); i++) {
            GIVE_PRICE_MSTR_bean bean = entrps_group_in.get(i);
            zone.add(bean);
        }
        for (int i = 0; i < entrps_group_out.size(); i++) {
            GIVE_PRICE_MSTR_bean bean = entrps_group_out.get(i);
            zone.add(bean);
        }

        return zone;
    }

    // 非綜合排序時，判斷各報價是否有效
    @Override
    public List<GIVE_PRICE_MSTR_bean> decide_give_price_valid(List<GIVE_PRICE_MSTR_bean> give_price_list) {
        Integer send_total_price = 0;
        if (!give_price_list.isEmpty()) {
            send_total_price = give_price_list.get(0).getTotal_price();
            // 找出最小的報價send_total_price
            for (int i = 0; i < give_price_list.size(); i++) {
                GIVE_PRICE_MSTR_bean give_price = give_price_list.get(i);
                Integer recv_total_price = give_price.getTotal_price();
                if (recv_total_price < send_total_price) {
                    send_total_price = recv_total_price;
                }
            }
        }

        for (int i = 0; i < give_price_list.size(); i++) {
            GIVE_PRICE_MSTR_bean give_price = give_price_list.get(i);
            give_price.setF_valid("Y");
            Integer recv_total_price = give_price.getTotal_price();
            Float diff_ratio = (recv_total_price - send_total_price) / (float) send_total_price;
            give_price.setDiff_ratio(diff_ratio);
            if (diff_ratio >= 0.5f) {
                give_price.setF_valid("N");
            }
        }

        return give_price_list;
    }
}
