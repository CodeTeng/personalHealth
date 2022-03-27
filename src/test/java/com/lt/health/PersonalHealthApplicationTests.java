package com.lt.health;

import com.lt.health.entity.User;
import com.lt.health.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class PersonalHealthApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        List<User> list = userService.list();
        int size = list.size();
        Assertions.assertEquals(0, size);
    }



}
