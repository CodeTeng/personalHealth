package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-数据权限表
 * @author 狂小腾
 * @TableName sys_roles_permissions
 */
@TableName(value ="sys_roles_permissions")
@Data
@ApiModel("角色数据权限实体类")
public class RolesPermissions implements Serializable {
    /**
     * 角色ID
     */
    @ApiModelProperty("角色id")
    private Long roleId;

    /**
     * 数据权限ID
     */
    @ApiModelProperty("数据权限id")
    private Long permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}