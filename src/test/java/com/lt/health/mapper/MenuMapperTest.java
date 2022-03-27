package com.lt.health.mapper;

import com.lt.health.entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description: 测试菜单sql
 * @author: 狂小腾
 * @date: 2022/3/27 17:05
 */
@SpringBootTest
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    void findMenusByUserId() {
        List<Menu> menus = menuMapper.findMenusByUserId(null);
        System.out.println(menus);
    }

    @Test
    void findChildrenMenuByPidAndUserId() {
        List<Menu> childrenMenus = menuMapper.findChildrenMenuByPidAndUserId(1L, null);
        System.out.println(childrenMenus);

    }
}