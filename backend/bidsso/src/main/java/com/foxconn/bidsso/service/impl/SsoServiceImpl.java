package com.foxconn.bidsso.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.foxconn.bidsso.config.AllowedServices;
import com.foxconn.bidsso.mapper.SsoMapper;
import com.foxconn.bidsso.model.ResultDTO;
import com.foxconn.bidsso.model.TokenDTO;
import com.foxconn.bidsso.model.UserInfoDO;
import com.foxconn.bidsso.model.UserInfoDTO;
import com.foxconn.bidsso.service.SsoService;
import com.foxconn.bidsso.session.CustomSessionContext;
import com.foxconn.bidsso.util.SimpleEncodeUtil;
import com.foxconn.bidsso.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RefreshScope
@Slf4j
@Service
public class SsoServiceImpl implements SsoService {
    @Autowired
    private AllowedServices allowedServices;

    @Value("${allowed.domains}")
    List<String> domains;

    @Value("${allowed.projects}")
    List<String> projects;

    @Autowired
    private SsoMapper mapper;

    /*
    @Value("${allowed.services}")
    List<String> services;
    */
    // 【已废弃】檢查服務是否授權認證(使用@Value("${allowed.services}"))
    /*
    private boolean checkIsAllow(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        if (referer == null || "".equals(referer)) {
            return false;
        }

        // 處理referer網址為標準的http://ip:port/project模式，然後查看是否在授權範圍內
        int indexOfQuestion = referer.indexOf('?');
        if (indexOfQuestion != -1) {
            referer = referer.substring(0, indexOfQuestion);
        }
        int index = referer.indexOf("http://");
        if (index == -1) {
            return false;
        }
        String str = referer.substring("http://".length());
        String[] strArr = str.split("/");
        if (strArr.length == 1) {
            index = str.indexOf("/");
            if (index == -1) {
                referer += "/";// 例如：http://metalworking.efoxconn.com/
            }
        } else {
            referer = "http://" + strArr[0] + "/" + strArr[1];// 例如：http://metalworking.efoxconn.com/bid
        }

        //List<String> services = allowedServices.getServices();
        if (services == null || services.size() == 0) {
            return false;
        }

        for (int i = 0; i < services.size(); i++) {
            String as = services.get(i);
            System.out.println( "service[" + i + "]=【" + as + "】");
        }

        boolean isAllow = false;
        for (int i = 0; i < services.size(); i++) {
            String as = services.get(i);
            if (referer.equals(as)) {
                isAllow = true;
                break;
            }
        }
        if (!isAllow) {
            return false;
        }

        return true;
    }
     */

    // 檢查服務是否授權認證（使用 @Value("${allowed.domains}")和@Value("${allowed.projects}")）
    private boolean checkIsAllow(HttpServletRequest request) {
        String referer = request.getHeader("referer");
        if (referer == null || "".equals(referer)) {
            return false;
        }

        String domainStr = "";
        String projectStr = "";

        // 處理referer網址為標準的http://ip:port/project模式，然後查看是否在授權範圍內
        int indexOfQuestion = referer.indexOf('?');
        if (indexOfQuestion != -1) {
            referer = referer.substring(0, indexOfQuestion);
        }
        String str = referer.substring(referer.indexOf("://") + 3);

        String[] strArr = str.split("/");
        if (strArr.length == 1) {
            int index = str.indexOf("/");
            if (index != -1) {
                domainStr = str.substring(0, str.indexOf("/"));
            }
        } else {
            domainStr = strArr[0];// 例如：metalworking.efoxconn.com
            projectStr = strArr[0] + "/" + strArr[1];// 例如: metalworking.efoxconn.com/bid
        }

        // 打印domains和projects
        if (domains == null || domains.size() == 0) {
            return false;
        }
        for (int i = 0; i < domains.size(); i++) {
            String as = domains.get(i);
            System.out.println( "domains[" + i + "]=【" + as + "】");
        }
        if (projects == null || projects.size() == 0) {
            return false;
        }
        for (int i = 0; i < projects.size(); i++) {
            String pr = projects.get(i);
            System.out.println("projects[" + i + "]=【" + pr + "】");
        }

        System.out.println("domainStr=【" + domainStr+ "】");
        // domains的优先级高，先匹配
        for (int i = 0; i < domains.size(); i++) {
            String domain = domains.get(i);
            if (domainStr.equals(domain)) {
                return true;
            }
        }

        // 没有匹配到domains中的数据，projectStr为空，请求类型为http://ip:port/或http://ip:port
        if ("".equals(projectStr)) {
            return false;
        }
        System.out.println("projectStr=【" + projectStr + "】");

        // domains没有匹配到，判断projects是否匹配
        for (int i = 0; i < projects.size(); i++) {
            String project = projects.get(i);
            if (projectStr.equals(project)) {
                return true;
            }
        }

        return false;
    }

