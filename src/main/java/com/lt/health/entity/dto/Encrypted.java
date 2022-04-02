package com.lt.health.entity.dto;

import lombok.Data;

/**
 * @description: WX小程序加密数据
 * @author: 狂小腾
 * @date: 2022/4/2 21:50
 */
@Data
public class Encrypted {
    /**
     * 加密数据
     */
    private String encryptedData;

    /**
     * 调用微信运动传递的矢量加密算法
     */
    private String iv;

    /**
     * 微信小程序登陆获取的会话key
     */
    private String sessionKey;

    /**
     * 微信小程序唯一标志
     */
    private String openid;
}
