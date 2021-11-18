package com.foxconn.bidding.interceptor;

import com.foxconn.bidding.model.ResultDTO;
import com.foxconn.bidding.model.TokenDTO;
import com.foxconn.bidding.remote.BidSsoRemote;
import com.foxconn.bidding.service.UserService;
import com.foxconn.bidding.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token驗證攔截器：用于token验证
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    /*
    // 【已弃用】以前未接入bidsso时使用的token验证
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 從http請求頭中取出token
        String token = request.getHeader("token");
        // 如果不是映射到Controller方法直接放行
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 檢查需不需要token
        if(method.isAnnotationPresent(VerifyToken.class)) {
            VerifyToken userLoginToken = method.getAnnotation(VerifyToken.class);
            if(userLoginToken.required()) {
                if(token == null) {
                    throw new RuntimeException("該請求沒有token， 請先獲取token！");
                }
                // 獲取token中的userId
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException e) {
                    //e.printStackTrace();
                    throw new RuntimeException("token非法，沒有userId！");
                }

                USER_INFO_bean user = userService.findUserById(userId);
                if(user == null) {
                    throw new RuntimeException("用戶不存在，請重新登錄！");
                }

                // 驗證token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SimpleEncodeUtil.encode(user.getPassword()))).build();

                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    //e.printStackTrace();
                    throw new RuntimeException("校驗token發生異常");
                }
                request.setAttribute("user_pkid", user.getPkid());

                return true;
            }
        }

        return true;
    }
     */

    @Autowired
    BidSsoRemote bidSsoRemote;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // 从http请求头中取出header
        String token = request.getHeader("token");
        // 如果不是映射到Controller方法直接放行
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查需不需要token
        if(method.isAnnotationPresent(VerifyToken.class)) {
            VerifyToken userLoginToken = method.getAnnotation(VerifyToken.class);
            if(userLoginToken.required()) {
                if(token == null || "".equals(token)) {
                    throw new RuntimeException("該請求沒有token， 請先獲取token！");
                }
                // 通过Feign请求bidsso验证token
                String userId;
                TokenDTO dto = new TokenDTO();
                dto.setToken(token);

                ResultDTO resultDTO = bidSsoRemote.tokenValidate(dto);
                if ("1".equals(resultDTO.getCode())) {
                    userId = (String) resultDTO.getT();
                } else {
                    throw new RuntimeException("token验证失败，" + resultDTO.getMsg());
                }

                request.setAttribute("user_pkid", userId);

                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
