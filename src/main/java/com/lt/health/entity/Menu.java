package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单表
 *
 * @author 狂小腾
 * @TableName sys_menu
 */
@TableName(value = "sys_menu")
@Data
@ApiModel("菜单实体类")
public class Menu implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;

    /**
     * 菜单路径
     */
    @ApiModelProperty("菜单路径")
    private String path;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String title;

    /**
     * 菜单组件
     */
    @ApiModelProperty("菜单组件")
    private String component;

    /**
     * 父级菜单
     */
    @ApiModelProperty("父级菜单id")
    private Long parentId;

    /**
     * 显示状态(0不显示、1显示)
     */
    @ApiModelProperty("菜单显示状态 0-不显示 1-显示")
    private boolean status;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    @ApiModelProperty("子菜单")
    private List<Menu> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}