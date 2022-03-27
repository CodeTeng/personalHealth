package com.lt.health.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.health.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 狂小腾
 * @description 针对表【sys_role】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.Role
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户ID查询角色信息
     *
     * @param userId 用户id
     * @return 该用户角色信息
     */
    List<Role> findRolesByUserId(@Param("userId") Long userId);
}




