package com.lt.health.mapper;

import com.lt.health.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_permission】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.Permission
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户ID查询权限数据
     *
     * @param userId 用户id
     * @return 权限数据
     */
    List<Permission> findPermissionsByUserId(@Param("userId") Long userId);
}




