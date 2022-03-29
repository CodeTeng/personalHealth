package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 狂小腾
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2022-03-26 20:36:16
*/
public interface RoleService extends IService<Role> {

    /**
     * 添加用户时角色信息列表
     */
    Result findAll();
}
