package com.buzz.authorization.interceptor;


import com.buzz.authorization.annotation.Authorization;
import com.buzz.constants.Constants;
import com.buzz.token.TokenManager;
import com.buzz.token.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String authorization = request.getHeader(Constants.AUTHORISATION);
        TokenModel model = tokenManager.getToken(authorization);

        if (tokenManager.checkToken(model)) {
            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
            return true;
        }

        //验证token失败才会走到这步
        if (method.getAnnotation(Authorization.class) != null) {//返回401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
