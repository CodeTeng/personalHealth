package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色表
 *
 * @author 狂小腾
 * @TableName sys_user_roles
 */
@TableName(value = "sys_user_roles")
@Data
@ApiModel("用户角色实体类")
public class UserRoles implements Serializable {
    /**
     * 用户ID
     */
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色id")
    private Long roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}