package com.lt.health.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: jwt工具类
 * @author: 狂小腾
 * @date: 2022/3/22 19:44
 */
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 根据荷载生成token
     */
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 从token中获取用户名
     */
    public String getUserNameFromToken(String token) {
        Claims claims = this.getTokenBody(token);
        if (claims == null) {
            throw new RuntimeException("获取用户名失败");
        }
        return claims.getSubject();
    }

    /**
     * token是否过期
     */
    public boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 验证token是否有效
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isExpiration(token);
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isExpiration(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getTokenBody(token);
        claims.setExpiration(new Date());
        return generateToken(claims);
    }

    /**
     * 获取token中的信息
     */
    public Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
