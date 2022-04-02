package com.lt.health.event.demo;

import org.springframework.stereotype.Component;

/**
 * @description: 用户注册成功事件监听器---负责给用户发送邮件
 * @author: 狂小腾
 * @date: 2022/4/2 17:04
 */
@Component
public class SendEmailOnUserRegisterSuccessListener implements EventListener<UserRegisterSuccessEvent> {

    @Override
    public void onEvent(UserRegisterSuccessEvent event) {
        System.out.println(String.format("给用户【%s】发送注册成功邮件!", event.getUserName()));
    }
}
