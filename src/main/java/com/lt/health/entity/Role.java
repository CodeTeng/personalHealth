package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("角色实体类")
public class Role implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;

    /**
     * 角色标签
     */
    @ApiModelProperty("角色标签")
    private String label;

    /**
     * 角色对应的标签值
     */
    @ApiModelProperty("角色对应的标签值")
    private String code;

    /**
     * 显示状态(0不显示、1显示)
     */
    @ApiModelProperty("角色显示状态 0-不显示 1-显示")
    private boolean status;

    @TableLogic
    @ApiModelProperty("受否删除")
    private Integer isDelete;

    /**
     * 菜单列表
     */
    @ApiModelProperty("菜单列表")
    @TableField(exist = false)
    private List<Menu> menus;

    /**
     * 权限列表
     */
    @TableField(exist = false)
    @ApiModelProperty("数据权限列表")
    private List<Permission> permissions;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}