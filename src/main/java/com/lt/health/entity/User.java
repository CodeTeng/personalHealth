package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.*;
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
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 登录名
     */
    private String userName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别(0男，1女，2未知)
     */
    private Integer sex;

    /**
     * 用户头像地址
     */
    private String avatarUrl;

    /**
     * 地址
     */
    private String address;

    /**
     * 微信小程序openid，每个用户对应一个，且唯一
     */
    private String openId;

    /**
     * 当前状态 1-正常 0-不正常
     */
    private Integer userStatus;

    /**
     * 是否是管理员 1-管理员 0-非管理员
     */
    private Integer isAdmin;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 1-删除 0-未删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 角色信息
     */
    @TableField(exist = false)
    private List<Role> roles;

    /**
     * 用户对应的菜单信息
     */
    @TableField(exist = false)
    private List<Menu> menus;

    /**
     * 用户对应的权限列表
     */
    @TableField(exist = false)
    private List<Permission> permissions;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}