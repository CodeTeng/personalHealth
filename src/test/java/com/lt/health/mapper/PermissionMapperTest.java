package com.lt.health.mapper;

import com.lt.health.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @description: 测试数据权限sql
 * @author: 狂小腾
 * @date: 2022/3/27 17:01
 */
@SpringBootTest
public class PermissionMapperTest {

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void findPermissionsByUserId() {
        List<Permission> permissions = permissionMapper.findPermissionsByUserId(null);
        System.out.println(permissions);
    }
}