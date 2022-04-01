package com.lt.health.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("测试hello接口")
    @ApiImplicitParam(name = "name", value = "用户名称", required = true)
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

}
