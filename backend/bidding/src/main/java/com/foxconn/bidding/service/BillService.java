package com.foxconn.bidding.service;

import com.foxconn.bidding.model.*;

import javax.servlet.http.HttpServletRequest;

public interface BillService {
    // 保存訂單
    ResultParam saveBill(BILL_bean param, HttpServletRequest request);

    // 發佈訂單(提交訂單)
    ResultParam submitBill(BILL_bean param, HttpServletRequest request);

    // 未登錄查詢訂單list(分頁查詢)
    ResultParam query_bill_list_not_login(BILL_bean param, HttpServletRequest request);

    // 發單用戶登錄個人中心的查詢訂單list(分頁查詢)
    ResultParam query_bill_list_send_user(BILL_bean param, HttpServletRequest request);

    // 發單用戶登錄查詢個狀態訂單list個數
    ResultParam query_status_bill_num_send_user(BILL_bean param, HttpServletRequest request);

    // 接單用戶登錄個人中心的查詢訂單list(分頁查詢)
    ResultParam query_bill_list_recv_user(BILL_bean param, HttpServletRequest request);

    // 接單用戶登錄查詢個狀態訂單list個數
    ResultParam query_status_num_recv_user(BILL_bean param, HttpServletRequest request);

    // 接單用戶登錄主頁查詢訂單list(分頁查詢)
    ResultParam query_bill_list_recv_main(BILL_bean param, HttpServletRequest request);

    // 接單用戶提交報價,開始競價（保存報價）
    ResultParam save_give_price(GIVE_PRICE_MSTR_bean param, HttpServletRequest request);

    // 根據pkid查詢訂單數據
    ResultParam query_bill_by_pkid(BILL_bean param, HttpServletRequest request);

    // 根據單個文件pkid刪除訂單零件圖檔文件
    ResultParam delete_part_doc_file_by_pkid(PART_DOC_FILE_bean param, HttpServletRequest request);

    // 更改訂單狀態
    ResultParam update_bill_status(BILL_bean param, HttpServletRequest request);

    // 保存發單方評價
    ResultParam save_send_eval(SEND_EVAL_bean param, HttpServletRequest request);

    // 保存接單方評價
    ResultParam save_recv_eval(RECV_EVAL_bean param, HttpServletRequest request);

    // 測試時間格式轉換
    ResultParam test_time_parse(TEST_TIME_PARSE_bean param, HttpServletRequest request);

    // 根據訂單id查詢報價信息
    ResultParam query_give_price_by_bill_pkid(GIVE_PRICE_MSTR_bean param, HttpServletRequest request);

    // 查詢發單方收到的評價list(分頁查詢)
    ResultParam query_send_get_eval_list(RequestParam param, HttpServletRequest request);

    // 查詢發單方做出的評價list(分頁查詢)
    ResultParam query_send_make_eval_list(RequestParam param, HttpServletRequest request);

    // 查詢接單方收到的評價list(分頁查詢)
    ResultParam query_recv_get_eval_list(RequestParam param, HttpServletRequest request);

    // 查詢接單方做出的評價list(分頁查詢)
    ResultParam query_recv_make_eval_list(RequestParam param, HttpServletRequest request);

    // 查詢發單方收到的評價平均分
    ResultParam query_send_get_eval_avg(RequestParam param, HttpServletRequest request);

    // 查詢接單方收到的評價平均分
    ResultParam query_recv_get_eval_avg(RequestParam param, HttpServletRequest request);

    // 【22】根據訂單id查詢排序後報價list(分頁查詢)
    ResultParam query_give_price_list(GIVE_PRICE_MSTR_bean param, HttpServletRequest request);

    // 【23】選定中標
    ResultParam select_win_bid(GIVE_PRICE_MSTR_bean param, HttpServletRequest request);

    // 自動流標
    // 查詢當前時間大於等於競標結束時間且無投標的訂單，將其設為流標，並郵件通知
    ResultParam auto_flow_bid();

    // 【24】棄標
    ResultParam abandon_bid(RequestParam param, HttpServletRequest request);

    // 【25】統計訂單信息
    ResultParam queryBillStatistics(RequestParam param, HttpServletRequest request);
}
