package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 日志表
 *
 * @author 狂小腾
 * @TableName sys_log_message
 */
@TableName(value = "sys_log_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogMessage implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 日志表述
     */
    private String content;

    /**
     * 参数集合
     */
    private String remarks;

    /**
     * 表格名称
     */
    private String tableName;

    /**
     * 操作时间
     */
    private String dateTime;

    /**
     * 返回值
     */
    private String resultValue;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 操作结果
     */
    private String result;

    /**
     * 错误信息
     */
    private String exString;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}