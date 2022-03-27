package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表
 *
 * @author 狂小腾
 * @TableName sys_role
 */
@TableName(value = "sys_role")
@Data
public class Role implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色标签
     */
    private String label;

    /**
     * 角色对应的标签值
     */
    private String code;

    /**
     * 显示状态(0不显示、1显示)
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}