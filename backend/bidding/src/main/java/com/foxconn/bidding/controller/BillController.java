package com.foxconn.bidding.controller;

import com.foxconn.bidding.model.*;
import com.foxconn.bidding.service.BillService;
import com.foxconn.bidding.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 訂單模塊
 */
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService svc;

    // 保存訂單
    @VerifyToken
    @RequestMapping("/saveBill")
    public ResultParam saveBill(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.saveBill(param, request);

        return result;
    }

    // 發佈訂單(提交訂單)
    @VerifyToken
    @RequestMapping("/submitBill")
    public ResultParam submitBill(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.submitBill(param, request);

        return result;
    }

    // 【3】未登錄查詢訂單list(分頁查詢)
    @RequestMapping(value = "/query_bill_list_not_login", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ResultParam query_bill_list_not_login(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_bill_list_not_login(param, request);

        return result;
    }

    // 發單用戶登錄個人中心的查詢訂單list(分頁查詢)
    @VerifyToken
    @RequestMapping("/query_bill_list_send_user")
    public ResultParam query_bill_list_send_user(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_bill_list_send_user(param, request);

        return result;
    }

    // 發單用戶登錄查詢個狀態訂單list個數
    @VerifyToken
    @RequestMapping("/query_status_bill_num_send_user")
    public ResultParam query_status_bill_num_send_user(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_status_bill_num_send_user(param, request);

        return result;
    }

    // 接單用戶登錄個人中心的查詢訂單list(分頁查詢)
    @VerifyToken
    @RequestMapping("/query_bill_list_recv_user")
    public ResultParam query_bill_list_recv_user(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_bill_list_recv_user(param, request);

        return result;
    }

    // 接單用戶登錄查詢個狀態訂單list個數
    @VerifyToken
    @RequestMapping("/query_status_num_recv_user")
    public ResultParam query_status_num_recv_user(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_status_num_recv_user(param, request);

        return result;
    }

    // 接單用戶登錄主頁查詢訂單list(分頁查詢)
    @VerifyToken
    @RequestMapping("/query_bill_list_recv_main")
    public ResultParam query_bill_list_recv_main(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_bill_list_recv_main(param, request);

        return result;
    }

    // 【11】接單用戶提交報價,開始競價（保存報價）
    @VerifyToken
    @RequestMapping("/save_give_price")
    public ResultParam save_give_price(@RequestBody GIVE_PRICE_MSTR_bean param, HttpServletRequest request) {
        ResultParam result = svc.save_give_price(param, request);

        return result;
    }

    // 根據pkid查詢訂單數據
    @RequestMapping("/query_bill_by_pkid")
    public ResultParam query_bill_by_pkid(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_bill_by_pkid(param, request);

        return result;
    }

    // 根據單個文件pkid刪除訂單零件圖檔文件
    @RequestMapping("/delete_part_doc_file_by_pkid")
    public ResultParam delete_part_doc_file_by_pkid(@RequestBody PART_DOC_FILE_bean param, HttpServletRequest request) {
        ResultParam result = svc.delete_part_doc_file_by_pkid(param, request);

        return result;
    }

    // 更改訂單狀態
    @VerifyToken
    @RequestMapping("/update_bill_status")
    public ResultParam update_bill_status(@RequestBody BILL_bean param, HttpServletRequest request) {
        ResultParam result = svc.update_bill_status(param, request);

        return result;
    }

    // 保存發單方評價
    @VerifyToken
    @RequestMapping("/save_send_eval")
    public ResultParam save_send_eval(@RequestBody SEND_EVAL_bean param, HttpServletRequest request) {
        ResultParam result = svc.save_send_eval(param, request);

        return result;
    }

    // 保存接單方評價
    @VerifyToken
    @RequestMapping("/save_recv_eval")
    public ResultParam save_recv_eval(@RequestBody RECV_EVAL_bean param, HttpServletRequest request) {
        ResultParam result = svc.save_recv_eval(param, request);

        return result;
    }

    // 測試時間格式轉換
    @RequestMapping("/test_time_parse")
    public ResultParam test_time_parse(@RequestBody TEST_TIME_PARSE_bean param, HttpServletRequest request) {
        ResultParam result = svc.test_time_parse(param, request);

        return result;
    }

    // 根據訂單id查詢報價信息
    @VerifyToken
    @RequestMapping("/query_give_price_by_bill_pkid")
    public ResultParam query_give_price_by_bill_pkid(@RequestBody GIVE_PRICE_MSTR_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_give_price_by_bill_pkid(param, request);

        return result;
    }

    // 查詢發單方收到的評價list(分頁查詢)
    @VerifyToken
    @RequestMapping("/query_send_get_eval_list")
    public ResultParam query_send_get_eval_list(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.query_send_get_eval_list(param, request);

        return result;
    }

    // 查詢發單方做出的評價list(分頁查詢)
    @VerifyToken
    @RequestMapping("/query_send_make_eval_list")
    public ResultParam query_send_make_eval_list(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.query_send_make_eval_list(param, request);

        return result;
    }

    // 查詢接單方收到的評價list(分頁查詢)
    @VerifyToken
    @RequestMapping("/query_recv_get_eval_list")
    public ResultParam query_recv_get_eval_list(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.query_recv_get_eval_list(param, request);

        return result;
    }

    // 查詢接單方做出的評價list(分頁查詢)
    @VerifyToken
    @RequestMapping("/query_recv_make_eval_list")
    public ResultParam query_recv_make_eval_list(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.query_recv_make_eval_list(param, request);

        return result;
    }

    // 查詢發單方收到的評價平均分
    @VerifyToken
    @RequestMapping("/query_send_get_eval_avg")
    public ResultParam query_send_get_eval_avg(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.query_send_get_eval_avg(param, request);

        return result;
    }

    // 查詢接單方收到的評價平均分
    @VerifyToken
    @RequestMapping("/query_recv_get_eval_avg")
    public ResultParam query_recv_get_eval_avg(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.query_recv_get_eval_avg(param, request);

        return result;
    }

    // 【22】根據訂單id查詢排序後報價list(分頁查詢)
    //@VerifyToken
    @RequestMapping("/query_give_price_list")
    public ResultParam query_give_price_list(@RequestBody GIVE_PRICE_MSTR_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_give_price_list(param, request);

        return result;
    }

    // 【23】選定中標
    @VerifyToken
    @RequestMapping("/select_win_bid")
    public ResultParam select_win_bid(@RequestBody GIVE_PRICE_MSTR_bean param, HttpServletRequest request) {
        ResultParam result = svc.select_win_bid(param, request);

        return result;
    }

    // 【24】棄標
    @VerifyToken
    @RequestMapping("/abandon_bid")
    public ResultParam abandon_bid(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.abandon_bid(param, request);

        return result;
    }

    // 【25】統計訂單信息
    @RequestMapping("/queryBillStatistics")
    public ResultParam queryBillStatistics(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.queryBillStatistics(param, request);

        return result;
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    // 【】模板,放在最後
    @VerifyToken
    @RequestMapping("/template")
    public ResultParam template(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = ResultParam.of("", "");

        return result;
    }
}
