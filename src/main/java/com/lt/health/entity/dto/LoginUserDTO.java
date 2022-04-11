package com.lt.health.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 登录参数
 * @author: 狂小腾
 * @date: 2022/3/22 19:23
 */
@Data
@ApiModel("用户登录参数")
public class LoginUserDTO {

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String phoneNumber;

    /**
     * 手机验证码
     */
    @ApiModelProperty("手机验证码")
    private String code;

    /**
     * 1-账号密码登录 2-手机验证码登录
     */
    @ApiModelProperty("登录方式 1-普通登录 2-手机验证码登录")
    private String type;

    /**
     * 唯一标识
     */
    @ApiModelProperty("图片验证唯一标识")
    private String uuid;

    /**
     * 登录图片验证码
     */
    @ApiModelProperty("图片验证码")
    private String captchaCode;
}
