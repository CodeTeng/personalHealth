package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class Permission implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限标签
     */
    private String label;

    /**
     * 数据权限标签值
     */
    private String code;

    /**
     * 显示状态(0不显示、1显示)
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}