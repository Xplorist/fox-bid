package com.foxconn.bidding.mapper;

import com.foxconn.bidding.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillMapper {
    // 保存零件圖檔文件信息
    Integer save_PART_DOC_FILE(PART_DOC_FILE_bean param);

    // 保存訂單主表數據
    Integer save_BILL(BILL_bean param);

    // 查詢訂單單號關聯表中的訂單單號
    String query_bill_no_from_rel(String bill_pkid);

    // 更新訂單單號
    Integer update_bill_no(@Param("bill_pkid") String bill_pkid, @Param("bill_no") String bill_no);

    // 查詢訂單是否存在
    Integer query_bill_is_exist(String bill_pkid);

    // 刪除零件圖檔文件list
    Integer delete_PART_DOC_FILE_list(String part_doc_file_rel_id);

    // 更新訂單主表數據
    Integer update_BILL(BILL_bean param);

    // 更新訂單主表的狀態
    Integer update_BILL_status(@Param("bill_pkid") String bill_pkid, @Param("bill_status") String bill_status);

    // 發佈時更新主表的狀態和單號標誌
    Integer update_bill_publish(BILL_bean param);

    // 根據訂單id查詢接單用戶id的list
    List<String> listGiveRecvUserIdByBillId(String bill_pkid);

    // 根據接單人個數類型查詢訂單id的list
    List<String> listBillPkidByRecvNumType(String recv_num_type);

    // 未登錄查詢訂單list(分頁查詢)
    List<BILL_bean> query_bill_list_not_login(BILL_bean param);

    // 發單用戶登錄個人中心的查詢訂單list(分頁查詢)
    List<BILL_bean> query_bill_list_send_user(BILL_bean param);

    // 接單用戶登錄個人中心的查詢訂單list(分頁查詢)
    List<BILL_bean> query_bill_list_recv_user(BILL_bean param);

    // 接單用戶登錄查詢個狀態訂單list個數
    Integer query_status_num_recv_user(BILL_bean param);

    // 接單用戶登錄主頁查詢訂單list(分頁查詢)
    List<BILL_bean> query_bill_list_recv_main(BILL_bean param);

    // 根據狀態查詢相應狀態的訂單個數
    Integer query_bill_num_by_status(@Param("send_user_pkid") String send_user_pkid, @Param("bill_status") String bill_status);

    // 查詢發單方待評價的訂單個數
    Integer query_bill_num_of_no_send_eval(String send_user_pkid);

    // 查詢訂單的接單用戶個數
    Integer query_bill_recv_user_num(String bill_pkid);

    // 查詢訂單參與報價的接單用戶list
    List<USER_INFO_bean> query_bill_give_recv_user_list(String bill_pkid);

    // 查詢訂單發單方評價
    SEND_EVAL_bean query_bill_send_eval(String bill_pkid);

    // 查詢訂單接單方評價
    RECV_EVAL_bean query_bill_recv_eval(String bill_pkid);

    // 保存報價從表（清單明細）數據
    Integer save_GIVE_PRICE_SLAV(GIVE_PRICE_SLAV_bean param);

    // 保存報價主表數據
    Integer save_GIVE_PRICE_MSTR(GIVE_PRICE_MSTR_bean param);

    // 根據pkid查詢訂單
    BILL_bean query_bill_by_pkid(String bill_pkid);

    // 查詢訂單零件圖檔文件list
    List<PART_DOC_FILE_bean> query_bill_file_list(String part_doc_file_rel_id);

    // 根據零件圖檔文件id查詢零件圖檔文件信息
    PART_DOC_FILE_bean query_part_doc_file(String part_doc_file_pkid);

    // 根據單個文件pkid刪除訂單零件圖檔文件
    Integer delete_part_doc_file_by_pkid(String pkid);

    // 保存發單方評價
    Integer save_send_eval(SEND_EVAL_bean param);

    // 保存接單方評價
    Integer save_recv_eval(RECV_EVAL_bean param);

    // 更新訂單發單方評價狀態為已評價
    Integer update_f_send_eval_y(String bill_pkid);

    // 更新訂單接單方評價狀態為已評價
    Integer update_f_recv_eval_y(String bill_pkid);

    // 測試時間格式轉換
    Integer save_TEST_TIME_PARSE(TEST_TIME_PARSE_bean param);

    // 測試查詢時間
    TEST_TIME_PARSE_bean query_TEST_TIME_PARSE_bean(String pkid);

    // 根據訂單id和接單用戶id查詢報價信息
    GIVE_PRICE_MSTR_bean query_give_price_mstr(@Param("bill_pkid") String bill_pkid, @Param("recv_user_pkid") String recv_user_pkid);

    // 根據報價從表關聯id查詢報價從表list(報價明細)
    List<GIVE_PRICE_SLAV_bean> query_give_price_slav_list(String give_price_slav_rel_id);

    // 查詢發單方收到的評價list(分頁查詢)
    List<RECV_EVAL_bean> query_send_get_eval_list(RequestParam param);

    // 查詢發單方做出的評價list(分頁查詢)
    List<SEND_EVAL_bean> query_send_make_eval_list(RequestParam param);

    // 查詢接單方收到的評價list(分頁查詢)
    List<SEND_EVAL_bean> query_recv_get_eval_list(RequestParam param);

    // 查詢接單方做出的評價list(分頁查詢)
    List<RECV_EVAL_bean> query_recv_make_eval_list(RequestParam param);

    // 查詢發單方收到的評價平均分
    RECV_EVAL_bean query_send_get_eval_avg(String send_user_pkid);

    // 查詢接單方收到的評價平均分
    SEND_EVAL_bean query_recv_get_eval_avg(String recv_user_pkid);

    // 根據訂單id查詢報價list（分頁查詢）
    List<GIVE_PRICE_MSTR_bean> query_give_price_list_pagi(GIVE_PRICE_MSTR_bean param);

    // 根據訂單id查詢報價list
    List<GIVE_PRICE_MSTR_bean> query_give_price_list(String bill_pkid);

    // 根據報價id查詢標價信息
    GIVE_PRICE_MSTR_bean query_give_price_by_pkid(String pkid);

    // 更新該報價數據為中標
    Integer update_give_price_win_bid(String pkid);

    // 更新訂單表為中標後狀態，更新中標接單用戶
    Integer update_bill_win_bid(@Param("bill_pkid") String bill_pkid, @Param("recv_user_pkid") String recv_user_pkid);

    // 查詢發單方收到的評價list(不分頁)
    List<RECV_EVAL_bean> query_send_get_eval_list_nopagi(String send_user_pkid);

    // 查詢接單方收到的評價list(不分頁)
    List<SEND_EVAL_bean> query_recv_get_eval_list_nopagi(String recv_user_pkid);

    // 查詢昨天發單的總筆數和總金額
    StatisticsContent query_last_day_send_statistics();

    // 查詢截止昨天累計發單的總筆數和總金額
    StatisticsContent query_total_send_statistics();

    // 查詢昨天中標的總筆數和總金額
    StatisticsContent query_last_day_win_bid_statistics();

    // 查詢截止昨天累計中標的總筆數和總金額
    StatisticsContent query_total_win_bid_statistics();

    // 查詢老闆郵箱地址list
    List<BOSS_EMAIL_ADDRESS_bean> query_boss_email_list();

    // 查詢此單是否達到競價結束時間
    Integer query_is_reach_bid_end_date(String bill_pkid);

    // 查詢需要選標或棄標的訂單，用來通知發單用戶進行操作
    List<BILL_bean> query_need_pick_bid_bill();

    // 查詢需要流標的訂單，用來定時器查詢後自動設置流標
    List<BILL_bean> query_need_flow_bid_bill();

}
