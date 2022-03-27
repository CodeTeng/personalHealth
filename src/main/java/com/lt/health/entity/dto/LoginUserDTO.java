package com.lt.health.entity.dto;

import lombok.Data;

/**
 * @description: 登录参数
 * @author: 狂小腾
 * @date: 2022/3/22 19:23
 */
@Data
public class LoginUserDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 验证码
     */
    private String code;

    /**
     * 1-账号密码登录 2-手机验证码登录
     */
    private String type;
}
