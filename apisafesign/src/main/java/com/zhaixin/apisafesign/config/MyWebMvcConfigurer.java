package com.zhaixin.apisafesign.config;


import com.zhaixin.apisafesign.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Program: interceptor
 * @Classname: MyWebMvcConfigurer
 * @Author: Zhai
 * @Description: 在spring boot 中对拦截器进行注册
 * @Date: 2021/06/09 15:07
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                InterceptorRegistration interceptorRegistration = registry.addInterceptor(myInterceptor);
                interceptorRegistration.addPathPatterns("/**");
            }
        };
    }
}

