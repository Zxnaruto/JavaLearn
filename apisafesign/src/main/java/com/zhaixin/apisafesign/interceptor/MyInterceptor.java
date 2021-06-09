package com.zhaixin.apisafesign.interceptor;


import com.zhaixin.apisafesign.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * @Program: interceptor
 * @Classname: MyInterceptor
 * @Author:  Zhai
 * @Description: 自定义拦截器
 * @Date: 2021/06/09 15:02
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
   // @Autowired
    //private AppSecretService appSecretService;

   // @Autowired
    //private RedisUtil redisUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         //appId验证
        final String appId = request.getHeader("appid");
        if(StringUtils.isEmpty(appId)){
            //throw new CommonException("appid不能为空");
        }
        //String appSecret = appSecretService.getAppSecretByAppId(appId);
        if(StringUtils.isEmpty(appSecret)){
            //throw new CommonException("appid不合法");
        }
        //时间戳验证
        final String timestamp = request.getHeader("timestamp");
        if(StringUtils.isEmpty(timestamp)){
            //throw new CommonException("timestamp不能为空");
        }
        //大于5分钟，非法请求
        long diff = System.currentTimeMillis() - Long.parseLong(timestamp);
        if(Math.abs(diff) > 1000 * 60 * 5){
            //throw new CommonException("timestamp已过期");
        }
        //临时流水号，防止重复提交
        final String nonce = request.getHeader("nonce");
        if(StringUtils.isEmpty(nonce)){
            //throw new CommonException("nonce不能为空");
        }
        //验证签名
        final String signature = request.getHeader("signature");
        if(StringUtils.isEmpty(nonce)){
            //throw new CommonException("signature不能为空");
        }
        final String method = request.getMethod();
        final String url = request.getRequestURI();
        final String body = StreamUtils.copyToString(request.getInputStream(), Charset.forName("UTF-8"));
        String signResult = SignUtil.getSignature(method, url, body, timestamp, nonce, appSecret);
        if(!signature.equals(signResult)){
           // throw new CommonException("签名验证失败");
        }
        //检查是否重复请求
        String key = appId + "_" + timestamp + "_" + nonce;
        //if(redisUtil.exist(key)){
        //    throw new CommonException("当前请求正在处理，请不要重复提交");
       // }
        //设置5分钟
        //redisUtil.save(key, signResult, 5*60);
        request.setAttribute("reidsKey",key);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //请求处理完毕之后，移除缓存
        String value = (String) request.getAttribute("reidsKey");
        if(!StringUtils.isEmpty(value)){
           // redisUtil.remove(value);
        }
    }
}

