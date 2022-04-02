package com.lt.health.event.demo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description: 事件测试
 * @author: 狂小腾
 * @date: 2022/4/2 16:59
 */
public class EventTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(MyConfig.class);
        // 获取用户注册服务
        UserRegisterService userRegisterService = context.getBean(UserRegisterService.class);
        // 模拟用户注册
        userRegisterService.registerUser("路人甲Java");
    }
}
