package com.lt.health.config.security.filter;

import com.lt.health.config.security.service.UserDetailServiceImpl;
import com.lt.health.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: token过滤器
 * @author: 狂小腾
 * @date: 2022/3/22 22:56
 */
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1.获取token
        String header = request.getHeader(tokenHeader);
        // 2.判断token是否存在
        if (header != null && header.startsWith(tokenHead)) {
            // 拿到token主体
            String token = header.substring(tokenHead.length());
            // 根据token获取用户名
            String username = jwtTokenUtil.getUserNameFromToken(token);
            // 3 token存在，但是没有登录信息
            if (username != null && null == SecurityContextHolder.getContext().getAuthentication()) {
                // 没有登录信息 直接登录
                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                // 判断token是否有效
                if (!jwtTokenUtil.isExpiration(token) && username.equals(userDetails.getUsername())) {
                    // 刷新security用户中的信息
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        // 4.过滤器放行
        filterChain.doFilter(request, response);
    }
}
