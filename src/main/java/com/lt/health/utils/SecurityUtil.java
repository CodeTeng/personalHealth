package com.lt.health.utils;

import com.lt.health.config.security.entity.LoginUser;
import com.lt.health.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @description: 用于获取当前登录用户的基本信息
 * @author: 狂小腾
 * @date: 2022/3/28 19:14
 */
public class SecurityUtil {

    /**
     * 从Security主体信息中获取用户信息
     */
    public static User getUser() {
        User user = null;
        try {
            LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (loginUser == null) {
                throw new Exception("用户不存在！！！");
            }
            user = loginUser.getUser();
            if (user == null) {
                throw new Exception("用户不存在！！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 用户脱敏
        user.setPassword(null);
        return user;
    }

    /**
     * 在security中获取用户名
     */
    public static String getUsername() {
        return getUser().getUserName();
    }

    /**
     * 在security中获取用户id
     */
    public static Long getUserId() {
       return getUser().getId();
    }

    /**
     * 在security中获取用户小程序ID
     */
    public static String getOpenId() {
        return getUser().getOpenId();
    }
}
