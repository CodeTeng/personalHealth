package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 *
 * @author 狂小腾
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
@ApiModel("用户实体类")
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;

    /**
     * 登录名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 性别(0男，1女，2未知)
     */
    @ApiModelProperty("性别")
    private Integer sex;

    /**
     * 用户头像地址
     */
    @ApiModelProperty("用户头像地址")
    private String avatarUrl;

    /**
     * 地址
     */
    @ApiModelProperty("用户地址")
    private String address;

    /**
     * 微信小程序openid，每个用户对应一个，且唯一
     */
    @ApiModelProperty("WX唯一id")
    private String openId;

    /**
     * 当前状态 1-正常 0-不正常
     */
    @ApiModelProperty("当前用户状态")
    private Integer userStatus;

    /**
     * 是否是管理员 1-管理员 0-非管理员
     */
    @ApiModelProperty("用户是否是管理员")
    private Integer isAdmin;

    /**
     * 电话号码
     */
    @ApiModelProperty("用户电话号码")
    private String phoneNumber;

    /**
     * 用户邮箱
     */
    @ApiModelProperty("用户邮箱")
    private String email;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 是否删除 1-删除 0-未删除
     */
    @ApiModelProperty("是否删除")
    @TableLogic
    private Integer isDelete;

    /**
     * 角色信息
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "角色信息")
    private List<Role> roles;

    /**
     * 用户对应的菜单信息
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "用户对应的菜单列表")
    private List<Menu> menus;

    /**
     * 用户对应的权限列表
     */
    @TableField(exist = false)
    @ApiModelProperty(value = "用户对应的权限数据")
    private List<Permission> permissions;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}