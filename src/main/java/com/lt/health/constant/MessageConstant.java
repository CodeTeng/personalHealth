package com.lt.health.constant;

/**
 * @description: 消息常量
 * @author: 狂小腾
 * @date: 2022/3/21 23:13
 */
public interface MessageConstant {
    String PAGE_SUCCESS = "分页查询成功";
    String PAGE_FAIL = "分页查询失败";
    String LOGIN_SUCCESS = "登录成功";
    String LOGIN_FAIL = "登录失败";
    String LOGOUT_SUCCESS = "退出成功";
    String LOGIN_USERNAME_FAIL = "该账号不存在";
    String LOGIN_USERNAME_PASSWORD_FAIL = "用户名或密码输入错误";
    String LOGIN_NOT_ADMIN = "您不是管理员，不可以进入系统";
    String NOT_PERMISSION = "权限不足";
    String LOGIN_NOT_STATE = "当前用户未激活";
    String UPLOAD_SUCCESS = "上传成功";
    String UPLOAD_FAIL = "上传失败";
    String FILE_READER_FAIL = "文件读取失败";
    String PIC_UPLOAD_SUCCESS = "图片上传成功";
    String PIC_UPLOAD_FAIL = "图片上传失败";
    String UPLOAD_CHOOSE = "请选择文件";
    String USER_SELECT_FAIL = "用户信息查询失败";
    String USER_SELECT_SUCCESS = "用户信息查询成功";

    String ADMIN_NOT_LOGIN = "尚未登录，或者登录过期";
    String UPDATE_USER_FAIL = "修改用户信息失败";
    String UPDATE_USER_SUCCESS = "修改用户信息成功";
    String ADD_USER_SUCCESS = "添加用户成功";
    String ADD_USER_FAIL = "添加用户失败";
    String USER_EXIST = "该用户名已经存在";
    String USER_NO_EXIST = "该用户不存在";
    String USER_DIS_ENABLE = "该用户被禁用";
    String DELETE_USER_SUCCESS = "用户信息删除成功";

    String SPORT_SELECT_FAIL = "运动信息查询失败";
    String SPORT_SELECT_SUCCESS = "运动信息查询成功";
    String SPORT_EXIST = "该运动已经存在";
    String ADD_SPORT_FAIL = "添加运动项目失败";
    String ADD_SPORT_SUCCESS = "添加运动成功";
    String UPDATE_SPORT_FAIL = "修改运动信息失败";
    String UPDATE_SPORT_SUCCESS = "修改运动信息成功";
    String CHOOSE_SPORT = "请选择运动项目";
    String SPORT_CONSULT_INSERT_SUCCESS = "运动咨询插入成功";
    String SPORT_CONSULT_INSERT_FAIL = "运动咨询插入失败";
    String SPORT_CONSULT_UPDATE_SUCCESS = "运动咨询更新成功";
    String SPORT_CONSULT_UPDATE_FAIL = "运动咨询更新失败";
    String SPORT_CONSULT_DELETE_SUCCESS = "运动咨询删除成功";
    String SPORT_CONSULT_DELETE_FAIL = "运动咨询删除失败";

    String DELETE_SPORT_FAIL = "删除运动项目失败";
    String DELETE_SPORT_SUCCESS = "删除运动项目成功";
    String REPORT_VIP_SUCCESS = "会员统计成功";
    String REPORT_VIP_EMPTY = "该时间内份暂无会员注册";
    String REPORT_VIP_TYPE_EMPTY = "请选择时间";

    String HALF_YEAR_AGO = "1";
    String THREE_YEAR_AGO = "3";
    String ONE_YEAR_AGO = "2";

    String GOODS_SELECT_FAIL = "商品查询失败";
    String GOODS_NO_EXIST = "商品不存在";
    String GOODS_SELECT_SUCCESS = "商品查询成功";
    String GOODS_EXIST = "商品已存在，请勿重复添加";
    String ADD_GOODS_FAIL = "商品添加失败";
    String ADD_GOODS_SUCCESS = "商品添加成功";
    String CHOOSE_GOODS = "请选择商品";
    String DELETE_GOODS_FAIL = "商品删除失败";
    String DELETE_GOODS_SUCCESS = "商品删除成功";
    String UPDATE_GOODS_FAIL = "商品更新失败";
    String UPDATE_GOODS_SUCCESS = "商品更新成功";

    String BATCH_IMPORT_SUCCESS = "数据批量导入成功";
    String BATCH_IMPORT_FAIL = "数据批量导入失败";
    String RESPONSE_OUT_FAIL = "文件导出失败";
    String RESPONSE_OUT_SUCCESS = "文件导出成功";
    String ROLE_SELECT_SUCCESS = "角色查询成功";
    String ROLE_SELECT_FAIL = "角色查询失败";
    String ROLE_INSERT_FAIL = "角色添加失败";
    String ROLE_INSERT_SUCCESS = "角色添加成功";
    String ROLE_DELETE_SUCCESS = "角色删除成功";
    String ROLE_DELETE_FAIL = "角色删除失败";
    String ROLE_UPDATE_FAIL = "角色修改失败";
    String ROLE_UPDATE_SUCCESS = "角色修改成功";
    String ROLE_EXIST = "角色已存在";
    String ROLE_DELETE_TIP_MENU = "删除失败，该角色下拥有菜单信息，请先删除对应的菜单信息！";
    String ROLE_DELETE_TIP_PERMISSION = "删除失败，该角色下拥有权限信息，请先删除对应的权限信息！";

    String MENU_SELECT_SUCCESS = "菜单列表查询成功";
    String MENU_SELECT_FAIL = "菜单列表查询失败";
    String MENU_INSERT_SUCCESS = "菜单添加成功";
    String MENU_INSERT_FAIL = "菜单添加失败";
    String MENU_DELETE_SUCCESS = "菜单删除成功";
    String MENU_DELETE_FAIL = "菜单删除失败";
    String MENU_UPDATE_SUCCESS = "菜单修改成功";
    String MENU_UPDATE_FAIL = "菜单修改失败";
    String MENU_NO_EXIST = "菜单不存在";
    String PARENT_MENU_SELECT_SUCCESS = "父级菜单查询成功";

    String PREMISSION_GET_SUCCESS = "权限查询成功";
    String PREMISSION_GET_FAIL = "权限查询失败";
    String PREMISSION_INSERT_SUCCESS = "权限添加成功";
    String PREMISSION_INSERT_FAIL = "权限添加失败";
    String PREMISSION_DELETE_SUCCESS = "权限删除成功";
    String PREMISSION_DELETE_FAIL = "权限删除失败";
    String PREMISSION_UPDATE_SUCCESS = "权限修改成功";
    String PREMISSION_UDPATE_FAIL = "权限修改失败";

    String COMPLETE_USER_INFO = "请按要求填写必填字段";
    String PHONE_CODE_SEND_SUCCESS = "手机验证码发送成功";
}
