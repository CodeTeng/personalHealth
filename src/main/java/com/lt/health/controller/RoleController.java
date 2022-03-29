package com.lt.health.controller;

import com.lt.health.constant.Result;
import com.lt.health.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 角色控制器
 * @author: 狂小腾
 * @date: 2022/3/28 22:42
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加用户时角色信息列表
     */
    @GetMapping("/findAll")
    public Result findAll() {
        return roleService.findAll();
    }
}
