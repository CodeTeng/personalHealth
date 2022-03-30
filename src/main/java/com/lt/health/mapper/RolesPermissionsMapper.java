package com.lt.health.mapper;

import com.lt.health.entity.RolesPermissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 狂小腾
 * @description 针对表【sys_roles_permissions】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.RolesPermissions
 */
@Repository
public interface RolesPermissionsMapper extends BaseMapper<RolesPermissions> {

    /**
     * 根据角色id和权限id插入数据
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     */
    @Insert("insert into sys_roles_permissions values (#{roleId}, #{permissionId})")
    void insertRolesPermissions(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    /**
     * 根据角色id删除对应的权限
     *
     * @param roleId 角色id
     */
    @Delete("delete from sys_roles_permissions where role_id = #{roleId}")
    void deletePermissionByRoleId(@Param("roleId") Long roleId);
}




