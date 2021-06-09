package com.zhaixin.apisafetoken.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Program: apisafetoken
 * @Classname: JwtUtils
 * @Author:  Zhai
 * @Description: jwt token生成与校验
 * @Date: 2021/06/09 15:45
 */
public class JwtUtils {
    // 定义token的头部
    public static final String AUTH_HEADER_KEY = "Authoriztion";
    // token 前缀
    public static final String TOKEN_PREFIX = "Bearer ";
    // 签名密钥
    public static final String KEY = "afljl2sf8nlo2ikj@kj*7$fjaljx";
    // token 有效时间
    public static final Long EXPIRATION_TIME = 1000L * 60 * 60 * 2;

    /*
    * 创建 token
    * */
    public static String createToken (String content) throws UnsupportedEncodingException {
        return TOKEN_PREFIX + JWT.create().withSubject(content)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(KEY));
    }

    /*
    * 验证token
    * */
    public static String verifyToken(String token) throws Exception {
        try {
            return JWT.require(Algorithm.HMAC512(KEY))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""))
                    .getSubject();
        } catch (TokenExpiredException e) {
            throw new Exception("token已失效，请重新登陆", e);
        } catch (JWTVerificationException e) {
            throw new Exception("token验证失败", e);
        }
    }
}

