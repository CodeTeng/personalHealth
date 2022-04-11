package com.lt.health.exception;

/**
 * @description: 验证码失效异常类
 * @author: 狂小腾
 * @date: 2022/4/11 12:35
 */
public class CaptchaExpireException extends RuntimeException {

    public CaptchaExpireException() {
        super("验证码已失效！！！");
    }
}
