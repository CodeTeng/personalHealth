package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_roles_menus
 */
@TableName(value ="sys_roles_menus")
@Data
public class RolesMenus implements Serializable {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单权限ID
     */
    private Long menuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}