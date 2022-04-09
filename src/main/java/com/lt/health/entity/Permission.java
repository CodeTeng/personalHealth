package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据权限表
 *
 * @author 狂小腾
 * @TableName sys_permission
 */
@TableName(value = "sys_permission")
@Data
@ApiModel("数据权限实体类")
public class Permission implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;

    /**
     * 权限标签
     */
    @ApiModelProperty("权限标签")
    private String label;

    /**
     * 数据权限标签值
     */
    @ApiModelProperty("权限标签值")
    private String code;

    /**
     * 显示状态(0不显示、1显示)
     */
    @ApiModelProperty("数据权限显示状态 0-不显示 1-显示")
    private boolean status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}