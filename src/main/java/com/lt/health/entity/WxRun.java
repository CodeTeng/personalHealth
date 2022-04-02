package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信运动步数记录表
 *
 * @author: 狂小腾
 * @TableName sys_wx_run
 */
@TableName(value = "sys_wx_run")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxRun implements Serializable {
    /**
     * 微信唯一标识
     */
    private String openid;

    /**
     * 时间
     */
    private String time;

    /**
     * 运动步数
     */
    private Integer step;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}