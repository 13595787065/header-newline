package com.atnewline.config;

import com.atnewline.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname InterceptorConfig
 * @Description TODO
 * @Date 2024/6/21 17:20
 * @Created by xj
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    //这是修改的内容11111111111111111111111
    // 这是xj2修改的内容1111111111111111111111122222222222
    //这是master修改的内容
    @Autowired
    private UserInterceptor userInterceptor;
    /**
     * 配置拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截关于文章操作的所有请求
        registry.addInterceptor(userInterceptor).addPathPatterns("/headline/**");
    }
}
