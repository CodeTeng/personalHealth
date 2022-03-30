package com.lt.health.mapper;

import com.lt.health.entity.RolesMenus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 狂小腾
 * @description 针对表【sys_roles_menus】的数据库操作Mapper
 * @createDate 2022-03-26 20:36:16
 * @Entity com.lt.health.entity.RolesMenus
 */
@Repository
public interface RolesMenusMapper extends BaseMapper<RolesMenus> {

    /**
     * 根据角色id和菜单id插入数据
     *
     * @param roleId 角色id
     * @param menuId 菜单id
     */
    @Insert("insert into sys_roles_menus values (#{roleId}, #{menuId})")
    void insertRolesMenus(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    /**
     * 根据角色id删除对应菜单
     *
     * @param roleId 角色id
     */
    @Delete("delete from sys_roles_menus where role_id = #{roleId}")
    void deleteMenusByRoleId(@Param("roleId") Long roleId);
}




