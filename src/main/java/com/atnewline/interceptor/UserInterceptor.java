package com.atnewline.interceptor;

import com.atnewline.utils.JwtHelper;
import com.atnewline.utils.Result;
import com.atnewline.utils.ResultCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname UserInterceptor
 * @Description TODO:登录拦截器，用户登录验证和权限控制
 * @Date 2024/6/21 15:39
 * @Created by xj
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.从请求头中获取token
        String token = request.getHeader("token");
        //2.判断token是否有效，有效则放行，否则就返回报错信息
        if (!jwtHelper.isExpiration(token)) {
            return true;
        }
        //3.返回错误信息
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        String result1 = objectMapper.writeValueAsString(result);
        response.getWriter().write(result1);
        return false;
    }
}
