package com.bear.interceptor;

import com.bear.context.UserContext;
import com.bear.dto.res.UserDetail;
import com.bear.exception.BusinessException;
import com.bear.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tokenKey = "token";
        String token = request.getHeader(tokenKey);
        if (StringUtils.isBlank(token) || !redisUtil.hasKey(token)) {
            throw new BusinessException("用户未登录或已失效,请重新登录");
        }
        UserDetail userDetail =(UserDetail) redisUtil.get(token);
        UserContext.set(userDetail);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        UserContext.remove();
    }

}
