package com.lt.health.exception;

/**
 * @description: 验证码错误异常
 * @author: 狂小腾
 * @date: 2022/4/11 12:40
 */
public class CaptchaException extends RuntimeException {

    public CaptchaException() {
        super("验证码错误！！！");
    }
}
