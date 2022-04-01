package com.lt.health.controller;

import com.lt.health.aop.log.SystemCrmLog;
import com.lt.health.constant.TableNameConstant;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试控制器
 * @author: 狂小腾
 * @date: 2022/3/31 20:58
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 普通用户没有删除权限
     */
    @ApiOperation("测试hello接口")
    @ApiImplicitParam(name = "name", value = "用户名称", required = true)
    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    @SystemCrmLog(description = "进行测试权限操作", tableName = {TableNameConstant.USER_TABLE_NAME, TableNameConstant.ROLE_TABLE_NAME, TableNameConstant.MENU_TABLE_NAME})
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }
}
