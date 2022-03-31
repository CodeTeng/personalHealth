package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class Menu implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单组件
     */
    private String component;

    /**
     * 父级菜单
     */
    private Long parentId;

    /**
     * 显示状态(0不显示、1显示)
     */
    private boolean status;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}