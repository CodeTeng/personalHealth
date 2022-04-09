package com.lt.health.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 发送邮件参数
 * @author: 狂小腾
 * @date: 2022/3/27 21:58
 */
@Data
@ApiModel("发送邮件参数")
public class MailDTO implements Serializable {

    /**
     * 是否是HTML格式
     */
    @ApiModelProperty("是否是HTML格式")
    private boolean html = false;

    /**
     * 接收人的邮箱 可以有多个
     */
    @ApiModelProperty("接收人的邮箱")
    private String[] receivers;

    /**
     * 邮件主题
     */
    @ApiModelProperty("邮件主题")
    private String subject;

    /**
     * 邮件内容
     */
    @ApiModelProperty("邮件内容")
    private String content;
}
