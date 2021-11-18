package com.foxconn.bidding.controller;

import com.foxconn.bidding.model.BulletinDO;
import com.foxconn.bidding.model.RequestParam;
import com.foxconn.bidding.model.ResultParam;
import com.foxconn.bidding.model.USER_INFO_bean;
import com.foxconn.bidding.service.AdminService;
import com.foxconn.bidding.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理員接口Controller
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService svc;

    // 【01】管理員查詢賬號審核任務(分頁)
    @VerifyToken
    @RequestMapping("/query_user_au_list_pagi")
    public ResultParam query_user_au_list_pagi(@RequestBody USER_INFO_bean param, HttpServletRequest request) {
        ResultParam result = svc.query_user_au_list_pagi(param, request);

        return result;
    }

    // 【02】管理員賬號審核（1:通過，2:駁回）
    @VerifyToken
    @RequestMapping("/user_au")
    public ResultParam user_au(@RequestBody USER_INFO_bean param, HttpServletRequest request) {
        ResultParam result = svc.user_au(param, request);

        return result;
    }

    // 【03】保存公告
    @VerifyToken
    @RequestMapping("/saveBulletin")
    public ResultParam saveBulletin(@RequestBody List<BulletinDO> paramList, HttpServletRequest request) {
        ResultParam result = svc.saveBulletin(paramList, request);

        return result;
    }

    // 【04】查詢公告list
    @RequestMapping("/listBulletin")
    public ResultParam listBulletin(@RequestBody RequestParam param, HttpServletRequest request) {
        ResultParam result = svc.listBulletin(param, request);

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
