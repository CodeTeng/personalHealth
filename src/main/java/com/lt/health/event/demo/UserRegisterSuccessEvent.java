package com.lt.health.event.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 用户注册成功具体事件
 * @author: 狂小腾
 * @date: 2022/4/2 16:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegisterSuccessEvent extends AbstractEvent {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 创建用户注册成功事件对象
     *
     * @param source   事件源
     * @param userName 当前注册的用户名
     */
    public UserRegisterSuccessEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }
}
