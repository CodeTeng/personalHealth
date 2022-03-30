package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.PageInfoDTO;

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

    /**
     * 分页查询
     *
     * @param pageInfoDTO 分页参数
     * @return 分页结果集
     */
    Result findPage(PageInfoDTO pageInfoDTO);

    /**
     * 添加角色
     *
     * @param role 角色信息
     * @return 成功或者失败信息
     */
    Result insert(Role role);

    /**
     * 修改角色
     *
     * @param role 角色信息
     * @return 修改提示信息
     */
    Result update(Role role);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 删除提示信息
     */
    Result delete(Long roleId);
}
