package com.lt.health.event.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @description: 事件配置类
 * @author: 狂小腾
 * @date: 2022/4/2 16:50
 */
@Configuration
public class MyConfig {

    /**
     * 注册一个bean：事件发布者
     *
     * @param eventListeners 事件监听者
     * @return 事件发布者
     */
    @Bean
    @Autowired(required = false)
    public EventMulticaster eventMulticaster(List<EventListener> eventListeners) {
        EventMulticaster eventMulticaster = new SimpleEventMulticaster();
        if (eventListeners != null) {
            eventListeners.forEach(eventMulticaster::addEventListener);
        }
        return eventMulticaster;
    }

    /**
     * 注册一个bean：用户注册服务
     *
     * @param eventMulticaster 事件广播器
     * @return 用户注册服务
     */
    @Bean
    public UserRegisterService userRegisterService(EventMulticaster eventMulticaster) {
        UserRegisterService userRegisterService = new UserRegisterService();
        userRegisterService.setEventMulticaster(eventMulticaster);
        return userRegisterService;
    }
}
