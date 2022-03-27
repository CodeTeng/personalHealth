package com.lt.health.mapper;

import com.lt.health.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description: 测试角色sql
 * @author: 狂小腾
 * @date: 2022/3/27 17:01
 */
@SpringBootTest
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    void findRolesByUserId() {
        List<Role> roles = roleMapper.findRolesByUserId(null);
        System.out.println(roles);
    }
}