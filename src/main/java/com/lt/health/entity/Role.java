package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
    private boolean status;

    @TableLogic
    private Integer isDelete;

    /**
     * 菜单列表
     */
    @TableField(exist = false)
    private List<Menu> menus;

    /**
     * 权限列表
     */
    @TableField(exist = false)
    private List<Permission> permissions;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}