package com.foxconn.bidsso.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.foxconn.bidsso.model.ResultDTO;
import com.foxconn.bidsso.model.TokenDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SsoService {
    // 【01】驗證是否登錄接口（jsonp接口）
    JSONPObject loginValidate(String callback, HttpServletRequest request, HttpServletResponse response);

    // 【02】登錄接口（jsonp接口）
    JSONPObject login(String callback, String username, String password, HttpServletRequest request, HttpServletResponse response);

    // 【03】註銷登錄接口（jsonp接口）
    JSONPObject logout(String callback, HttpServletRequest request, HttpServletResponse response);

    // 【04】驗證token接口（Ajax接口）
    ResultDTO tokenValidate(TokenDTO param, HttpServletRequest request, HttpServletResponse response);

    // 【05】登錄接口（Ajax接口）[業務系統後端使用httpclient調用此接口]
    ResultDTO loginAjax(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
