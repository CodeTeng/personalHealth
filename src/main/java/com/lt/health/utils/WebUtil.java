package com.lt.health.utils;

import com.lt.health.constant.UserConstant;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: 日志工具类 用来获取session中的用户信息来存入数据库
 * @author: 狂小腾
 * @date: 2022/4/1 18:47
 */
@Component
public class WebUtil {

    /**
     * 从session中获取用户对象
     *
     * @param request request
     * @return 用户对象
     */
    public Map<String, Object> getUser(HttpServletRequest request) {
        Map<String, Object> attributes = null;
        if (request != null) {
            Object user = request.getSession().getAttribute(UserConstant.USER_SESSION_KEY);
            attributes = (Map<String, Object>) user;
        }
        return attributes;
    }
}
