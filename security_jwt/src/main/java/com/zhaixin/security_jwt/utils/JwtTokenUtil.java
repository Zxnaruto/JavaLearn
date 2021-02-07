package com.zhaixin.security_jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: security_jwt
 * @Classname: JwtTokenUtil
 * @Author: Abner Zhai
 * @Description:
 * @Date: 2021/02/02 10:31
 */
@Slf4j
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /*
     * 根据负载生成token
     * */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /*
     * 根据Token获取负载
     * */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /*
     * 生成token的过期时间
     * */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /*
     * 从token中获取登陆用户名
     * */
    public String getUserNameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            log.error("get user name by token error");
        }
        return username;
    }

    /*
    * 验证token是否还有效
    * */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /*
    * 判段token是否过期
    * */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /*
    * 从token中，获取token过期时间
    * */
    private Date getExpiredDateFromToken(String token) {
        Date expiredDate = null;
        try {
            Claims claims = getClaimsFromToken(token);
            expiredDate = claims.getExpiration();
        } catch (Exception e) {
            log.error("get token expire date error");
        }
        return expiredDate;
    }

    /*
    * 根据用户信息生成token
    * */
    public String generateTokenByUserDetails(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /*
    * 判断token是否可以被刷新
    * */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /*
    * 刷新token
    * */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


}

