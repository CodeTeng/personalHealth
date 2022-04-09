package com.lt.health.constant;

/**
 * @description: 表名常量类
 * @author: 狂小腾
 * @date: 2022/4/1 23:15
 */
public interface TableNameConstant {

    /**
     * 用户表
     */
    String USER_TABLE_NAME = "sys_user";

    /**
     * 食物表
     */
    String FOOD_TABLE_NAME = "sys_food";

    /**
     * 食物类别表
     */
    String FOOD_TYPE_TABLE_NAME = "sys_food_type";

    /**
     * 用户日志表
     */
    String LOG_MESSAGE_TABLE_NAME = "sys_log_message";

    /**
     * 角色表
     */
    String ROLE_TABLE_NAME = "sys_role";

    /**
     * 角色-菜单表
     */
    String ROLE_MENU_TABLE_NAME = "sys_roles_menus";

    /**
     * 角色权限表
     */
    String ROLE_PERMISSIONS_TABLE_NAME = "sys_roles_permissions";

    /**
     * 菜单权限表
     */
    String MENU_TABLE_NAME = "sys_menu";

    /**
     * 运动项目表
     */
    String MOTION_TABLE_NAME = "sys_motion";

    /**
     * 数据权限表
     */
    String PERMISSIONS_TABLE_NAME = "sys_permission";

    /**
     * 运动咨询表
     */
    String SPORT_TABLE_NAME = "sys_port";

    /**
     * WX运动步数表
     */
    String WX_RUN_TABLE_NAME = "sys_wx_run";

    /**
     * 用户-角色表
     */
    String USER_ROLE_TABLE_NAME = "sys_user_roles";

    /**
     * 商品表
     */
    String GOODS_TABLE_NAME = "sys_goods";
}
