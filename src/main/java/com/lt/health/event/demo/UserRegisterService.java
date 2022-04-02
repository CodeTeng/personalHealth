package com.lt.health.event.demo;

import lombok.Data;

/**
 * @description: 用户注册服务
 * @author: 狂小腾
 * @date: 2022/4/2 16:47
 */
@Data
public class UserRegisterService {

    /**
     * 事件发布者
     */
    private EventMulticaster eventMulticaster;

    /**
     * 注册用户
     *
     * @param userName 用户名
     */
    public void registerUser(String userName) {
        // 将用户信息存入数据库
        System.out.println(String.format("用户【%s】注册成功", userName));
        // 发送广播
        eventMulticaster.multicastEvent(new UserRegisterSuccessEvent(this, userName));
    }
}
