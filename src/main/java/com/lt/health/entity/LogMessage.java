package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("日志实体类")
public class LogMessage implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String userName;

    /**
     * 日志表述
     */
    @ApiModelProperty("日志描述")
    private String content;

    /**
     * 参数集合
     */
    @ApiModelProperty("参数集合")
    @JsonIgnore
    private String remarks;

    /**
     * 表格名称
     */
    @ApiModelProperty("操作表格名称")
    private String tableName;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private String dateTime;

    /**
     * 返回值
     */
    @ApiModelProperty("返回值")
    private String resultValue;

    /**
     * ip地址
     */
    @ApiModelProperty("ip地址")
    private String ip;

    /**
     * 请求地址
     */
    @ApiModelProperty("请求地址")
    private String requestUrl;

    /**
     * 操作结果
     */
    @ApiModelProperty("操作结果")
    private String result;

    /**
     * 错误信息
     */
    @ApiModelProperty("错误信息")
    private String exString;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}