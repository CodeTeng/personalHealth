package com.lt.health.constant;

/**
 * @description: 用户常量信息
 * @author: 狂小腾
 * @date: 2022/3/28 19:44
 */
public interface UserConstant {
    /**
     * 用户缓存的key的前缀
     */
    String USER_KEY_PRE = "userInfo";

    /**
     * 用户缓存过期时间 5min
     */
    long EXPIRE_TIME = 5L;

    /**
     * 用户Session的key
     */
    String USER_SESSION_KEY = "userSession";

}
