package com.zhaixin.apisafetoken.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhaixin.apisafetoken.entity.User;
import com.zhaixin.apisafetoken.utils.JwtUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @Program: apisafetoken
 * @Classname: UserLoginController
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/09 16:05
 */
@RestController
public class UserLoginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
        //...参数合法性验证
        //从数据库获取用户信息
        //....用户、密码验证
        User user1 = new User();
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        //创建token，并将token放在响应头
        String token = JwtUtils.createToken(JSONObject.toJSONString(user1));
        response.setHeader(JwtUtils.AUTH_HEADER_KEY, token);
        return "ok";
    }
}

