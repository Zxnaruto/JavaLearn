package com.zhaixin.security_jwt.component;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: security_jwt
 * @Classname: LoginFailOrTokenFailAuthenticationEntryPoint
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/02/02 12:01
 */
@Component
public class LoginFailOrTokenFailAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println("token fail");
        httpServletResponse.getWriter().flush();
    }
}

