package com.lt.health.constant;

/**
 * @description: 通用常量
 * @author: 狂小腾
 * @date: 2022/4/10 16:09
 */
public class Constants {
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;
}
