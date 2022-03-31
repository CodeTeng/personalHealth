package com.lt.health.service;

import com.lt.health.constant.Result;
import com.lt.health.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.health.entity.dto.PageInfoDTO;

/**
 * @author 狂小腾
 * @description 针对表【sys_permission】的数据库操作Service
 * @createDate 2022-03-26 20:36:16
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 查询所有的权限
     *
     * @return 返回结果
     */
    Result findAll();

    /**
     * 分页查询
     *
     * @param pageInfoDTO 分页参数---权限标签或数据权限标签
     * @return 分页结果
     */
    Result findPage(PageInfoDTO pageInfoDTO);

    /**
     * 添加权限
     *
     * @param permission 添加权限数据
     * @return 成功或失败结果
     */
    Result insert(Permission permission);

    /**
     * 修改权限
     *
     * @param permission 修改的权限数据
     * @return 成功或失败的结果
     */
    Result update(Permission permission);

    /**
     * 删除权限
     *
     * @param id 权限id
     * @return 成功或失败结果
     */
    Result delete(Long id);
}
