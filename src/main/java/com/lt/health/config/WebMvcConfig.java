package com.lt.health.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: 狂小腾
 * @date: 2022/3/22 19:32
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                // 过滤所有的请求
                .addMapping("/**")
                //配置跨域来源
                .allowedOrigins("http://localhost:8888")
                // 是否支持跨域Cookie
                .allowCredentials(true)
                // 最大响应时间
                .maxAge(3600)
                // 设置允许访问的方法
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTION");
    }
}
