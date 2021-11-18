package com.xplorist.bidforum.interceptor;

import com.xplorist.bidforum.model.dto.ResultDTO;
import com.xplorist.bidforum.model.dto.TokenDTO;
import com.xplorist.bidforum.remote.BidSsoRemote;
import com.xplorist.bidforum.util.VerifyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * token验证拦截器：用于token验证
 */
public class AuthInterceptor implements HandlerInterceptor {
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

                request.setAttribute("userId", userId);

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
