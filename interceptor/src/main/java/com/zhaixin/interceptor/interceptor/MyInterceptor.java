package com.zhaixin.interceptor.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Program: interceptor
 * @Classname: MyInterceptor
 * @Author:  Zhai
 * @Description: 自定义拦截器
 * @Date: 2021/06/09 15:02
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 前置操作
        System.out.println("pre=========pre");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // controller执行之后的一些操作，在页面渲染前调用
        System.out.println("mid==========mid");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 页面渲染之后的操作，一般用于资源清理
        System.out.println("after========after");
    }
}

