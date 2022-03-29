package com.lt.health.mapper;

import com.lt.health.entity.UserRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 狂小腾
 * @description 针对表【sys_user_roles】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.UserRoles
 */
@Repository
public interface UserRolesMapper extends BaseMapper<UserRoles> {

    /**
     * 添加用户id和角色id
     *
     * @param userId 用户id
     * @param roleId 角色id
     */
    void insertUserRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);
}




