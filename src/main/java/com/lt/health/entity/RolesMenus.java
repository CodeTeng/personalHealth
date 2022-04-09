package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-菜单权限表
 *
 * @author 狂小腾
 * @TableName sys_roles_menus
 */
@TableName(value = "sys_roles_menus")
@Data
@ApiModel("角色菜单权限实体类")
public class RolesMenus implements Serializable {
    /**
     * 角色ID
     */
    @ApiModelProperty("角色id")
    private Long roleId;

    /**
     * 菜单权限ID
     */
    @ApiModelProperty("菜单id")
    private Long menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}