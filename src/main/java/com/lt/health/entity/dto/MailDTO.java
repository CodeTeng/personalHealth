package com.lt.health.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 发送邮件参数
 * @author: 狂小腾
 * @date: 2022/3/27 21:58
 */
@Data
public class MailDTO implements Serializable {

    /**
     * 是否是HTML格式
     */
    private boolean html = false;

    /**
     * 接收人的邮箱 可以有多个
     */
    private String[] receivers;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;
}
