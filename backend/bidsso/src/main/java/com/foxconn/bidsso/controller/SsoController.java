package com.foxconn.bidsso.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.foxconn.bidsso.model.ResultDTO;
import com.foxconn.bidsso.model.TokenDTO;
import com.foxconn.bidsso.service.SsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class SsoController {
    @Autowired
    private SsoService svc;

    // 【01】驗證是否登錄接口（jsonp接口）
    @RequestMapping("/loginValidate")
    public JSONPObject loginValidate(String callback, HttpServletRequest request, HttpServletResponse response) {
        return svc.loginValidate(callback, request, response);
    }

    // 【02】登錄接口（jsonp接口）
    @RequestMapping("/login")
    public JSONPObject login(String callback, String username, String password, HttpServletRequest request, HttpServletResponse response) {
        return svc.login(callback, username, password, request, response);
    }

    // 【03】註銷登錄接口（jsonp接口）
    @RequestMapping("/logout")
    public JSONPObject logout(String callback, HttpServletRequest request, HttpServletResponse response) {
        return svc.logout(callback, request, response);
    }

    // 【04】驗證token接口（Ajax接口）[業務系統後端使用httpclient調用此接口]
    @RequestMapping("/tokenValidate")
    public ResultDTO tokenValidate(@RequestBody TokenDTO param, HttpServletRequest request, HttpServletResponse response) {
        return svc.tokenValidate(param, request, response);
    }

    // 【05】登錄接口（Ajax接口）[業務系統後端使用httpclient調用此接口]
    @RequestMapping("/loginAjax")
    public ResultDTO loginAjax(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        return svc.loginAjax(username, password, request, response);
    }
}