    // 【01】驗證是否登錄接口（jsonp接口）
    @Override
    public JSONPObject loginValidate(String callback, HttpServletRequest request, HttpServletResponse response) {
        if (callback == null || "".equals(callback)) {
            return new JSONPObject(callback, new ResultDTO("-1", "回調函數不能為空", null));
        }

        // 檢查服務是否授權認證
        if (!checkIsAllow(request)) {
            return new JSONPObject(callback, new ResultDTO("-1", "此服務未授權認證", null));
        }

        // 判斷是否存在JSESSIONID及其對應的session
        String jSessionId = null;
        String tokenInCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return new JSONPObject(callback, new ResultDTO("0", "未登錄", null));
        }
        boolean hasJSessionID = false;
        Cookie jSessionIdCookie = null;
        Cookie tokenCookie = null;
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            String cookieName = cookie.getName();
            if ("JSESSIONID".equals(cookieName)) {
                jSessionId = cookie.getValue();
                jSessionIdCookie = cookie;
                hasJSessionID = true;
            } else if ("token".equals(cookieName)) {
                tokenInCookie = cookie.getValue();
                tokenCookie = cookie;
            }
        }
        if (!hasJSessionID) {
            if (tokenCookie != null) {
                tokenCookie.setMaxAge(0);
                response.addCookie(tokenCookie);
            }
            return new JSONPObject(callback, new ResultDTO("0", "未登錄", null));
        }
        CustomSessionContext csc = CustomSessionContext.getInstance();
        HttpSession session = csc.getSession(jSessionId);
        if (session == null) {
            jSessionIdCookie.setMaxAge(0);
            response.addCookie(jSessionIdCookie);
            if (tokenCookie != null) {
                tokenCookie.setMaxAge(0);
                response.addCookie(tokenCookie);
            }
            return new JSONPObject(callback, new ResultDTO("0", "登錄失效", null));
        }

        // 判斷session中是否存在token
        String tokenInSession = (String)session.getAttribute("token");
        if (tokenInSession == null || "".equals(tokenInSession)) {
            session.invalidate();
            jSessionIdCookie.setMaxAge(0);
            response.addCookie(jSessionIdCookie);
            if (tokenCookie != null) {
                tokenCookie.setMaxAge(0);
                response.addCookie(tokenCookie);
            }
            return new JSONPObject(callback, new ResultDTO("0", "未登錄", null));
        }
        // 判斷cookie中是否存在token
        if (tokenInCookie == null || "".equals(tokenInCookie)){
            session.invalidate();
            jSessionIdCookie.setMaxAge(0);
            tokenCookie.setMaxAge(0);
            response.addCookie(jSessionIdCookie);
            response.addCookie(tokenCookie);
            return new JSONPObject(callback, new ResultDTO("0", "未登錄", null));
        }
        // 判斷兩個token是否相同
        if (!tokenInCookie.equals(tokenInSession)) {
            session.invalidate();
            jSessionIdCookie.setMaxAge(0);
            tokenCookie.setMaxAge(0);
            response.addCookie(jSessionIdCookie);
            response.addCookie(tokenCookie);
            return new JSONPObject(callback, new ResultDTO("0", "登錄失效", null));
        }
        String userId = (String)session.getAttribute("userId");
        UserInfoDTO dto = UserInfoDTO.of(userId, tokenInSession);
        if (userId == null || "".equals(userId)) {
            session.invalidate();
            jSessionIdCookie.setMaxAge(0);
            tokenCookie.setMaxAge(0);
            response.addCookie(jSessionIdCookie);
            response.addCookie(tokenCookie);
            return new JSONPObject(callback, new ResultDTO("0", "登錄失效", null));
        }

        return new JSONPObject(callback, new ResultDTO("1", "登錄驗證成功", dto));
    }

    // 登录逻辑
    public UserInfoDTO loginLogic(UserInfoDO userInfoDO, String username, String password, HttpServletRequest request, HttpServletResponse response) {
        // 登錄前，如果有cookie，將其設置為null，如果此cookie對應的session也不為空，將session設置為失效
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String cookieName = cookie.getName();
                if ("JSESSIONID".equals(cookieName)) {
                    CustomSessionContext csc = CustomSessionContext.getInstance();
                    HttpSession cscSession = csc.getSession(cookie.getValue());
                    if (cscSession != null) {
                        cscSession.invalidate();
                    }
                    cookie.setValue(null);
                } else if ("token".equals(cookieName)) {
                    cookie.setValue(null);
                }
            }
        }

        // 登錄成功，創建session,根據sessionId和用戶id生成token，
        // 將token和用戶id存進session，將token放入cookie中
        // 返回用戶id和token
        String userId = userInfoDO.getPkid();
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String token = TokenUtil.getToken(sessionId, userId, password);
        session.setAttribute("userId", userId);
        session.setAttribute("token", token);
        Cookie jSessionCookie = new Cookie("JSESSIONID", sessionId);
        Cookie tokenCookie = new Cookie("token", token);
        response.addCookie(jSessionCookie);
        response.addCookie(tokenCookie);
        UserInfoDTO dto = UserInfoDTO.of(userId, token);

        return dto;
    }

    // 【02】登錄接口（jsonp接口）
    @Override
    public JSONPObject login(String callback, String username, String password, HttpServletRequest request, HttpServletResponse response) {
        // 參數非空檢查
        if (callback == null || "".equals(callback)) {
            return new JSONPObject(callback, new ResultDTO("0", "回調函數不能為空", null));
        }
        if (username == null || "".equals(username)) {
            return new JSONPObject(callback, new ResultDTO("0", "用戶名不能為空", null));
        }
        if (password == null || "".equals(password)) {
            return new JSONPObject(callback, new ResultDTO("0", "密碼不能為空", null));
        }

        // 檢查服務是否授權認證
        if (!checkIsAllow(request)) {
            return new JSONPObject(callback, new ResultDTO("0", "此服務未授權認證", null));
        }

        // 根據用戶名查詢用戶id和密碼
        UserInfoDO userInfoDO = mapper.queryUserIdPswByUsername(username);
        if (userInfoDO == null) {
            return new JSONPObject(callback, new ResultDTO("0", "賬號或密碼不正確", null));
        }
        String encodePsw = SimpleEncodeUtil.encode(password);
        if (!encodePsw.equals(userInfoDO.getPassword())) {
            return new JSONPObject(callback, new ResultDTO("0", "賬號或密碼不正確", null));
        }

        // 登录逻辑
        UserInfoDTO dto = loginLogic(userInfoDO, username, password, request, response);

        return new JSONPObject(callback, new ResultDTO("1", "登錄成功", dto));
    }

    // 【03】註銷登錄接口（jsonp接口）
    @Override
    public JSONPObject logout(String callback, HttpServletRequest request, HttpServletResponse response) {
        // 參數非空檢查
        if (callback == null || "".equals(callback)) {
            return new JSONPObject(callback, new ResultDTO("0", "回調函數不能為空", null));
        }
        // 檢查服務是否授權認證
        if (!checkIsAllow(request)) {
            return new JSONPObject(callback, new ResultDTO("0", "此服務未授權認證", null));
        }

        // 判斷cookie中有無JSESSIONID，有
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return new JSONPObject(callback, new ResultDTO("1", "註銷登錄成功", null));
        }
        String jSessionId = null;
        Cookie jSessionCookie = null;
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            String cookieName = cookie.getName();
            if ("JSESSIONID".equals(cookieName)) {
                jSessionId = cookie.getValue();
                jSessionCookie = cookie;
                break;
            }
        }

        // 設置cookie和session失效
        if (jSessionCookie != null) {
            CustomSessionContext csc = CustomSessionContext.getInstance();
            HttpSession session = csc.getSession(jSessionId);
            if (session != null) {
                session.invalidate();
            }
            jSessionCookie.setMaxAge(0);
            response.addCookie(jSessionCookie);
        }

        return new JSONPObject(callback, new ResultDTO("1", "註銷登錄成功", null));
    }

    // 【04】驗證token接口（Ajax接口）
    @Override
    public ResultDTO tokenValidate(TokenDTO param, HttpServletRequest request, HttpServletResponse response) {
        // 參數非空檢查
        if (param == null) {
            return  new ResultDTO("0", "請求JSON參數不能為空", null);
        }
        String token = param.getToken();
        if (token == null || "".equals(token)) {
            return  new ResultDTO("0", "請求JSON參數中token不能為空", null);
        }

        DecodedJWT decodedJWT = null;
        try {
            // 解碼token
            decodedJWT = JWT.decode(token);
        } catch (Exception e) {
            log.error("解碼token=" + token + "失敗", e);
            return new ResultDTO("0", "解碼token=" + token + "失敗", null);
        }

        String sessionId = decodedJWT.getId();
        String userId = decodedJWT.getAudience().get(0);
        if (sessionId == null || "".equals(sessionId)
            || userId == null || "".equals(userId)) {
            return new ResultDTO("0", "token已失效", null);
        }

        CustomSessionContext csc = CustomSessionContext.getInstance();
        HttpSession session = csc.getSession(sessionId);
        if (session == null) {
            return new ResultDTO("0", "token已失效", null);
        }
        // 通過用戶id查詢密碼
        UserInfoDO userInfoDO = mapper.queryUserPswById(userId);
        if (userInfoDO == null) {
            return new ResultDTO("0", "token已失效", null);
        }
        String password = SimpleEncodeUtil.encode(userInfoDO.getPassword());
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).build();

        try {
            jwtVerifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("token=" + token + "驗證失敗");
            return new ResultDTO("0", "token=" + token + "驗證失敗", null);
        }

        return new ResultDTO("1", "驗證token成功", userId);
    }

    // 【05】登錄接口（Ajax接口）[業務系統後端使用httpclient調用此接口]
    @Override
    public ResultDTO loginAjax(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        // 参数非空验证
        if (username == null || "".equals(username)) {
            return new ResultDTO("0", "用户名不能为空", null);
        }
        if (password == null || "".equals(password)) {
            return new ResultDTO("0", "密码不能为空", null);
        }

        // 根據用戶名查詢用戶id和密碼
        UserInfoDO userInfoDO = mapper.queryUserIdPswByUsername(username);
        if (userInfoDO == null) {
            return new ResultDTO("0", "賬號或密碼不正確", null);
        }
        String encodePsw = SimpleEncodeUtil.encode(password);
        if (!encodePsw.equals(userInfoDO.getPassword())) {
            return new ResultDTO("0", "賬號或密碼不正確", null);
        }

        // 登录逻辑
        UserInfoDTO dto = loginLogic(userInfoDO, username, password, request, response);

        return new ResultDTO("1", "【05】登錄接口（Ajax接口）成功", dto);
    }
}
